package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

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
            parameterSet = new DeviceParameterSet(6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                    68,24,1.3f,1f,"None","None","None","None",
                    "cdm111",true,"tested");
        }
        return parameterSet.getParametersMap();
    }

    @PostMapping("/phones")
    public PhoneRegion addPhoneRegions(
            @RequestBody List<PhoneRegion> phoneRegions
    ){
        System.out.println(phoneRegions.toString());
        return null;
    }

    @GetMapping("/authenticated")
    public String authenticated(){
        try {
            return "Auth";
        } catch (ArithmeticException e){
            throw new BadCredentialsException("invalid.");
        }
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

}
