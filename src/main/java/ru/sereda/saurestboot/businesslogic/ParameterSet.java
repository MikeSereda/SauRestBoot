package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ParameterSet extends ReducedParameterSet{

    private int rsl;
    private int temperature;
    private float txPowerLevelIncrease;
    private float ber;
    private String unitAlarm;
    private String txAlarm;
    private String rxAlarm;
    private String oduAlarm;

    public ParameterSet() {
    }

    public ParameterSet(float ebNo,
                        float ebNoRemote,
                        LocalDateTime timestampWotz,
                        int rsl,
                        int temperature,
                        float txPowerLevelIncrease,
                        float ber,
                        String unitAlarm,
                        String txAlarm,
                        String rxAlarm,
                        String oduAlarm) {
        super(ebNo, ebNoRemote, timestampWotz);
        this.rsl = rsl;
        this.temperature = temperature;
        this.txPowerLevelIncrease = txPowerLevelIncrease;
        this.ber = ber;
        this.unitAlarm = unitAlarm;
        this.txAlarm = txAlarm;
        this.rxAlarm = rxAlarm;
        this.oduAlarm = oduAlarm;
    }

    @Override
    public HashMap<String, Object> getParametersMap() {
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("timestampWotz",super.timestampWotz);
        parametersMap.put("ebNo",super.ebNo);
        parametersMap.put("ebNoRemote",super.ebNoRemote);
        parametersMap.put("rsl",rsl);
        parametersMap.put("temperature",temperature);
        parametersMap.put("txPowerLevelIncrease",txPowerLevelIncrease);
        parametersMap.put("ber",ber);
        parametersMap.put("unitAlarm",unitAlarm);
        parametersMap.put("txAlarm",txAlarm);
        parametersMap.put("rxAlarm",rxAlarm);
        parametersMap.put("oduAlarm",oduAlarm);
        return parametersMap;
    }

    @Override
    public String toString() {
        return "ParameterSet{" +
                "rsl=" + rsl +
                ", temperature=" + temperature +
                ", txPowerLevelIncrease=" + txPowerLevelIncrease +
                ", ber=" + ber +
                ", unitAlarm='" + unitAlarm + '\'' +
                ", txAlarm='" + txAlarm + '\'' +
                ", rxAlarm='" + rxAlarm + '\'' +
                ", oduAlarm='" + oduAlarm + '\'' +
                ", ebNo=" + ebNo +
                ", ebNoRemote=" + ebNoRemote +
                ", timestampWotz=" + timestampWotz +
                '}';
    }

    public int getRsl() {
        return rsl;
    }

    public void setRsl(int rsl) {
        this.rsl = rsl;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public float getTxPowerLevelIncrease() {
        return txPowerLevelIncrease;
    }

    public void setTxPowerLevelIncrease(float txPowerLevelIncrease) {
        this.txPowerLevelIncrease = txPowerLevelIncrease;
    }

    public float getBer() {
        return ber;
    }

    public void setBer(float ber) {
        this.ber = ber;
    }

    public String getUnitAlarm() {
        return unitAlarm;
    }

    public void setUnitAlarm(String unitAlarm) {
        this.unitAlarm = unitAlarm;
    }

    public String getTxAlarm() {
        return txAlarm;
    }

    public void setTxAlarm(String txAlarm) {
        this.txAlarm = txAlarm;
    }

    public String getRxAlarm() {
        return rxAlarm;
    }

    public void setRxAlarm(String rxAlarm) {
        this.rxAlarm = rxAlarm;
    }

    public String getOduAlarm() {
        return oduAlarm;
    }

    public void setOduAlarm(String oduAlarm) {
        this.oduAlarm = oduAlarm;
    }
}
