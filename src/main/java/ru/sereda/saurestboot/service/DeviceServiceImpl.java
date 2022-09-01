package ru.sereda.saurestboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.DeviceDAO;
import ru.sereda.saurestboot.businesslogic.Device;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

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
}
