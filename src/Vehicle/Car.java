package Vehicle;

public class Car extends Vehicle {
    private String regNr;

    public Car(String regNr) {
        super(regNr);
        this.regNr = regNr;
        //System.out.println("Car with regNr: " + this.regNr +" has been instantiated");
    }
}
