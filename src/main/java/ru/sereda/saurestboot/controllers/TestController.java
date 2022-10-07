package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.DTO.UserDTO;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.SessionService;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    DeviceParameterSetService parameterSetService;

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

}
