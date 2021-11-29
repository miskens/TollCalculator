package Main;

import java.time.LocalDateTime;
import java.util.Random;

import Vehicle.Car;
import Vehicle.Motorcycle;
import Vehicle.Vehicle;

public class MockData {
    public Vehicle[] getListOfVehicles() {
        Vehicle[] vehicles = new Vehicle[6];
        Car car1 = new Car("678 idc");
        Car car2 = new Car("678 idc");
        Car car3 = new Car("678 idc");
        Motorcycle mc1 = new Motorcycle("442 def");
        Motorcycle mc2 = new Motorcycle("456 def");
        Motorcycle mc3 = new Motorcycle("486 def");

        vehicles[0] = car1;
        vehicles[1] = car2;
        vehicles[2] = car3;
        vehicles[3] = mc1;
        vehicles[4] = mc2;
        vehicles[5] = mc3;
        
        return vehicles;
    }

    public LocalDateTime[] getCameraFlashes(int numberOfFLashes) {
        LocalDateTime[] cameraFlashes = new LocalDateTime[numberOfFLashes];
        int randomHour;
        int randomDay;
        Random random = new Random();
        
        for (int i = 0; i < cameraFlashes.length; i++) {
            randomHour = random.nextInt(23);
            randomDay = random.nextInt(30) +1;
            cameraFlashes[i] = LocalDateTime.of(2021, 11, randomDay, randomHour, 31, 00);

        }

        return cameraFlashes;
    }
}
