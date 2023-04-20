package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.SessionDAO;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionDAO sessionDAO;
    @Autowired
    DeviceService deviceService;

    @Override
    public List<Session> getSessions(String modemId, String startTime, String endTime) {
        LocalDateTime requiredStartTime = null;
        LocalDateTime requiredEndTime = null;

        if (startTime.equals("") && endTime.equals("")){
            requiredStartTime = LocalDateTime.now().minusDays(7);
            requiredEndTime = LocalDateTime.now();
        }
        else {
            if (!startTime.equals("") && !endTime.equals("")){
                requiredStartTime = LocalDateTime.parse(startTime);
                requiredEndTime = LocalDateTime.parse(endTime);
            }
            else {
                if (startTime.equals("")){
                    requiredEndTime = LocalDateTime.parse(endTime);
                    requiredStartTime = requiredEndTime.minusDays(7);
                }
                if (endTime.equals("")){
                    requiredStartTime = LocalDateTime.parse(startTime);
                    requiredEndTime = requiredStartTime.plusDays(7);
                }
            }
        }
        List<Session> sessionList = sessionDAO.getSessions(modemId,requiredStartTime,requiredEndTime);
        Collections.sort(sessionList);
        return sessionList;
    }

    @Override
    public List<Session> getSessions(String startTime, String endTime) {
        List<Session> sessionList = new ArrayList<>();

        for (String modemId : deviceService.getDeviceIds())
            sessionList.addAll(getSessions(modemId,startTime,endTime));
        Collections.sort(sessionList);

        return sessionList;
    }

    public List<Session> getMixedSessions(String modemId, String startTime, String endTime) {
        LocalDateTime requiredStartTime = null;
        LocalDateTime requiredEndTime = null;

        if (startTime.equals("") && endTime.equals("")){
            requiredStartTime = LocalDateTime.now().minusDays(7);
            requiredEndTime = LocalDateTime.now();
        }
        else {
            if (!startTime.equals("") && !endTime.equals("")){
                requiredStartTime = LocalDateTime.parse(startTime);
                requiredEndTime = LocalDateTime.parse(endTime);
            }
            else {
                if (startTime.equals("")){
                    requiredEndTime = LocalDateTime.parse(endTime);
                    requiredStartTime = requiredEndTime.minusDays(7);
                }
                if (endTime.equals("")){
                    requiredStartTime = LocalDateTime.parse(startTime);
                    requiredEndTime = requiredStartTime.plusDays(7);
                }
            }
        }

        List<Session> savedSessions = sessionDAO.getOnlySavedSessions(modemId,requiredStartTime,requiredEndTime);
        List<Session> mixedSessions = new ArrayList<>();
        for (Session sessionForCheck : sessionDAO.getSessions(modemId,requiredStartTime,requiredEndTime)){
            boolean needToAdd = false;
            for (Session mainSession : savedSessions){
                needToAdd=needToAdd||mainSession.containsThisTime(sessionForCheck);
            }
            if (!needToAdd)
                mixedSessions.add(sessionForCheck);
        }
        mixedSessions.addAll(savedSessions);
        Collections.sort(mixedSessions);
        return mixedSessions;
    }

    public List<Session> getMixedSessions(String startTime, String endTime) {
        List<Session> sessionList = new ArrayList<>();

        for (String modemId : deviceService.getDeviceIds())
            sessionList.addAll(getMixedSessions(modemId,startTime,endTime));

        Collections.sort(sessionList);
        return sessionList;
    }
}
