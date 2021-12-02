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
        LocalDateTime[] cameraFlashes = mock.getFlashes(29);
        int currentFee = 0;
        int lastFee = 0;
        int fee;

        printInfoAboutVehicles(vehicles);

        int rowNr = 1;
        for (LocalDateTime flash : cameraFlashes) {
            Random random = new Random();
            Vehicle vehicle = vehicles[random.nextInt(vehicles.length)];
            currentFee = vehicle.getCurrentFee();
            lastFee = vehicle.getLastFee();
            fee = tc.getTollFee(vehicle, lastFee, currentFee, flash);

            System.out.println("Vehicle nr: " + rowNr);
            printCameraFlashInfo(rowNr, vehicle, flash, currentFee, fee);
            rowNr++;
        }
    }

    private static void printInfoAboutVehicles(Vehicle[] vehicles) {
        System.out.println("Number of Vehicles used in this program: " + Vehicle.getNrOfVehicles());
        System.out.println("These are the vehicles:\n");

        for (Vehicle v : vehicles) {
            System.out.println(v.getClass().getSimpleName() + " with regNr: " + v.getRegNr());
        }
        System.out.println();
    }

    private static void printCameraFlashInfo(int rowNr, Vehicle vehicle, LocalDateTime flash, int currentFee, int fee) {

        
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
        System.out.println("Total Fee of Vehicle above: " + vehicle.getCurrentFee() + "\n");
    }
}
