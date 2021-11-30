package Main;

import java.time.LocalDateTime;
import java.util.Random;

import Vehicle.Car;
import Vehicle.Motorcycle;
import Vehicle.Vehicle;

public class MockData {
    public Vehicle[] getListOfVehicles() {
        Car car1 = new Car("678 iao");
        Car car2 = new Car("272 ztc");
        Car car3 = new Car("479 hdy");
        Motorcycle mc1 = new Motorcycle("442 lkf");
        Motorcycle mc2 = new Motorcycle("556 igv");
        Motorcycle mc3 = new Motorcycle("986 ruf");
        
        Vehicle[] vehicles = new Vehicle[6];
        vehicles[0] = car1;
        vehicles[1] = car2;
        vehicles[2] = car3;
        vehicles[3] = mc1;
        vehicles[4] = mc2;
        vehicles[5] = mc3;
        
        return vehicles;
    }

    public LocalDateTime[] getRandomizedCameraFlashes(int numberOfFLashes) {
        LocalDateTime[] cameraFlashes = new LocalDateTime[numberOfFLashes];
        Random random = new Random();
        int randomHour;
        int randomDay;

        for (int i = 0; i < cameraFlashes.length; i++) {
            randomHour = random.nextInt(23);
            randomDay = random.nextInt(30) +1;
            cameraFlashes[i] = LocalDateTime.of(2021, 11, randomDay, randomHour, 31, 00);
        }

        return cameraFlashes;
    }
}
