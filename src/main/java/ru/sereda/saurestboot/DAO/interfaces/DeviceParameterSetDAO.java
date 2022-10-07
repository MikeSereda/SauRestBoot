package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.time.LocalDateTime;
import java.util.List;

public interface DeviceParameterSetDAO {
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced,int limit);
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);
    List<ParameterSet> getParametersApproximated(String modemId, LocalDateTime startTime, boolean reduced,int limit, int approximating);
    List<ParameterSet> getParametersApproximated(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit, int approximating);
    LocalDateTime getLastUpdateTime();
    LocalDateTime getLastUpdateTime(String modemId);
}
