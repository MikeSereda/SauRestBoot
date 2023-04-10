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

    // TODO: 10.04.2023 select timestamp_wotz, avg as eb_no from
    //    (select timestamp_wotz, avg(eb_no) over (partition by t.row) as avg, row, lag(row) over () as row_lag from
    //     (SELECT timestamp_wotz, eb_no, (row_number() over () -1)/5 as row FROM public.parameters
    //       WHERE device_id='cdm111' AND timestamp_wotz<'2023-04-10 04:57:45' AND timestamp_wotz>'2023-04-10 04:40:05' ORDER BY timestamp_wotz)
    //          as T order by T.row ) as D where row<>row_lag
    //  query for approx params

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
            @RequestParam(name = "startTime") String startTime,
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
