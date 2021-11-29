package Main;

import Vehicle.Car;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        TollCalculator tc = new TollCalculator();
        Car car = new Car("123 abc");
        LocalDateTime timeOfCurrentFlash = LocalDateTime.of(2021, 11, 30, 8, 31, 00);

        int fee = tc.getTollFee(car, timeOfCurrentFlash);

        System.out.println(fee); // prints out the fee when we are done calculating
    }
}
