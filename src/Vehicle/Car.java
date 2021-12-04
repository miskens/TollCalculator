package Vehicle;

import Interfaces.ITollable;

public class Car extends Vehicle implements ITollable {
    public Car(String regNr) {
        super(regNr);
        this.regNr = regNr;
    }
}
