package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;
import ru.sereda.saurestboot.service.ParameterSetService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ParameterSetService parameterSetService;

    @GetMapping("/")
    public HashMap<String,Object> testMethod3(
            @RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced)
    {
        ReducedParameterSet parameterSet;
        if (reduced){
            parameterSet = new ReducedParameterSet("p1","p2","p3");
        }
        else{
            parameterSet = new ParameterSet("p1","p2","p3","p4","p5","p6");
        }
        return parameterSet.getParameters();
    }

    @GetMapping("/test-parameter-set")
    public List<ReducedParameterSet> testMethod4(@RequestParam(name = "reduced",required = false, defaultValue = "false") boolean reduced){
        return parameterSetService.getParameters(reduced);
    }
}
