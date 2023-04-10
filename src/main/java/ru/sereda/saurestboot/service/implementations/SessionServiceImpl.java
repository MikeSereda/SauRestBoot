package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.SessionDAO;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionDAO sessionDAO;
    @Autowired
    DeviceService deviceService;

    @Override
    public Map<String, List<Session>> getSessions(String modemId, String startTime, String endTime) {
        Map<String, List<Session>> sessions = new HashMap<>();
        LocalDateTime requiredStartTime;
        LocalDateTime requiredEndTime;
        if (startTime.equals(""))
            requiredStartTime = LocalDateTime.now().minusWeeks(2);
        else
            requiredStartTime = LocalDateTime.parse(startTime);
        if (endTime.equals(""))
            requiredEndTime = LocalDateTime.now();
        else
            requiredEndTime = LocalDateTime.parse(startTime);
        sessions.put(modemId, sessionDAO.getSessions(modemId,requiredStartTime,requiredEndTime));
        return sessions;
    }

    @Override
    public Map<String, List<Session>> getSessions(String startTime, String endTime) {
        Map<String, List<Session>> sessions = new HashMap<>();

        for (String modemId : deviceService.getDeviceIds())
            sessions.putAll(getSessions(modemId,startTime,endTime));

        return sessions;
    }
}
