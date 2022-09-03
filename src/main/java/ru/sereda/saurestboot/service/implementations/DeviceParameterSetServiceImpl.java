package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.DeviceParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.DeviceService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeviceParameterSetServiceImpl implements DeviceParameterSetService {

    @Autowired
    DeviceParameterSetDAO parameterSetDAO;

    @Autowired
    DeviceService deviceService;

    @Value("${sql.parameters.parameterset.limit}")
    int sqlParametersetLimit;

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, reduced, limit));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, endTime, reduced, limit));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        return parameterSetDAO.getParameters(modemId, startTime, reduced, limit);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        return parameterSetDAO.getParameters(modemId, startTime, endTime, reduced, limit);
    }

    @Override
    public List<ParameterSet> getUpdates(HashMap<String, LocalDateTime> lastPairs, boolean reduced, int limit) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        for (String deviceId : lastPairs.keySet()){
            deviceParameterSets.addAll(getParameters(deviceId,lastPairs.get(deviceId), reduced, limit));
        }
        return deviceParameterSets;
    }
}
