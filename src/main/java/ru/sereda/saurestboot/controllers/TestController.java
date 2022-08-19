package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sereda.saurestboot.businesslogic.ParameterCarrier;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.service.ParameterSetService;
import ru.sereda.saurestboot.service.SessionService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    ParameterSetService parameterSetService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/test")
    public HashMap<String,Object> testMethod3(
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced)
    {
        ParameterCarrier parameterSet;
        if (reduced){
            parameterSet = new ReducedParameterSet("cdm111",6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        else{
            parameterSet = new ParameterSet(6.5f, 6.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),68,24,1.3f,1f,"None","None","None","None", "cdm111");
        }
        return parameterSet.getParametersMap();
    }

    @GetMapping("/test-parameter-set")
    public List<ParameterCarrier> testMethod4(@RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced){
        return parameterSetService.getParameters(reduced);
    }

    @GetMapping("/test-sessions")
    public List<Session> testMethod5(){
        return sessionService.getSessions("cdm111",LocalDateTime.parse("2022-08-16T21:55:52"),LocalDateTime.parse("2022-08-16T21:56:55"));
    }
}
