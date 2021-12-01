package Test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import Main.TollCalculator;
import Vehicle.Car;
import Vehicle.Motorcycle;
import Vehicle.Vehicle;

public class TollCalculatorTest {

    @Test
    public void testFeesVariesBetween8and18() {
        // setup
        Motorcycle mc = new Motorcycle("");
        Car car = new Car("");
        TollCalculator tc = new TollCalculator();
        int mcFee = tc.getTollFee(mc, 0, 0, LocalDateTime.of(2021, 11, 30, 9, 10, 0));
        int carFee = tc.getTollFee(car, 0, 0, LocalDateTime.of(2021, 11, 30, 9, 10, 0));
        int rushHourFee = tc.getTollFee(mc, 0, 0, LocalDateTime.of(2021, 11, 30, 8, 10, 0));

        // actual
        int[] actual = { mcFee, carFee, rushHourFee };

        // Expected
        int[] expected = { 8, 11, 18 };

        // Assert
        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void testThereAreVehiclesTypesOfCarAndMotorcycles() {
        // setup
        Vehicle mc = new Motorcycle("");
        Vehicle car = new Car("");
        String mcType = mc.getClass().getSimpleName();
        String carType = car.getClass().getSimpleName();

        // Actual
        String[] actuals = { carType, mcType };

        // Exspected
        String[] expected = { "Car", "Motorcycle" };

        // Assert
        Assert.assertArrayEquals(expected, actuals);
    }

}
