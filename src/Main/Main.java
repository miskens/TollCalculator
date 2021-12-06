package Main;

import Vehicle.Vehicle;
import java.util.Random;

import Interfaces.ITollable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        MockData mock = new MockData();
        ITollable[] vehicles = mock.getListOfVehicles();
        LocalDateTime[] cameraFlashes = mock.getFlashes();

        printInfoAboutVehicles(vehicles);

        printAllCameraFlashesInfo(cameraFlashes, vehicles);
    }

    private static void printInfoAboutVehicles(ITollable[] vehicles) {
        System.out.println("Number of Vehicles used in this program: " + Vehicle.getNrOfVehicles());
        System.out.println("These are the vehicles:\n");

        String vehicleSubclass;
        for (ITollable v : vehicles) {
            vehicleSubclass = v.getClass().getSimpleName();
            System.out.println(vehicleSubclass + " with regNr: " + v.getRegNr());
        }
        System.out.println();
    }

    private static void printAllCameraFlashesInfo(LocalDateTime[] cameraFlashes, ITollable[] vehicles) {
        TollCalculator tc = new TollCalculator();
        Random random = new Random();
        int currentTotalFee = 0;
        int lastFee = 0;
        int fee = 0;
        int rowNr = 1;
        
        for (LocalDateTime flash : cameraFlashes) {
            ITollable randomVehicle = vehicles[random.nextInt(vehicles.length)];

            currentTotalFee = randomVehicle.getCurrentTotalFee();
            lastFee = randomVehicle.getLastFee();

            fee = tc.getTollFee(vehicles, randomVehicle, lastFee, currentTotalFee, flash);

            System.out.println("Camera flash nr: " + rowNr);

            printCurrentCameraFlashInfo(rowNr, randomVehicle, flash, currentTotalFee, fee);
            rowNr++;
        }
    }

    private static void printCurrentCameraFlashInfo(int rowNr, ITollable vehicle, LocalDateTime flash, int currentTotalFee,
            int fee) {
        String vehicleSubclass = vehicle.getClass().getSimpleName();
        System.out.println(vehicleSubclass + " with regNr: '" + vehicle.getRegNr() + ":");

        if (currentTotalFee >= 60) {
            System.out.println(vehicle.getRegNr() + " has reached max fee, toll: " + fee + " kr.");
        } else if (flash.getDayOfWeek() == DayOfWeek.SATURDAY || flash.getDayOfWeek() == DayOfWeek.SUNDAY) {
            System.out.println("Weekend! Toll is " + fee + " kr.");
        } else {
            System.out.println("Passed on a " + flash.getDayOfWeek() + " at hour " + flash.getHour() + ": " + fee + " kr.");
        }
        System.out.println("Total Fee of Vehicle above today: " + vehicle.getCurrentTotalFee() + " kr.\n");
    }
}
