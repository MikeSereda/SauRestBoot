package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.Session;

import java.util.List;

public interface SessionService {
    List<Session> getSessions(String modemId, String startTime, String endTime);
    List<Session> getSessions(String startTime, String endTime);
    List<Session> getMixedSessions(String modemId, String startTime, String endTime);
    List<Session> getMixedSessions(String startTime, String endTime);
}
