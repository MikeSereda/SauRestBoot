package ru.sereda.saurestboot.service;

import ru.sereda.saurestboot.businesslogic.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionService {
    List<Session> getSessions(String modemId, LocalDateTime startTime);
    List<Session> getSessions(String modemId, LocalDateTime startTime, LocalDateTime endTime);
    List<Session> getSessions(LocalDateTime startTime);
    List<Session> getSessions(LocalDateTime startTime, LocalDateTime endTime);
}