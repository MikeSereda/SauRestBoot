package ru.sereda.saurestboot.businesslogic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceParameterSet extends DeviceReducedParameterSet {


    private final int rsl;
    private final int temperature;
    private final float txPowerLevelIncrease;
    private final float ber;
    private final String unitAlarm;
    private final String txAlarm;
    private final String rxAlarm;
    private final String oduAlarm;

    public DeviceParameterSet(float ebNo,
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
                              String modemId) {
        super(modemId, ebNo, ebNoRemote, timestampWotz);
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
        return "DeviceParameterSet{" +
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

    static public List<DeviceParameterSet> parameterSetWrapper(List<Map<String, Object>> mapList) {
        List<DeviceParameterSet> parameterSetList = new ArrayList<>();
        for (Map<String,Object> map : mapList){
            parameterSetList.add(new DeviceParameterSet(
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
                    (String) map.get("modemId")));
        }
        return parameterSetList;
    }
}
