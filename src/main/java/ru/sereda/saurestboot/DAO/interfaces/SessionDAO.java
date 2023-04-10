package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionDAO {
    List<Session> getSessions(String modemId, LocalDateTime startTime, LocalDateTime endTime);
}
