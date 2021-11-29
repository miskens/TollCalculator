package Main;

import Vehicle.Vehicle;
import java.util.Random;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) throws Exception {
        TollCalculator tc = new TollCalculator();
        MockData mock = new MockData();
        Vehicle[] vehicles = mock.getListOfVehicles();
        LocalDateTime[] cameraFlashes = mock.getCameraFlashes(20);
        int fee;

        for(LocalDateTime flash : cameraFlashes){
            Random random = new Random();
            Vehicle vehicle = vehicles[random.nextInt(vehicles.length)];
            fee = tc.getTollFee(vehicle, flash);
            System.out.println(vehicle.getClass().getSimpleName() + " with regNr: " + vehicle.getRegNr() + " at hour: " + flash.getHour());
            System.out.println(fee);
        }
    }
}
