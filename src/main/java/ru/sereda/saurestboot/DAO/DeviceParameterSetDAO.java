package ru.sereda.saurestboot.DAO;

import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.time.LocalDateTime;
import java.util.List;

public interface DeviceParameterSetDAO {
    List<ParameterSet> getParameters(boolean reduced);
    List<ParameterSet> getParameters(LocalDateTime startTime);
//    List<ParameterSet> getParameters(LocalDateTime startTime,LocalDateTime endTime);
    List<ParameterSet> getParameters(LocalDateTime startTime,LocalDateTime endTime, int limit);
//    List<ParameterSet> getParameters(LocalDateTime startTime,LocalDateTime endTime, int limit, boolean reduced);
//    List<ParameterSet> getParameters(LocalDateTime startTime,LocalDateTime endTime, boolean reduced);
//    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime);
//    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime,LocalDateTime endTime);
    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime,LocalDateTime endTime, int limit);
//    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime,LocalDateTime endTime, int limit, boolean reduced);
//    List<ParameterSet> getParameters(String modemId, LocalDateTime startTime,LocalDateTime endTime, boolean reduced);
}
