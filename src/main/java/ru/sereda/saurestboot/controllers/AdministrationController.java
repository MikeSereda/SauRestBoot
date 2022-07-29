package ru.sereda.saurestboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AdministrationController {
    @GetMapping("/baseLoad2")
    public HashMap<String,String> testMethod(
            @RequestParam(required = false,name = "device_type",defaultValue = "cdm 570l") String deviceType)
    {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put("Test key1", deviceType);
        return testHashMap;
    }
}
