package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.service.interfaces.DeviceService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class DevicesController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<Device> getDevice(@PathVariable("deviceId") String deviceId){
        Device device = deviceService.getDevice(deviceId);
        if (device!=null){
            return new ResponseEntity<>(device, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/devices")
    public List<Device> getDevices(@RequestParam(name = "type",required = false,defaultValue = "") String deviceType){
        return deviceService.getDevices(deviceType);
    }

    @GetMapping("/device-types")
    public List<String> getDeviceTypes(){
        return deviceService.getDeviceTypes();
    }
}
