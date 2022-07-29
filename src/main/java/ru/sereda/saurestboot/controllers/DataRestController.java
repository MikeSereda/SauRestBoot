package ru.sereda.saurestboot.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.LastPair;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class DataRestController {

    @GetMapping("/devices")
    public HashMap<String,String> testMethod(
            @RequestParam(required = false,name = "deviceType",defaultValue = "cdm 570l") String deviceType)
    {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put("Test key1", deviceType);
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
        if (modemId!=null){
            params.put("modemId",modemId);
        }
        else {
            params.put("modemId","multiple_modems");
        }
        params.put("limit", String.valueOf(limit));
        if (reduced){
            System.out.println("reduced is true");
        }
        else {
            System.out.println("reduced is false");
        }
        return params;
    }

    @PostMapping("/refresh-modem-graphs")
    public ArrayList<LastPair>refreshModemGraphs(
            @RequestParam(name = "limit", required = false, defaultValue = "${dashboard.limit}") int limit,
            @RequestBody ArrayList<LastPair> lastPairs
    ){
        System.out.println(limit);
        return lastPairs;
    }

    @GetMapping("/devices/{deviceId}")
    public HashMap<String,String> testMethod2(@PathVariable("deviceId") String deviceId)
    {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put("Test key1", deviceId);
        return testHashMap;
    }

    @GetMapping("/test")
    public HashMap<String,Object> testMethod3(@RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced)
    {
        ReducedParameterSet parameterSet;
        if (reduced){
            parameterSet = new ReducedParameterSet();
            return parameterSet.getParameters();
        }
        else{
            parameterSet = new ParameterSet();
            return parameterSet.getParameters();
        }
    }
}
