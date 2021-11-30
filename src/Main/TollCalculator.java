package Main;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle currentVehicle, int lastFee, int currentFee, LocalDateTime timeOfCurrentFlash) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int fee;
        int hour = timeOfCurrentFlash.getHour();
        LocalDateTime timeOfLastCameraFlash;
        DayOfWeek day = timeOfCurrentFlash.getDayOfWeek();

        if(currentFee >= 60 || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return 0;
        }

        fee = getFeeDependingOnHoursAndVehicleType(hour, vehicleType);

        if (currentVehicle.getTimeOfLastCameraFlash() != null) {
            timeOfLastCameraFlash = currentVehicle.getTimeOfLastCameraFlash();
            long minutesSinceLastFlash = timeOfLastCameraFlash.until(timeOfCurrentFlash, ChronoUnit.MINUTES);

            if(minutesSinceLastFlash <= 60) {
                if(lastFee > fee) {
                    return lastFee - fee;
                }
                else if (fee > lastFee) {
                    currentVehicle.setLastFee(fee);
                    return fee - lastFee;
                }
                else {
                    return 0;
                }
            }
        }

        if (currentFee + fee >= 60) {
            currentVehicle.setCurrentFee(60);
            return 60 - currentFee;
        }

        currentVehicle.setCurrentFee(currentFee + fee);
        currentVehicle.setLastFee(fee);
        currentVehicle.setTimeOfLastCameraFlash(timeOfCurrentFlash);
        return fee;
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
