package Vehicle;

public class Vehicle {
    protected int currentFee;
    protected int lastFee;
    protected String regNr;

    public Vehicle(String regNr) {
        this.currentFee = 0;
        this.lastFee = 0;
        this.regNr = regNr;
    }

    public int getCurrentFee() {
        return this.currentFee;
    }

    public void setCurrentFee(int fee) {
        this.currentFee = fee;
    }

    public int getLastFee() {
        return this.lastFee;
    }

    public void setLastFee(int fee) {
        this.lastFee = fee;
    }

    public String getRegNr() {
        return this.regNr;
    }
}
