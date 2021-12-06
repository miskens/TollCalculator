package Main;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Interfaces.ITollable;

public class TollCalculator {

    public int getTollFee(ITollable[] vehicles, ITollable currentVehicle, int lastFee, int currentTotalFee, LocalDateTime timeOfCurrentFlash) {
        int fee = 0;
        LocalDateTime timeOfLastCameraFlash = currentVehicle.getTimeOfLastCameraFlash();

        resetFeeIfNewDay(vehicles, timeOfCurrentFlash, timeOfLastCameraFlash);

        boolean zeroFee = checkIfZeroFee(timeOfCurrentFlash, currentTotalFee);

        if(zeroFee) {
            return 0;
        }

        fee = calculateHours(currentVehicle, timeOfLastCameraFlash, timeOfCurrentFlash, lastFee, fee);

        boolean reachesTotalDailyFee = checkIfNewFeeReachesDailyTotalFee(currentTotalFee, fee);

        if (reachesTotalDailyFee) {
            currentVehicle.setCurrentTotalFee(60);
            return 60 - currentTotalFee;
        }

        updateCurrentVehicle(currentVehicle, currentTotalFee, fee, timeOfCurrentFlash);

        return fee;
    }

    private boolean checkIfNewFeeReachesDailyTotalFee(int currentTotalFee, int fee) {
        if (currentTotalFee + fee >= 60) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkIfZeroFee(LocalDateTime timeOfCurrentFlash, int currentTotalFee) {
        DayOfWeek dayName = timeOfCurrentFlash.getDayOfWeek();
        if (currentTotalFee >= 60 || dayName == DayOfWeek.SATURDAY || dayName == DayOfWeek.SUNDAY) {
            return true;
        }
        else {
            return false;
        }
    }

    private void resetFeeIfNewDay(ITollable[] vehicles, LocalDateTime timeOfCurrentFlash, LocalDateTime timeOfLastCameraFlash) {
        int dayOfCurrentFlash = timeOfCurrentFlash.getDayOfMonth();
        if (timeOfLastCameraFlash != null) {
            int dayOfLastFlash = timeOfLastCameraFlash.getDayOfMonth();
            resetCurrentTotalFeeForNewday(vehicles, dayOfCurrentFlash, dayOfLastFlash);
        }
    }

    private int calculateHours(ITollable currentVehicle, LocalDateTime timeOfLastCameraFlash, LocalDateTime timeOfCurrentFlash, int lastFee, int fee) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int hour = timeOfCurrentFlash.getHour();

        fee = getFeeDependingOnHoursAndVehicleType(hour, vehicleType);

        fee = calculateFeeWithinSameHour(currentVehicle, timeOfLastCameraFlash, timeOfCurrentFlash, lastFee, fee);

        return fee;
    }

    private int calculateFeeWithinSameHour(ITollable currentVehicle, LocalDateTime timeOfLastCameraFlash, LocalDateTime timeOfCurrentFlash,
                                                        int lastFee, int fee) {
        if (currentVehicle.getTimeOfLastCameraFlash() != null) { 
            long minutesSinceLastFlash = timeOfLastCameraFlash.until(timeOfCurrentFlash, ChronoUnit.MINUTES);

            if (minutesSinceLastFlash <= 60) {
                if (lastFee > fee) {
                    return lastFee - fee;
                } else if (fee > lastFee) {
                    currentVehicle.setLastFee(fee);
                    return fee - lastFee;
                } else {
                    return 0;
                }
            }
        }
        
        return fee;
    }

    private void updateCurrentVehicle(ITollable currentVehicle, int currentTotalFee, int fee, LocalDateTime timeOfCurrentFlash) {
        currentVehicle.setCurrentTotalFee(currentTotalFee + fee);
        currentVehicle.setLastFee(fee);
        currentVehicle.setTimeOfLastCameraFlash(timeOfCurrentFlash);
    }

    private void resetCurrentTotalFeeForNewday(ITollable[] vehicles, int dayOfLastFlash, int dayOfCurrentFlash) {
        if (dayOfCurrentFlash > dayOfLastFlash) {
            for (ITollable vehicle : vehicles)
            vehicle.setCurrentTotalFee(0);
            }
    }

    private int getFeeDependingOnHoursAndVehicleType(int hour, String vehicleType) {
        switch (hour) {
            case 8:
            case 16:
                return 18;
            default:
                switch (vehicleType) {
                    case "Car":
                        return 11;
                    case "Motorcycle":
                        return 8;
                    default:
                        return 0;
                }
        }
    }
}
