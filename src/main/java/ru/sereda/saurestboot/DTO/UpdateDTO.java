package ru.sereda.saurestboot.DTO;

import java.time.LocalDateTime;
import java.util.Set;

public class UpdateDTO {
    private final String deviceId;
    private final LocalDateTime lastUpTime;
    private final Set<String> requiredParams;

    public UpdateDTO(String deviceId, LocalDateTime lastUpTime, Set<String> requiredParams) {
        this.deviceId = deviceId;
        this.lastUpTime = lastUpTime;
        this.requiredParams = requiredParams;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getLastUpTime() {
        return lastUpTime;
    }

    public Set<String> getRequiredParams() {
        return requiredParams;
    }
}
