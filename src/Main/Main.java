package Main;

import Vehicle.Vehicle;
import java.util.Random;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        TollCalculator tc = new TollCalculator();
        MockData mock = new MockData();
        Vehicle[] vehicles = mock.getListOfVehicles();
        LocalDateTime[] cameraFlashes = mock.getRandomizedCameraFlashes(28);
        int currentFee = 0;
        int lastFee = 0;
        int fee;

        for (LocalDateTime flash : cameraFlashes) {
            Random random = new Random();
            Vehicle vehicle = vehicles[random.nextInt(vehicles.length)];
            currentFee = vehicle.getCurrentFee();
            lastFee = vehicle.getLastFee();
            fee = tc.getTollFee(vehicle, lastFee, currentFee, flash);
            if (currentFee >= 60) {
                System.out.println(vehicle.getRegNr() + " has reached max fee, toll free!");
            }
            else if(flash.getDayOfWeek() == DayOfWeek.SATURDAY || flash.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println("Weekend! Toll free drive!");
            }
            else {
                System.out
                .println(vehicle.getClass().getSimpleName() + " with regNr: '" + vehicle.getRegNr() + "' at hour " +
                        flash.getHour() + ": " + fee + " kr.");
            }
            System.out.println("Total Fee of Vehicle above: " + vehicle.getCurrentFee());
        }
    }
}
