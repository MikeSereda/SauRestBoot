package ru.sereda.saurestboot.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class TextController {

    @GetMapping("/greet")
    public Map<String,Object> greeting(){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("headerText","Информационный заголовок");
        returnMap.put("on_monday","Понедельничный вариант");
        returnMap.put("bodyText","Обкатка проекта");
        return returnMap;
    }
}
