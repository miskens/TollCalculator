package Main;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle[] vehicles, Vehicle currentVehicle, int lastFee, int currentTotalFee, LocalDateTime timeOfCurrentFlash) {
        int fee = 0;
        LocalDateTime timeOfLastCameraFlash = currentVehicle.getTimeOfLastCameraFlash();
        DayOfWeek day = timeOfCurrentFlash.getDayOfWeek();

        if (timeOfLastCameraFlash != null) {
            resetCurrentTotalFeeForNewday(vehicles, timeOfCurrentFlash.getDayOfMonth(), timeOfLastCameraFlash.getDayOfMonth(), currentVehicle);
        }

        if (currentTotalFee >= 60 || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
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
