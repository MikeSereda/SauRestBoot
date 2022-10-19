package ru.sereda.saurestboot.businesslogic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendedParameterSet extends ReducedParameterSet {
    private final int rsl;
    private final int temperature;
    private final float txPowerLevelIncrease;
    private final float ber;
    private final String unitAlarm;
    private final String txAlarm;
    private final String rxAlarm;
    private final String oduAlarm;
    private final boolean reachable;
    private final String askerVersion;

    private final float frequencyOffset;

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
        this.frequencyOffset = 0;
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
}
