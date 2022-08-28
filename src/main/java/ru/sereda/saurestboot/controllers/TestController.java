package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.security.jwt.JwtTokenProvider;
import ru.sereda.saurestboot.service.DeviceService;
import ru.sereda.saurestboot.service.DeviceParameterSetService;
import ru.sereda.saurestboot.service.SessionService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    @GetMapping("/test-parameter-set")
    public List<ParameterSet> testMethod1(
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "modemId", required = false, defaultValue = "") String modemId,
            @RequestParam(name = "limit", required = false, defaultValue = "${sql.parameters.parameterset.limit}") int limit,
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced){
        if (modemId.isEmpty()){
            if (endTime.isEmpty()){
                return parameterSetService.getParameters(LocalDateTime.parse(startTime),reduced,limit);
            }
            else {
                return parameterSetService.getParameters(LocalDateTime.parse(startTime),LocalDateTime.parse(endTime),reduced,limit);
            }
        }
        else {
            if (endTime.isEmpty()){
                return parameterSetService.getParameters(modemId, LocalDateTime.parse(startTime),reduced,limit);
            }
            else {
                return parameterSetService.getParameters(modemId, LocalDateTime.parse(startTime),LocalDateTime.parse(endTime),reduced,limit);
            }
        }
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

    @GetMapping("/phones")
    public List<PhoneRegion> getPhones(){
        List<PhoneRegion> phoneRegions = new ArrayList<>();
        phoneRegions.add(new PhoneRegion());
        phoneRegions.get(0).setCity("Красноярск");
        phoneRegions.get(0).setCityCode("0391");
        phoneRegions.get(0).addSubscriber("Начальник смены","21213");
        phoneRegions.get(0).addSubscriber("Серпантин","23219");

        phoneRegions.add(new PhoneRegion());
        phoneRegions.get(1).setCity("Владивосток");
        phoneRegions.get(1).setCityCode("0423");
        phoneRegions.get(1).addSubscriber("Серпантин","2043");
        phoneRegions.get(1).addSubscriber("Дейкун","2053");
        return phoneRegions;
    }
}
