package ru.sereda.saurestboot.DAO;

import ru.sereda.saurestboot.businesslogic.DeviceParameterSet;
import ru.sereda.saurestboot.businesslogic.DeviceReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.time.LocalDateTime;
import java.util.List;

public interface DeviceParameterSetDAO {
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit);
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit);
}
