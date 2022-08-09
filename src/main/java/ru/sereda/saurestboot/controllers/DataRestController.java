package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.service.ParameterSetService;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class DataRestController {

    @Autowired
    ParameterSetService parameterSetService;

    @GetMapping("/devices")
    public HashMap<String,String> testMethod(
            @RequestParam(required = false,name = "deviceType",defaultValue = "cdm 570l") String deviceType)
    {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put("Device 1", deviceType);
        return testHashMap;
    }

    @GetMapping("/params-between")
    public HashMap<String,String> paramsBetween(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime") String endTime,
            @RequestParam(name = "modemId", required = false) String modemId,
            @RequestParam(name = "limit", required = false, defaultValue = "${dashboard.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced
    ){
        HashMap<String,String> params = new HashMap<>();
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        if (modemId == null){
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

    @GetMapping("/devices/{deviceId}")
    public HashMap<String,String> testMethod2(@PathVariable("deviceId") String deviceId)
    {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put(deviceId, "Device N");
        return testHashMap;
    }
}
