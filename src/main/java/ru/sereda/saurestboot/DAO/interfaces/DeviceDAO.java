package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.Device;
import java.util.List;

public interface DeviceDAO {
    List<Device> getDevices();
    Device getDevice(String id);
    List<Device> getDevices(String deviceType);
    List<String> getDeviceTypes();
    List<String> getDeviceIds();
    List<String> getActiveDeviceIds();
}
