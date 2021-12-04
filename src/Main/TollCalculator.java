package Main;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle[] vehicles, Vehicle currentVehicle, int lastFee, int currentTotalFee, LocalDateTime timeOfCurrentFlash) {
        int fee = 0;
        LocalDateTime timeOfLastCameraFlash = currentVehicle.getTimeOfLastCameraFlash();

        boolean zeroFee = checkIfNewDayOrZeroFee(vehicles, timeOfLastCameraFlash, timeOfCurrentFlash, currentVehicle, currentTotalFee);

        if(zeroFee) {
            return 0;
        }

        fee = calculateHours(currentVehicle, timeOfLastCameraFlash, timeOfCurrentFlash, lastFee, fee);

        if (currentTotalFee + fee >= 60) {
            currentVehicle.setCurrentTotalFee(60);
            return 60 - currentTotalFee;
        }

        updateCurrentVehicle(currentVehicle, currentTotalFee, fee, timeOfCurrentFlash);

        return fee;
    }

    private boolean checkIfNewDayOrZeroFee(Vehicle[] vehicles, LocalDateTime timeOfLastCameraFlash, LocalDateTime timeOfCurrentFlash, Vehicle currentVehicle,
                                                int currentTotalFee) {
        resetFeeIfNewDay(vehicles, timeOfCurrentFlash, timeOfLastCameraFlash, currentVehicle);
        
        boolean zeroFee = checkIfZeroFee(timeOfCurrentFlash, currentTotalFee);

        return zeroFee;
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

    private void resetFeeIfNewDay(Vehicle[] vehicles, LocalDateTime timeOfCurrentFlash, LocalDateTime timeOfLastCameraFlash, Vehicle currentVehicle) {
        int dayOfCurrentFlash = timeOfCurrentFlash.getDayOfMonth();
        if (timeOfLastCameraFlash != null) {
            int dayOfLastFlash = timeOfLastCameraFlash.getDayOfMonth();
            resetCurrentTotalFeeForNewday(vehicles, dayOfCurrentFlash, dayOfLastFlash, currentVehicle);
        }
    }

    private int calculateHours(Vehicle currentVehicle, LocalDateTime timeOfLastCameraFlash, LocalDateTime timeOfCurrentFlash, int lastFee, int fee) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int hour = timeOfCurrentFlash.getHour();

        fee = getFeeDependingOnHoursAndVehicleType(hour, vehicleType);

        fee = calculateFeeWithinSameHour(currentVehicle, timeOfLastCameraFlash, timeOfCurrentFlash, lastFee, fee);

        return fee;
    }

    private int calculateFeeWithinSameHour(Vehicle currentVehicle, LocalDateTime timeOfLastCameraFlash, LocalDateTime timeOfCurrentFlash,
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

    private void updateCurrentVehicle(Vehicle currentVehicle, int currentTotalFee, int fee, LocalDateTime timeOfCurrentFlash) {
        currentVehicle.setCurrentTotalFee(currentTotalFee + fee);
        currentVehicle.setLastFee(fee);
        currentVehicle.setTimeOfLastCameraFlash(timeOfCurrentFlash);
    }

    private void resetCurrentTotalFeeForNewday(Vehicle[] vehicles, int dayOfMonth, int dayOfMonth2, Vehicle currentVehicle) {
        if (dayOfMonth > dayOfMonth2) {
            for (Vehicle vehicle : vehicles)
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
