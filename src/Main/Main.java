package Main;

import Vehicle.Car;
import Vehicle.Motorcycle;
import java.util.Random;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        TollCalculator tc = new TollCalculator();
        Car car = new Car("123 abc");
        Motorcycle motorcycle = new Motorcycle("456 def");

        LocalDateTime[] cameraFlashes = getCameraFlashes(20);
        for (LocalDateTime test : cameraFlashes) {
            System.out.println(test);
        }

        // int fee = tc.getTollFee(car, timeOfCurrentFlash2);
        // int fee2 = tc.getTollFee(motorcycle, timeOfCurrentFlash2);

        // System.out.println(fee); // prints out the fee when we are done calculating
        // System.out.println(fee2); // prints out the fee when we are done calculating
    }

    private static LocalDateTime[] getCameraFlashes(int numberOfFLashes) {
        LocalDateTime[] cameraFlashes = new LocalDateTime[numberOfFLashes];
        int randomHour;
        Random random = new Random();
        for (int i = 0; i < cameraFlashes.length; i++) {
            randomHour = random.nextInt(23);
            cameraFlashes[i] = LocalDateTime.of(2021, 11, 30, randomHour, 31, 00);

        }
        return cameraFlashes;
    }
}
