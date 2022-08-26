package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.service.DeviceService;
import ru.sereda.saurestboot.service.DeviceParameterSetService;
import ru.sereda.saurestboot.service.SessionService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataRestController {

    @Autowired
    DeviceParameterSetService parameterSetService;

    @Autowired
    SessionService sessionService;

    @Autowired
    DeviceService deviceService;

    @GetMapping("/parameters")
    public List<ParameterSet> getParameters(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId,
            @RequestParam(name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced){
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
    public List<ParameterSet> updateDeviceGraphs(
            @RequestParam(name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced,
            @RequestBody HashMap<String, LocalDateTime> lastPairs
    ){
        return parameterSetService.getUpdates(lastPairs,reduced,limit);
    }

    @GetMapping("/sessions")
    public List<Session> testMethod5(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId) {
        return sessionService.getSessions(deviceId,startTime,endTime);
    }

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<Device> testMethod2(@PathVariable("deviceId") String deviceId){
        return deviceService.getDevice(deviceId);
    }

    @GetMapping("/devices")
    public List<Device> testMethod3(@RequestParam(name = "type",required = false,defaultValue = "") String deviceType){
        return deviceService.getDevices(deviceType);
    }

    @GetMapping("/device-types")
    public List<String> testMethod4(){
        return deviceService.getDeviceTypes();
    }
}
