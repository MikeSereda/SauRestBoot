package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class DeviceReducedParameterSet implements ParameterSet {

    private final float ebNo;
    private final float ebNoRemote;
    private final LocalDateTime timestampWotz;

    public DeviceReducedParameterSet(float ebNo, float ebNoRemote, LocalDateTime timestampWotz) {
        this.ebNo = ebNo;
        this.ebNoRemote = ebNoRemote;
        this.timestampWotz = timestampWotz.truncatedTo(ChronoUnit.SECONDS);
    }

    @JsonIgnore
    public HashMap<String, Object> getParametersMap(){
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("timestampWotz",timestampWotz);
        parametersMap.put("ebNo",ebNo);
        parametersMap.put("ebNoRemote",ebNoRemote);
        return parametersMap;
    }

    public float getEbNo() {
        return ebNo;
    }

    public float getEbNoRemote() {
        return ebNoRemote;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getTimestampWotz() {
        return timestampWotz;
    }

    @Override
    public String toString() {
        return "DeviceReducedParameterSet{" +
                "ebNo=" + ebNo +
                ", ebNoRemote=" + ebNoRemote +
                ", timestampWotz=" + timestampWotz +
                '}';
    }

    static public List<DeviceReducedParameterSet> reducedParameterSetWrapper(List<Map<String, Object>> mapList) {
        List<DeviceReducedParameterSet> parameterSetList = new ArrayList<>();
        for (Map<String,Object> map : mapList){
            parameterSetList.add(new DeviceReducedParameterSet(
                    (float) map.get("ebNo"),
                    (float) map.get("ebNoRemote"),
                    ((Timestamp)map.get("timestampWotz")).toLocalDateTime()));
        }
        return parameterSetList;
    }
}
