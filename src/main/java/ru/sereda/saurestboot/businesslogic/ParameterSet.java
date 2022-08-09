package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ParameterSet extends ReducedParameterSet{

    private final int rsl;
    private final int temperature;
    private final float txPowerLevelIncrease;
    private final float ber;
    private final String unitAlarm;
    private final String txAlarm;
    private final String rxAlarm;
    private final String oduAlarm;

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
        parametersMap.put("timestampWotz",super.getTimestampWotz());
        parametersMap.put("ebNo",super.getEbNo());
        parametersMap.put("ebNoRemote",super.getEbNoRemote());
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
                ", ebNo=" + super.getEbNo() +
                ", ebNoRemote=" + super.getEbNoRemote() +
                ", timestampWotz=" + super.getTimestampWotz()+
                '}';
    }

    public int getRsl() {
        return rsl;
    }

    public int getTemperature() {
        return temperature;
    }

    public float getTxPowerLevelIncrease() {
        return txPowerLevelIncrease;
    }

    public float getBer() {
        return ber;
    }

    public String getUnitAlarm() {
        return unitAlarm;
    }

    public String getTxAlarm() {
        return txAlarm;
    }

    public String getRxAlarm() {
        return rxAlarm;
    }

    public String getOduAlarm() {
        return oduAlarm;
    }
}
