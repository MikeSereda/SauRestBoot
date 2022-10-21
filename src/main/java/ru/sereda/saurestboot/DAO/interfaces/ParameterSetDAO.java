package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public interface ParameterSetDAO {
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced,int limit);
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);
    List<ParameterSet> getParametersApproximated(String modemId, LocalDateTime startTime, boolean reduced,int limit, int approximating);
    List<ParameterSet> getParametersApproximated(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit, int approximating);
    int parameterSetCount(String modemId, LocalDateTime startTime, LocalDateTime endTime);
    int parameterSetCount(String modemId, LocalDateTime startTime);

    TreeMap<LocalDateTime,LocalDateTime> approximatingTimestamps(String modemId, int approximatingValue);

    ParameterSet getApproximatedSet(String modemId, LocalDateTime startTime, LocalDateTime endTime);

    ParameterSet getApproximatedSet(String modemId, LocalDateTime startTime);

    LocalDateTime getLastUpdateTime();
    LocalDateTime getLastUpdateTime(String modemId);
}
