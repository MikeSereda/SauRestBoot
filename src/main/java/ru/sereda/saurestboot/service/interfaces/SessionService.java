package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.Session;

import java.util.List;
import java.util.Map;

public interface SessionService {
    Map<String, List<Session>> getSessions(String modemId, String startTime, String endTime);
    Map<String, List<Session>> getSessions(String startTime, String endTime);
}
