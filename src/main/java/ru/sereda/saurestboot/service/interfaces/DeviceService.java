package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getDevices(String deviceType);

    List<Device> getDevices();
    Device getDevice(String id);
    List<String> getDeviceTypes();
    List<String> getDeviceIds();
    List<String> getActiveDeviceIds();

    List<Device> addDevices(List<Device> addingDevices);

    Device removeDevice(String deviceId);
}
