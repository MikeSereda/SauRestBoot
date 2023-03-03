package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ParameterSetService {

    //All modems parameters
    Map<String, List<ParameterSet>> getParameters(LocalDateTime startTime, boolean reduced, int limit);
    Map<String, List<ParameterSet>> getParameters(LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);

    //Current modem parameters
    Map<String, List<ParameterSet>> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit);
    Map<String, List<ParameterSet>> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);

    //All modems deltas
    Map<String, List<ReducedParameterSet>> getDeltas(LocalDateTime startTime, int limit);
    Map<String, List<ReducedParameterSet>> getDeltas(LocalDateTime startTime, LocalDateTime endTime, int limit);

    //Current modem deltas
    Map<String, List<ReducedParameterSet>> getDeltas(String modemId, LocalDateTime startTime, int limit);
    Map<String, List<ReducedParameterSet>> getDeltas(String modemId, LocalDateTime startTime, LocalDateTime endTime, int limit);


    Map<String, List<ParameterSet>> getUpdates(HashMap<String, LocalDateTime> lastPairs, boolean reduced, int limit);

    Map<String, List<ParameterSet>> getLastUpdates(boolean relativeTime);
    Map<String, List<ParameterSet>> getLastUpdates(String modemId, boolean relativeTime);
}
