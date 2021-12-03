package Main;

import Vehicle.Vehicle;
import java.util.Random;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        MockData mock = new MockData();
        Vehicle[] vehicles = mock.getListOfVehicles();
        LocalDateTime[] cameraFlashes = mock.getLeFlashes();
        int currentTotalFee = 0;
        int lastFee = 0;
        int fee = 0;

        printInfoAboutVehicles(vehicles);
        printAllCameraFlashesInfo(cameraFlashes, vehicles, currentTotalFee, lastFee, fee);
    }

    private static void printAllCameraFlashesInfo(LocalDateTime[] cameraFlashes, Vehicle[] vehicles, int currentTotalFee,
                                                            int lastFee, int fee) {
        TollCalculator tc = new TollCalculator();
        int rowNr = 1;
        for (LocalDateTime flash : cameraFlashes) {
            Random random = new Random();
            Vehicle vehicle = vehicles[random.nextInt(vehicles.length)];
            currentTotalFee = vehicle.getCurrentTotalFee();
            lastFee = vehicle.getLastFee();
            fee = tc.getTollFee(vehicles, vehicle, lastFee, currentTotalFee, flash);

            System.out.println("Camera flash nr: " + rowNr);
            printCurrentCamerFlashInfo(rowNr, vehicle, flash, currentTotalFee, fee);
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

    private static void printCurrentCamerFlashInfo(int rowNr, Vehicle vehicle, LocalDateTime flash, int currentTotalFee,
            int fee) {

        String vehicleSubclass = vehicle.getClass().getSimpleName();
        System.out.println(vehicleSubclass + " with regNr: '" + vehicle.getRegNr() + ":");

        if (currentTotalFee >= 60) {
            System.out.println(vehicle.getRegNr() + " has reached max fee, toll: " + fee + " kr.");
        } else if (flash.getDayOfWeek() == DayOfWeek.SATURDAY || flash.getDayOfWeek() == DayOfWeek.SUNDAY) {
            System.out.println("Weekend! Toll is " + fee + " kr.");
        } else {
            System.out
                    .println("Passed on a " + flash.getDayOfWeek() + " at hour " + flash.getHour() + ": " + fee + " kr.");
        }
        System.out.println("Total Fee of Vehicle above today: " + vehicle.getCurrentTotalFee() + " kr.\n");
    }
}
