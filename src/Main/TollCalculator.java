package Main;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle[] vehicles, Vehicle currentVehicle, int lastFee, int currentTotalFee, LocalDateTime timeOfCurrentFlash) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int fee;
        int hour = timeOfCurrentFlash.getHour();
        LocalDateTime timeOfLastCameraFlash = currentVehicle.getTimeOfLastCameraFlash();
        DayOfWeek day = timeOfCurrentFlash.getDayOfWeek();

        if (timeOfLastCameraFlash != null) {
            resetcurrentTotalFeeIfNewday(vehicles, timeOfCurrentFlash.getDayOfMonth(), timeOfLastCameraFlash.getDayOfMonth(), currentVehicle);
        }

        if (currentTotalFee >= 60 || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return 0;
        }

        fee = getFeeDependingOnHoursAndVehicleType(hour, vehicleType);

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

        if (currentTotalFee + fee >= 60) {
            currentVehicle.setCurrentTotalFee(60);
            return 60 - currentTotalFee;
        }

        currentVehicle.setCurrentTotalFee(currentTotalFee + fee);
        currentVehicle.setLastFee(fee);
        currentVehicle.setTimeOfLastCameraFlash(timeOfCurrentFlash);
        return fee;
    }

    private void resetcurrentTotalFeeIfNewday(Vehicle[] vehicles, int dayOfMonth, int dayOfMonth2, Vehicle currentVehicle) {
        // If day of this flash > day of last flash:
        if (dayOfMonth > dayOfMonth2) {
            //      Code to send currentTotal for invoicing or whatever since fees shouldn´t be transferred to next day
            //      Makes sure the calculation for max 60kr/day can be done using dayOfMonth above

            //      set currentTotal to 0
            
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
