package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.SessionDAO;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.service.interfaces.SessionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionDAO sessionDAO;

    @Override
    public List<Session> getSessions(String modemId, String startTime, String endTime) {
        if (endTime.isEmpty()){
            if (modemId.isEmpty()){
                return sessionDAO.getSessions(LocalDateTime.parse(startTime));
            }
            else {
                return sessionDAO.getSessions(modemId,LocalDateTime.parse(startTime));
            }
        }
        else{
            if (modemId.isEmpty()){
                return sessionDAO.getSessions(LocalDateTime.parse(startTime),LocalDateTime.parse(endTime));
            }
            else {
                return sessionDAO.getSessions(modemId,LocalDateTime.parse(startTime),LocalDateTime.parse(endTime));
            }
        }
    }
}
