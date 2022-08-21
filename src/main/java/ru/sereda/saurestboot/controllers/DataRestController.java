package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.Device;
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

    @GetMapping("/params-between")
    public HashMap<String,String> paramsBetween(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime") String endTime,
            @RequestParam(name = "modemId", required = false, defaultValue = "") String modemId,
            @RequestParam(name = "limit", required = false, defaultValue = "${dashboard.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced
    ){
        HashMap<String,String> params = new HashMap<>();
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        if (modemId.isEmpty()){
            params.put("modemId","multiple_modems");
        }
        else {
            params.put("modemId",modemId);
        }
        params.put("limit", String.valueOf(limit));
        params.put("reduced",String.valueOf(reduced));
        return params;
    }

    @PostMapping("/refresh-modem-graphs")
    public HashMap<String, LocalDateTime> refreshModemGraphs(
            @RequestParam(name = "limit", required = false, defaultValue = "${dashboard.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced,
            @RequestBody HashMap<String, LocalDateTime> lastPairs
    ){
        return lastPairs;
    }

    @GetMapping("/sessions")
    public List<Session> testMethod5(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "modemId", required = false, defaultValue = "") String modemId) {
        return sessionService.getSessions(modemId,startTime,endTime);
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
