package Main;

import java.time.LocalDateTime;

import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle currentVehicle, LocalDateTime timeOfCurrentFlash) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int hour = timeOfCurrentFlash.getHour();

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
