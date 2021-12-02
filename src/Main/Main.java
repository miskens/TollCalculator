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
            printCameraFlashInfo(rowNr, vehicle, flash, currentFee, fee); //Change name to printCurrent...
            rowNr++;
        }
    }

    private static void printInfoAboutVehicles(Vehicle[] vehicles) {
        System.out.println("Number of Vehicles used in this program: " + Vehicle.getNrOfVehicles());
        System.out.println("These are the vehicles:\n");

        String vehicleSubclass;
        for (Vehicle v : vehicles) {
            vehicleSubclass = v.getClass().getSimpleName();
            System.out.println(vehicleSubclass + " with regNr: " + v.getRegNr());
        }
        System.out.println();
    }

    private static void printCameraFlashInfo(int rowNr, Vehicle vehicle, LocalDateTime flash, int currentFee, int fee) {

        String vehicleSubclass = vehicle.getClass().getSimpleName();
        System.out.println(vehicleSubclass + " with regNr: '" + vehicle.getRegNr() + ":");

        if (currentFee >= 60) {
            System.out.println(vehicle.getRegNr() + " has reached max fee, toll: " + fee + " kr.");
        }
        else if(flash.getDayOfWeek() == DayOfWeek.SATURDAY || flash.getDayOfWeek() == DayOfWeek.SUNDAY) {
            System.out.println("Weekend! Toll is " + fee + " kr.");
        }
        else {
            System.out
            .println("Passed at hour " + flash.getHour() + ": " + fee + " kr.");
        }
        System.out.println("Total Fee of Vehicle above: " + vehicle.getCurrentFee() + " kr.\n");
    }
}
