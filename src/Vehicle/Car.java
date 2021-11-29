package Vehicle;

public class Car extends Vehicle {

    public Car(String regNr) {
        super(regNr);
        System.out.println("Car with regNr: " + regNr +" has been instantiated");
    }
}
