package Main;

import java.time.LocalDateTime;
import java.util.Random;

import Vehicle.Car;
import Vehicle.Motorcycle;
import Vehicle.Vehicle;

public class MockData {
    public Vehicle[] getListOfVehicles() {
        Vehicle[] vehicles = {
            new Car("678 iao"),
            new Car("272 ztc"),
            new Car("479 hdy"),
            new Motorcycle("442 lkf"),
            new Motorcycle("556 igv"),
            new Motorcycle("986 ruf")
        };

        return vehicles;
    }

    public LocalDateTime[] getFlashes(int numberOfFLashes) {
        LocalDateTime[] cameraFlashes = new LocalDateTime[numberOfFLashes];
       
        cameraFlashes = getRandomizedCameraFlashes(cameraFlashes);

        return cameraFlashes;
    }

    private LocalDateTime[] getRandomizedCameraFlashes(LocalDateTime[] cameraFlashes) {
        int day = 1;
        int randomHour;
        Random random = new Random();

        for (int i = 0; i < cameraFlashes.length; i++) {
            randomHour = random.nextInt(23);
            cameraFlashes[i] = LocalDateTime.of(2021, 11, day, randomHour, 31, 00);
            day++;
        }
        return cameraFlashes;
    }
}
