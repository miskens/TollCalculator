package Vehicle;

public class Motorcycle extends Vehicle implements ITollable {
    public Motorcycle(String regNr) {
        super(regNr);
        this.regNr = regNr;
    }
}
