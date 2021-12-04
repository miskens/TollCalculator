package Vehicle;

public class Car extends Vehicle implements ITollable {
    public Car(String regNr) {
        super(regNr);
        this.regNr = regNr;
    }
}
