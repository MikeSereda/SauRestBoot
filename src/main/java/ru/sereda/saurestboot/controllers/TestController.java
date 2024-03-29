package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.implementations.ParametersService;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    SessionService sessionService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    ParametersService parametersService;

    @PostMapping("/phones")
    public List<PhoneRegion> addPhoneRegions(
            @RequestBody List<PhoneRegion> phoneRegions
    ){
        System.out.println(phoneRegions.toString());
        return phoneRegions;
    }

    @GetMapping("/approximated")
    public Map<String,List<Session>> approximated(){
        return null;
    }

    @GetMapping("/mixedSessions")
    public List<Session> getMixedSessions(){
        return sessionService.getMixedSessions("cdm111","2023-04-17T00:00:00","2023-04-21T00:00:00");
    }
}
