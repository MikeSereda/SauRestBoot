package ru.sereda.saurestboot.service.interfaces;

import org.springframework.http.ResponseEntity;
import ru.sereda.saurestboot.businesslogic.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getDevices(String deviceType);
    Device getDevice(String id);
    List<String> getDeviceTypes();
    List<String> getDeviceIds();
}
