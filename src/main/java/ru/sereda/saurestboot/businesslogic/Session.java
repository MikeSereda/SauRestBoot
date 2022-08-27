package ru.sereda.saurestboot.businesslogic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class Session {
    private final String deviceType;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Session(String deviceType, LocalDateTime startTime, LocalDateTime endTime) {
        this.deviceType = deviceType;
        this.startTime = startTime.truncatedTo(ChronoUnit.SECONDS);
        this.endTime = endTime.truncatedTo(ChronoUnit.SECONDS);
    }

    public String getDeviceType() {
        return deviceType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public static Session sessionWrapper(Map<String,Object> map){
        return new Session(
                (String)map.get("modem_id"),
                ((Timestamp)map.get("start_time")).toLocalDateTime(),
                ((Timestamp)map.get("end_time")).toLocalDateTime()
        );
    }
}
