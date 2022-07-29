package ru.sereda.saurestboot.businesslogic;

public class LastPair {
    private String modemId;
    private String lastUpTime;

    public String getModemId() {
        return modemId;
    }

    public void setModemId(String modemId) {
        this.modemId = modemId;
    }

    public String getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(String lastUpTime) {
        this.lastUpTime = lastUpTime;
    }

    public LastPair(String modemId, String lastUpTime) {
        this.modemId = modemId;
        this.lastUpTime = lastUpTime;
    }
}
