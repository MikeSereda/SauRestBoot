package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashMap;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class ReducedParameterSet implements ParameterCarrier{

    private final float ebNo;
    private final float ebNoRemote;
    private final LocalDateTime timestampWotz;

    public ReducedParameterSet(float ebNo, float ebNoRemote, LocalDateTime timestampWotz) {
        this.ebNo = ebNo;
        this.ebNoRemote = ebNoRemote;
        this.timestampWotz = timestampWotz;
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
        return "ReducedParameterSet{" +
                "ebNo=" + ebNo +
                ", ebNoRemote=" + ebNoRemote +
                ", timestampWotz=" + timestampWotz +
                '}';
    }
}
