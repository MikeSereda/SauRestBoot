package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedParameterSet extends ReducedParameterSet {
    private final float ber;
    private float txPowerLevel;
    private final float txPowerLevelIncrease;
    private final int rsl;
    private String faults;
    private final int temperature;
    private float frequencyOffset;
    private final boolean reachable;
    private int testMode;
    private String framingMode;
    private int delay;
    private String carrierState;
    private final String unitAlarm;
    private final String txAlarm;
    private final String rxAlarm;
    private final String oduAlarm;
    private final String askerVersion;
    private float ebNoDelta;
    private float ebNoRemoteDelta;

    public ExtendedParameterSet(float ebNo,
                                float ebNoRemote,
                                LocalDateTime timestampWotz,
                                int rsl,
                                int temperature,
                                float txPowerLevelIncrease,
                                float ber,
                                String unitAlarm,
                                String txAlarm,
                                String rxAlarm,
                                String oduAlarm,
                                boolean reachable,
//                                float frequencyOffset,
                                String askerVersion) {
        super(ebNo, ebNoRemote, timestampWotz);
        this.rsl = rsl;
        this.temperature = temperature;
        this.txPowerLevelIncrease = txPowerLevelIncrease;
        this.ber = ber;
        this.unitAlarm = unitAlarm;
        this.txAlarm = txAlarm;
        this.rxAlarm = rxAlarm;
        this.oduAlarm = oduAlarm;
        this.reachable = reachable;
        this.askerVersion = askerVersion;
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
        parametersMap.put("reachable",reachable);
        parametersMap.put("askerVersion",askerVersion);
        parametersMap.put("frequencyOffset",frequencyOffset);
        return parametersMap;
    }

    @Override
    public String toString() {
        return "ExtendedParameterSet{" +
                "rsl=" + rsl +
                ", temperature=" + temperature +
                ", txPowerLevelIncrease=" + txPowerLevelIncrease +
                ", ber=" + ber +
                ", unitAlarm='" + unitAlarm + '\'' +
                ", txAlarm='" + txAlarm + '\'' +
                ", rxAlarm='" + rxAlarm + '\'' +
                ", oduAlarm='" + oduAlarm + '\'' +
                ", reachable=" + reachable +
                ", askerVersion='" + askerVersion + '\'' +
                ", frequencyOffset=" + frequencyOffset +
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

    public boolean isReachable() {
        return reachable;
    }

    public String getAskerVersion() {
        return askerVersion;
    }

    static public List<ExtendedParameterSet> parameterSetWrapper(List<Map<String, Object>> mapList) {
        List<ExtendedParameterSet> parameterSetList = new ArrayList<>();
        for (Map<String,Object> map : mapList){
            parameterSetList.add(new ExtendedParameterSet(
                    (float) map.get("ebNo"),
                    (float) map.get("ebNoRemote"),
                    ((Timestamp)map.get("timestampWotz")).toLocalDateTime(),
                    (int) map.get("rsl"),
                    (int) map.get("temperature"),
                    (float) map.get("txPowerLevelIncrease"),
                    (float) map.get("ber"),
                    (String) map.get("unitAlarm"),
                    (String) map.get("txAlarm"),
                    (String) map.get("rxAlarm"),
                    (String) map.get("oduAlarm"),
                    (Boolean) map.get("reachable"),
//                    (float) map.get("frequencyOffset"),
                    (String) map.get("askerVersion")));
        }
        return parameterSetList;
    }

    public float getTxPowerLevel() {
        return txPowerLevel;
    }

    public void setTxPowerLevel(float txPowerLevel) {
        this.txPowerLevel = txPowerLevel;
    }

    public String getFaults() {
        return faults;
    }

    public void setFaults(String faults) {
        this.faults = faults;
    }

    public float getFrequencyOffset() {
        return frequencyOffset;
    }

    public int getTestMode() {
        return testMode;
    }

    public void setTestMode(int testMode) {
        this.testMode = testMode;
    }

    public String getFramingMode() {
        return framingMode;
    }

    public void setFramingMode(String framingMode) {
        this.framingMode = framingMode;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getCarrierState() {
        return carrierState;
    }

    public void setCarrierState(String carrierState) {
        this.carrierState = carrierState;
    }

    public void setFrequencyOffset(float frequencyOffset) {
        this.frequencyOffset = frequencyOffset;
    }

    public float getEbNoDelta() {
        return ebNoDelta;
    }

    public void setEbNoDelta(float ebNoDelta) {
        this.ebNoDelta = ebNoDelta;
    }

    public float getEbNoRemoteDelta() {
        return ebNoRemoteDelta;
    }

    public void setEbNoRemoteDelta(float ebNoRemoteDelta) {
        this.ebNoRemoteDelta = ebNoRemoteDelta;
    }
}
