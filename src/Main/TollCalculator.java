package Main;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle currentVehicle, int currentFee, LocalDateTime timeOfCurrentFlash) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int hour = timeOfCurrentFlash.getHour();
        DayOfWeek day = timeOfCurrentFlash.getDayOfWeek();

        if(currentFee >= 60 || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return currentFee;
        }

        int fee = getFeeDependingOnHoursAndVehicleType(hour, vehicleType);

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
