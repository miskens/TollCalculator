package Test;

public class TestMain {
    public static void main(String[] args) {
        TollCalculatorTest tollcalcTest = new TollCalculatorTest();
        tollcalcTest.testFeesVariesBetween8and18();
        tollcalcTest.testThereAreVehiclesTypesOfCarAndMotorcycles();
        tollcalcTest.testRushHourFeesAppliesForBothHours8And16();
        tollcalcTest.testFeeCanNeverBeHigherThan60();
        tollcalcTest.testOnlyHighestFeeAppliedWithinSameHourWhenLastFeeWasLowerThanCurrentFee();
        tollcalcTest.testOnlyHighestFeeAppliedWithinSameHourWhenCurrentFeeWasLowerThanCLastFee();
        tollcalcTest.testNoFeeOnSaturdayOrSunday();
    }
}
