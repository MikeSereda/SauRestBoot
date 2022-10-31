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

    @GetMapping("/popups")
    public Map<String,String> popups(){
        Map<String,String> returnMap = new HashMap<>();
        returnMap.put("style_id1","Описание всплывашки 1");
        returnMap.put("style_id2","Описание всплывашки 2");
        returnMap.put("style_default","Описание всплывашки дефолт");
        return returnMap;
    }
}
