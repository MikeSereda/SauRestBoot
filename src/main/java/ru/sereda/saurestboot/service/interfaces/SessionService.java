package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.Session;

import java.util.List;

public interface SessionService {
    List<Session> getSessions(String modemId, String startTime, String endTime);
}
