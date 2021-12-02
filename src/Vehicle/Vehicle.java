package Vehicle;

import java.time.LocalDateTime;

public class Vehicle {
    protected LocalDateTime timeOfLastCameraFlash;
    protected int currentTotalFee;
    protected int lastFee;
    protected String regNr;
    private static int nrOfVehicles = 0;

    public Vehicle(String regNr) {
        this.currentTotalFee = 0;
        this.lastFee = 0;
        this.regNr = regNr;
        nrOfVehicles++;
    }

    public int getCurrentTotalFee() {
        return this.currentTotalFee;
    }

    public void setCurrentTotalFee(int fee) {
        this.currentTotalFee = fee;
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

    public LocalDateTime getTimeOfLastCameraFlash() {
        return this.timeOfLastCameraFlash;
    }

    public void setTimeOfLastCameraFlash(LocalDateTime timeofLastCameraFlash) {
        this.timeOfLastCameraFlash = timeofLastCameraFlash;
    }

    public static int getNrOfVehicles() {
        return nrOfVehicles;
    }
}
