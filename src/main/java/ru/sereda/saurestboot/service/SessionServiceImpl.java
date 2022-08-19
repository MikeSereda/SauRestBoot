package ru.sereda.saurestboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.SessionDAO;
import ru.sereda.saurestboot.businesslogic.Session;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    SessionDAO sessionDAO;

    @Override
    public List<Session> getSessions(String modemId, LocalDateTime startTime) {
        return sessionDAO.getSessions(modemId, startTime);
    }

    @Override
    public List<Session> getSessions(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        return sessionDAO.getSessions(modemId, startTime, endTime);
    }

    @Override
    public List<Session> getSessions(LocalDateTime startTime) {
        return sessionDAO.getSessions(startTime);
    }

    @Override
    public List<Session> getSessions(LocalDateTime startTime, LocalDateTime endTime) {
        return sessionDAO.getSessions(startTime, endTime);
    }
}
