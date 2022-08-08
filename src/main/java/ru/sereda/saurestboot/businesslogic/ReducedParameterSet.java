package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashMap;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
public class ReducedParameterSet implements ParameterCarrier{

    protected float ebNo;
    protected float ebNoRemote;

    protected LocalDateTime timestampWotz;

    public ReducedParameterSet() {
    }

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

    public void setEbNo(float ebNo) {
        this.ebNo = ebNo;
    }

    public float getEbNoRemote() {
        return ebNoRemote;
    }

    public void setEbNoRemote(float ebNoRemote) {
        this.ebNoRemote = ebNoRemote;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getTimestampWotz() {
        return timestampWotz;
    }

    public void setTimestampWotz(LocalDateTime timestampWotz) {
        this.timestampWotz = timestampWotz;
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
