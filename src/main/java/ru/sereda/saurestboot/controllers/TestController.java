package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.ParameterSetService;
import ru.sereda.saurestboot.service.interfaces.SessionService;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.List;

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

    @Autowired
    UserService userService;

    @PostMapping("/phones")
    public PhoneRegion addPhoneRegions(
            @RequestBody List<PhoneRegion> phoneRegions
    ){
        System.out.println(phoneRegions.toString());
        return null;
    }
}
