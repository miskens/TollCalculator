package Interfaces;

import java.time.LocalDateTime;

public interface ITollable {
    public int getCurrentTotalFee();

    public void setCurrentTotalFee(int fee);

    public int getLastFee();

    public void setLastFee(int fee);

    public LocalDateTime getTimeOfLastCameraFlash();

    public void setTimeOfLastCameraFlash(LocalDateTime timeofLastCameraFlash);
}
