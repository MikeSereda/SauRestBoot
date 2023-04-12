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
        Map<String, List<Session>> sessions = new HashMap<>();
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

//        if (startTime.equals(""))
//            requiredStartTime = LocalDateTime.now().minusWeeks(2);
//        else
//            requiredStartTime = LocalDateTime.parse(startTime);
//        if (endTime.equals(""))
//            requiredEndTime = LocalDateTime.now();
//        else
//            requiredEndTime = LocalDateTime.parse(startTime);
        sessions.put(modemId, sessionDAO.getSessions(modemId,requiredStartTime,requiredEndTime));
        return sessionsSorter(sessions);
    }

    @Override
    public List<Session> getSessions(String startTime, String endTime) {
        Map<String, List<Session>> sessions = new HashMap<>();

        for (String modemId : deviceService.getDeviceIds())
            sessions.put(modemId,getSessions(modemId,startTime,endTime));

        return sessionsSorter(sessions);
    }

    private List<Session> sessionsSorter(Map<String, List<Session>> sessionsMap){
        List<Session> sessions = new ArrayList<>();
        for (String deviceId: sessionsMap.keySet()){
            for (Session session: sessionsMap.get(deviceId)){
                session.setDeviceId(deviceId);
                sessions.add(session);
            }

        }
        Collections.sort(sessions);
        return sessions;
    }
}
