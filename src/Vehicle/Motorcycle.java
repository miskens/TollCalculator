package Vehicle;

import java.time.LocalDateTime;

import Interfaces.ITollable;

public class Motorcycle extends Vehicle implements ITollable {
    
    public Motorcycle(String regNr) {
        super(regNr);
        this.regNr = regNr;
    }

    public String getRegNr() {
        return this.regNr;
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

    public LocalDateTime getTimeOfLastCameraFlash() {
        return this.timeOfLastCameraFlash;
    }

    public void setTimeOfLastCameraFlash(LocalDateTime timeofLastCameraFlash) {
        this.timeOfLastCameraFlash = timeofLastCameraFlash;
    }
}
