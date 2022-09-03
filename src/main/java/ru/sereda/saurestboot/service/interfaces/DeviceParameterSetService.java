package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface DeviceParameterSetService {
    List<ParameterSet> getParameters(LocalDateTime startTime, boolean reduced, int limit);
    List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);

    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit);
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);

    List<ParameterSet> getUpdates(HashMap<String, LocalDateTime> lastPairs, boolean reduced, int limit);
}