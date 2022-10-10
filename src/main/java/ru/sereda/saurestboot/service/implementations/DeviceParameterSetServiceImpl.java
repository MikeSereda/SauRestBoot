package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.DeviceParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.DeviceService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceParameterSetServiceImpl implements DeviceParameterSetService {

    @Autowired
    DeviceParameterSetDAO parameterSetDAO;

    @Autowired
    DeviceService deviceService;

    @Value("${sql.parameters.parameterset.limit}")
    int sqlParametersetLimit;

    @Override
    public Map<String, List<ParameterSet>> getParameters(LocalDateTime startTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        Map<String, List<ParameterSet>> deviceParametersMap = new HashMap<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParametersMap.put(id,getParameters(id,startTime, reduced, limit));
        }
        return deviceParametersMap;
    }

    @Override
    public Map<String, List<ParameterSet>> getParameters(LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        Map<String, List<ParameterSet>> deviceParametersMap = new HashMap<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParametersMap.put(id,getParameters(id,startTime, endTime, reduced, limit));
        }
        return deviceParametersMap;
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
    public Map<String, List<ParameterSet>> getUpdates(HashMap<String, LocalDateTime> lastPairs, boolean reduced, int limit) {
        Map<String, List<ParameterSet>> parameters = new HashMap<>();
        for (String deviceId : lastPairs.keySet()){
            List<ParameterSet> currentParameterSet = new ArrayList<>();
            currentParameterSet.addAll(getParameters(deviceId,lastPairs.get(deviceId), reduced, limit));
            if (currentParameterSet.size()>0){
                if (lastPairs.get(deviceId)==currentParameterSet.get(0).getParametersMap().get("timestampWotz")){
                    currentParameterSet.remove(0);
                }
            }
            parameters.put(deviceId,currentParameterSet);
        }
        return parameters;
    }


    @Override
    public Map<String, List<ParameterSet>> getLastUpdates(boolean relativeTime) {
        Map<String, List<ParameterSet>> parameters = new HashMap<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            parameters.putAll(getLastUpdates(id,relativeTime));
        }
        return parameters;
    }

    @Override
    public Map<String, List<ParameterSet>> getLastUpdates(String modemId,boolean relativeTime) {
        LocalDateTime upFrom;
        if (relativeTime){
            upFrom = parameterSetDAO.getLastUpdateTime(modemId).truncatedTo(ChronoUnit.SECONDS).minusHours(2);
        }
        else {
            upFrom = LocalDateTime.now().minusHours(2);
        }
        Map<String, List<ParameterSet>> parameters = new HashMap<>();
        List<ParameterSet> parameterSetList = parameterSetDAO.getParameters(modemId,upFrom,true,2500);
        parameters.put(modemId,parameterSetList);
        return parameters;
    }
}
