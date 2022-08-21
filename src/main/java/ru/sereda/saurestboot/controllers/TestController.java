package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.DeviceService;
import ru.sereda.saurestboot.service.DeviceParameterSetService;
import ru.sereda.saurestboot.service.SessionService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    DeviceParameterSetService parameterSetService;

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
            parameterSet = new DeviceReducedParameterSet("cdm111",6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        else{
            parameterSet = new DeviceParameterSet(6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),68,24,1.3f,1f,"None","None","None","None", "cdm111");
        }
        return parameterSet.getParametersMap();
    }
}
