package Main;

import java.time.LocalDateTime;

import Vehicle.Vehicle;

public class TollCalculator {

    public int getTollFee(Vehicle currentVehicle, LocalDateTime timeOfCurrentFlash) {
        String vehicleType = currentVehicle.getClass().getSimpleName();
        int hour = timeOfCurrentFlash.getHour();

        int fee = getFeeDependingOnHours(hour, vehicleType);

        return fee;
    }

    private int getFeeDependingOnHours(int hour, String vehicleType) {
        int fee = 0;

        switch (hour){
            case 8:
            case 16:
                fee = 18;
                break;
            default:
            switch(vehicleType){
                case "Car":
                    fee = 11;
                    break;
                case "Motorcycle":
                    fee = 8;
                    break;
            }
        }

        return fee;
    }  

}
