package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.ParameterSetService;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    ParameterSetService parameterSetService;

    @Autowired
    SessionService sessionService;

    @Autowired
    DeviceService deviceService;

    @PostMapping("/phones")
    public PhoneRegion addPhoneRegions(
            @RequestBody List<PhoneRegion> phoneRegions
    ){
        System.out.println(phoneRegions.toString());
        return null;
    }

    @GetMapping("/approximted")
    public Map<String,List<ParameterSet>> approximated(){

        return parameterSetService.getParameters("cdm115", LocalDateTime.parse("2022-07-22T15:26:17"),LocalDateTime.parse("2022-07-22T15:27:47"),true,1000);
    }
}
