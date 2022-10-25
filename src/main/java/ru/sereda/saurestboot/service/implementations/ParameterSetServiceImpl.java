package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.ParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.service.interfaces.ParameterSetService;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.PhoneService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParameterSetServiceImpl implements ParameterSetService {
    @Autowired
    PhoneService phoneService;

    @Autowired
    ParameterSetDAO parameterSetDAO;

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
        List<String> deviceIds = deviceService.getActiveDeviceIds();
        for (String id : deviceIds){
            deviceParametersMap.put(id,getParameters(id,startTime, reduced, limit).get(id));
        }
        return deviceParametersMap;
    }

    @Override
    public Map<String, List<ParameterSet>> getParameters(LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        Map<String, List<ParameterSet>> deviceParametersMap = new HashMap<>();
        List<String> deviceIds = deviceService.getActiveDeviceIds();
        for (String id : deviceIds){
            deviceParametersMap.put(id,getParameters(id,startTime, endTime, reduced, limit).get(id));
        }
        return deviceParametersMap;
    }

    @Override
    public Map<String, List<ParameterSet>> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        Map<String, List<ParameterSet>> parameters = new HashMap<>();
        parameters.put(modemId,parameterSetDAO.getParameters(modemId, startTime, reduced, limit));
        return parameters;
    }

    @Override
    public Map<String, List<ParameterSet>> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        Map<String, List<ParameterSet>> parameters = new HashMap<>();
        int count = parameterSetDAO.parameterSetCount(modemId,startTime,endTime);
//        if (count>1500){
            int approximating = count / 1000;
            System.out.println(approximating);
            List<ParameterSet> parameterSetList = new ArrayList<>();
            Map<LocalDateTime, LocalDateTime> timePairs = parameterSetDAO.approximatingTimestamps(modemId,approximating);
            System.out.println(timePairs);
            for (LocalDateTime start : timePairs.keySet()){
                LocalDateTime end = timePairs.get(start);
                if (end!=null){
                    parameterSetList.add(parameterSetDAO.getApproximatedSet(modemId,start,timePairs.get(start)));
                }
                else{
                    parameterSetList.add(parameterSetDAO.getApproximatedSet(modemId,start));
                }
            }
            parameters.put(modemId,parameterSetList);
//        }
//        else {
//            parameters.put(modemId,parameterSetDAO.getParameters(modemId, startTime, endTime, reduced, limit));
//        }
        parameters.put(modemId,parameterSetDAO.getParameters(modemId, startTime, endTime, reduced, limit));
        return parameters;
    }

    @Override
    public Map<String, List<ParameterSet>> getUpdates(HashMap<String, LocalDateTime> lastPairs, boolean reduced, int limit) {
        Map<String, List<ParameterSet>> parameters = new HashMap<>();
        for (String deviceId : lastPairs.keySet()){
            List<ParameterSet> currentParameterSet = new ArrayList<>(getParameters(deviceId, lastPairs.get(deviceId), reduced, limit).get(deviceId));
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
        List<ParameterSet> parameterSetList = parameterSetDAO.getParameters(modemId,upFrom,true,500);
        parameters.put(modemId,parameterSetList);
        return parameters;
    }
}
