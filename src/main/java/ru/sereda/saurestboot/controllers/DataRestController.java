package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.PhoneRegion;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.security.jwt.JwtTokenProvider;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.PhoneService;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DataRestController {

    @Autowired
    DeviceParameterSetService parameterSetService;

    @Autowired
    SessionService sessionService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    PhoneService phoneService;

    @GetMapping("/parameters")
    public List<ParameterSet> getParameters(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId,
            @RequestParam(name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced){
        startTime=startTime.replace(' ','T');
        endTime=endTime.replace(' ','T');
        if (deviceId.isEmpty()){
            if (endTime.isEmpty()){
                return parameterSetService.getParameters(LocalDateTime.parse(startTime),reduced,limit);
            }
            else {
                return parameterSetService.getParameters(LocalDateTime.parse(startTime),LocalDateTime.parse(endTime),reduced,limit);
            }
        }
        else {
            if (endTime.isEmpty()){
                return parameterSetService.getParameters(deviceId, LocalDateTime.parse(startTime),reduced,limit);
            }
            else {
                return parameterSetService.getParameters(deviceId, LocalDateTime.parse(startTime),LocalDateTime.parse(endTime),reduced,limit);
            }
        }
    }

    @PostMapping("/updates")
    public List<ParameterSet> getUpdates(
            @RequestParam(name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced,
            @RequestBody HashMap<String, LocalDateTime> lastPairs
    ){
        return parameterSetService.getUpdates(lastPairs,reduced,limit);
    }

    @GetMapping("/sessions")
    public List<Session> getSessions(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId) {
        startTime=startTime.replace(' ','T');
        endTime=endTime.replace(' ','T');
        return sessionService.getSessions(deviceId,startTime,endTime);
    }

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

    @GetMapping("/phones")
    public List<PhoneRegion> getPhones(){
        return phoneService.getPhones();
    }

    @GetMapping("/phones/{city}")
    public ResponseEntity<PhoneRegion> getPhones(@PathVariable("city") String city){
        PhoneRegion phoneRegion = phoneService.getPhones(city);
        if (phoneRegion!=null){
            return new ResponseEntity<>(phoneRegion, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
