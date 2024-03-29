package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/sessions")
    public List<Session> getSessions(
            @RequestParam(name = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(name = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "deviceId", required = false, defaultValue = "") String deviceId,
            @RequestParam(name = "newVersion", required = false, defaultValue = "false") boolean newVersion) {
        startTime=startTime.replace(' ','T');
        endTime=endTime.replace(' ','T');
        if (deviceId.equals("")){
            if (newVersion){
                return sessionService.getMixedSessions(startTime, endTime);
            }
            return sessionService.getSessions(startTime, endTime);
        }
        if (newVersion){
            return sessionService.getMixedSessions(deviceId, startTime, endTime);
        }
        return sessionService.getSessions(deviceId, startTime, endTime);
    }

}
