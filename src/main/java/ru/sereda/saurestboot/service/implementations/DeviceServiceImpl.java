package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.DeviceDAO;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.service.interfaces.DeviceService;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceDAO deviceDAO;

    @Override
    public List<Device> getDevices(String deviceType) {
        if (deviceType.isEmpty()){
            return deviceDAO.getDevices();
        }
        else {
            return deviceDAO.getDevices(deviceType);
        }
    }

    @Override
    public List<Device> getDevices() {
        return deviceDAO.getDevices();
    }

    @Override
    public Device getDevice(String id) {
        return deviceDAO.getDevice(id);
    }

    @Override
    public List<String> getDeviceTypes() {
        return deviceDAO.getDeviceTypes();
    }

    @Override
    public List<String> getDeviceIds() {
        return deviceDAO.getDeviceIds();
    }

    @Override
    public List<String> getActiveDeviceIds() {
        return deviceDAO.getActiveDeviceIds();
    }

    @Override
    public List<Device> addDevices(List<Device> addingDevices) {
        return deviceDAO.addDevices(addingDevices);
    }

    @Override
    public Device removeDevice(String deviceId) {
        if (getDevice(deviceId)!=null){
            return deviceDAO.removeDevice(deviceId);
        }
        else return null;
    }


}
