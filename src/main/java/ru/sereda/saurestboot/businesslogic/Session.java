package ru.sereda.saurestboot.businesslogic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static java.lang.Math.abs;

public class Session implements Comparable<Session>{
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long duratonHours;
    private final long duratonMinutes;
    private final byte carrierState;

    private String deviceId;

    public Session(LocalDateTime startTime, LocalDateTime endTime, long duratonMinutes, byte carrierState) {
        this.startTime = startTime;
        this.endTime = endTime;
        duratonMinutes = abs(duratonMinutes);
        this.duratonHours = duratonMinutes/60;
        this.duratonMinutes = duratonMinutes-(duratonHours*60);
        this.carrierState = carrierState;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getDuration(){
        StringBuilder duration = new StringBuilder();
        duration.append(duratonHours);
        duration.append(":");
        if (duratonMinutes<10)
            duration.append("0");
        duration.append(duratonMinutes);
        return duration.toString();
    }

    public byte getCarrierState() {
        return carrierState;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int compareTo(Session o) {
        return (int) ChronoUnit.MINUTES.between(this.startTime,o.startTime);
    }
}
