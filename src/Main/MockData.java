package Main;

import java.time.LocalDateTime;
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

    public LocalDateTime[] getLeFlashes() {
        LocalDateTime[] cameraFlashes = {
            LocalDateTime.of(2021, 11, 1, 7, 30, 00),
            LocalDateTime.of(2021, 11, 1, 9, 30, 00),
            LocalDateTime.of(2021, 11, 1, 12, 30, 00),
            LocalDateTime.of(2021, 11, 1, 14, 30, 00),
            LocalDateTime.of(2021, 11, 1, 15, 30, 00),
            LocalDateTime.of(2021, 11, 1, 16, 30, 00),
            LocalDateTime.of(2021, 11, 1, 17, 30, 00),

            LocalDateTime.of(2021, 11, 2, 7, 30, 00),
            LocalDateTime.of(2021, 11, 2, 8, 10, 00),
            LocalDateTime.of(2021, 11, 2, 9, 5, 00),
            LocalDateTime.of(2021, 11, 2, 10, 5, 00),
            LocalDateTime.of(2021, 11, 2, 11, 5, 00),
            LocalDateTime.of(2021, 11, 2, 12, 15, 00),

            LocalDateTime.of(2021, 11, 3, 6, 30, 00),
            LocalDateTime.of(2021, 11, 3, 7, 20, 00),
            LocalDateTime.of(2021, 11, 3, 8, 10, 00),
            LocalDateTime.of(2021, 11, 3, 9, 40, 00),
            LocalDateTime.of(2021, 11, 3, 15, 40, 00),
            LocalDateTime.of(2021, 11, 3, 16, 10, 00),
            LocalDateTime.of(2021, 11, 3, 16, 20, 00),
        };
        return cameraFlashes;
    }
}
