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
        System.out.println("testFeesVariesBetween8and18 OK!");
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
        System.out.println("testThereAreVehiclesTypesOfCarAndMotorcycles OK!");
    }

    

    @Test
    public void testRushHourFeesAppliesForBothHours8And16() {
        // Setup
        Car car = new Car("");
        TollCalculator tc = new TollCalculator();
        int fee8 = tc.getTollFee(car, 0, 0, LocalDateTime.of(2021, 11,30,8,10,0));
        int fee16 = tc.getTollFee(car, 0, 0, LocalDateTime.of(2021, 11,30,16,10,0));

        // Actual
        int[] actual = {fee8, fee16};

        // Expected
        int[] expected = {18, 18};

        //Assert
        Assert.assertArrayEquals(actual, expected);
        System.out.println("testRushHourFeesAppliesForBothHours8And16 OK!");
    }

    @Test
    public void testFeeCanNeverBeHigherThan60() {
        // Setup
        int feebefore = 58;
        Car car = new Car("");
        TollCalculator tc = new TollCalculator();
        int fee = tc.getTollFee(car, 0, feebefore, LocalDateTime.of(2021, 11,30,8,10,0));
        int maxFee = feebefore + fee;

        // Actual
        int actual = maxFee;

        // Expected
        int expected = 60;

        // Assert
        Assert.assertEquals(expected, actual);
        System.out.println("testFeeCanNeverBeHigherThan60 OK!");
    }

    @Test
    public void testOnlyHighestFeeAppliedWithinSameHourWhenLastFeeWasLowerThanCurrentFee() {
        // Setup
        Car car = new Car("");
        TollCalculator tc = new TollCalculator();
        int fee = tc.getTollFee(car, 0, 0, LocalDateTime.of(2021, 11,30,7,50,0)); // To set time of last camera flash on car
        int fee2 = tc.getTollFee(car, 11, 0, LocalDateTime.of(2021, 11,30,8,5,0));
        int maxFee = fee + fee2;

        // Actual
        int actual = maxFee;

        // Expected
        int expected = 18;

        // Assert
        Assert.assertEquals(expected, actual);
        System.out.println("testOnlyHighestFeeAppliedWithinSameHourWhenLastFeeWasLowerThanCurrentFee OK!");
    }

    @Test
    public void testOnlyHighestFeeAppliedWithinSameHourWhenCurrentFeeWasLowerThanCLastFee() {
        // Setup
        Car car = new Car("");
        TollCalculator tc = new TollCalculator();
        int fee = tc.getTollFee(car, 0, 0, LocalDateTime.of(2021, 11,30,8,50,0)); // To set time of last camera flash on car
        int fee2 = tc.getTollFee(car, 11, 0, LocalDateTime.of(2021, 11,30,9,5,0));
        int maxFee = fee + fee2;

        // Actual
        int actual = maxFee;

        // Expected
        int expected = 18;

        // Assert
        Assert.assertEquals(expected, actual);
        System.out.println("testOnlyHighestFeeAppliedWithinSameHourWhenCurrentFeeWasLowerThanCLastFee OK!");
    }
}
