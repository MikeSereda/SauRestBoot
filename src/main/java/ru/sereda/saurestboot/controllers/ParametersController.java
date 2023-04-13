package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.Parameters;
import ru.sereda.saurestboot.service.implementations.ParametersService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ParametersController {
    @Autowired
    ParametersService parametersService;

    @GetMapping("/parameters")
    public Map<String, List<Parameters>> testGetParameters(
            @RequestParam(name = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId,
            @RequestParam
                    (name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit){
        Map<String, List<Parameters>> parametersMap;
        if (deviceId.equals(""))
            parametersMap = parametersService.getRequiredParameters(null,startTime,endTime, limit);
        else
            parametersMap = parametersService.getRequiredParameters(null,deviceId,startTime,endTime, limit);
        return parametersMap;
    }

    @PostMapping("/parameters")
    public Map<String, List<Parameters>> testPostParameters(
            @RequestParam(name = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId,
            @RequestParam
                    (name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestBody Set<String> requiredValues){
        Map<String, List<Parameters>> parametersMap;
        if (deviceId.equals(""))
            parametersMap = parametersService.getRequiredParameters(requiredValues,startTime,endTime, limit);
        else
            parametersMap = parametersService.getRequiredParameters(requiredValues,deviceId,startTime,endTime, limit);
        return parametersMap;
    }

    @PostMapping("/updates")
    public Map<String, List<Parameters>> testUpdates(
            @RequestParam
                    (name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestBody HashMap<String, LocalDateTime> lastPairs){
        Map<String, List<Parameters>> parametersMap;
        parametersMap = parametersService.getUpdates(lastPairs,limit);
        return parametersMap;
    }
}
