package Test;

public class TestMain {
    public static void main(String[] args) {
        TollCalculatorTest tct = new TollCalculatorTest();

        tct.testFeesVariesBetween8and18();
        tct.testThereAreVehiclesTypesOfCarAndMotorcycles();
        tct.testRushHourFeesAppliesForBothHours8And16();
        tct.testFeeCanNeverBeHigherThan60();
        tct.testOnlyHighestFeeAppliedWithinSameHourWhenLastFeeWasLowerThanCurrentFee();
        tct.testOnlyHighestFeeAppliedWithinSameHourWhenCurrentFeeWasLowerThanCLastFee();
    }
}
