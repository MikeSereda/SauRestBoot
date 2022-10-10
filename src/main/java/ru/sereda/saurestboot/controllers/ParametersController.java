package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ParametersController {

    @Autowired
    DeviceParameterSetService parameterSetService;

    @GetMapping("/updates")
    public Map<String, List<ParameterSet>> getLastUpdates(
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId,
            @RequestParam(name = "relative",required = false, defaultValue = "true") boolean reduced)
    {
        Map<String, List<ParameterSet>> parameterSetList;
        if (deviceId.isEmpty()){
            parameterSetList = parameterSetService.getLastUpdates(reduced);
        }
        else{
            parameterSetList = parameterSetService.getLastUpdates(deviceId,reduced);
        }
        return parameterSetList;
    }

    @PostMapping("/updates")
    public Map<String, List<ParameterSet>> getUpdates(
            @RequestParam(name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "true") boolean reduced,
            @RequestBody HashMap<String, LocalDateTime> lastPairs
    ){
        return parameterSetService.getUpdates(lastPairs,reduced,limit);
    }

    @GetMapping("/parameters")
    public Map<String, List<ParameterSet>> getParameters(
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
            Map<String, List<ParameterSet>> outputMap = new HashMap<>();
            if (endTime.isEmpty()){
                outputMap.put(deviceId,parameterSetService.getParameters(deviceId, LocalDateTime.parse(startTime),reduced,limit));
                return outputMap;
            }
            else {
                outputMap.put(deviceId,parameterSetService.getParameters(deviceId, LocalDateTime.parse(startTime),LocalDateTime.parse(endTime),reduced,limit));
                return outputMap;
            }
        }
    }
}
