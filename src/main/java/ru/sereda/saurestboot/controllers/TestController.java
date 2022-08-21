package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.DAO.DeviceDAO;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.DeviceService;
import ru.sereda.saurestboot.service.ModemParameterSetService;
import ru.sereda.saurestboot.service.SessionService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    ModemParameterSetService parameterSetService;

    @Autowired
    SessionService sessionService;

    @Autowired
    DeviceService deviceService;

    @GetMapping("/test")
    public HashMap<String,Object> testMethod(
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced)
    {
        ParameterSet parameterSet;
        if (reduced){
            parameterSet = new ModemReducedParameterSet("cdm111",6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        else{
            parameterSet = new ModemParameterSet(6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),68,24,1.3f,1f,"None","None","None","None", "cdm111");
        }
        return parameterSet.getParametersMap();
    }

    @GetMapping("/test-devices/{deviceId}")
    public ResponseEntity<Device> testMethod2(@PathVariable("deviceId") String deviceId){
        return deviceService.getDevice(deviceId);
    }

    @GetMapping("/test-devices")
    public List<Device> testMethod3(@RequestParam(name = "type",required = false,defaultValue = "") String deviceType){
        return deviceService.getDevices(deviceType);
    }

    @GetMapping("/test-device-types")
    public List<String> testMethod4(){
        return deviceService.getDeviceTypes();
    }
}
