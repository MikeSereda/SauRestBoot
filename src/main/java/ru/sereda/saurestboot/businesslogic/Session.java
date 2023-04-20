package ru.sereda.saurestboot.businesslogic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Math.abs;

public class Session implements Comparable<Session>{
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long duratonHours;
    private final long duratonMinutes;
    private final byte carrierState;

    private String deviceId;
    private Set<LocalDateTime> concatedStartTimes = new TreeSet<>();
    private int sessionType;
    private String masterStation;
    private String slaveStation = "Unsaved Session";
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

    public Set<LocalDateTime> getConcatedStartTimes() {
        return concatedStartTimes;
    }

    public void setConcatedStartTimes(Set<LocalDateTime> concatedStartTimes) {
        this.concatedStartTimes = concatedStartTimes;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public String getMasterStation() {
        return masterStation;
    }

    public void setMasterStation(String masterStation) {
        this.masterStation = masterStation;
    }

    public String getSlaveStation() {
        return slaveStation;
    }

    public void setSlaveStation(String slaveStation) {
        this.slaveStation = slaveStation;
    }

    public boolean containsThisTime(Session checkedSession){
        return concatedStartTimes.contains(checkedSession.startTime);
    }
}
