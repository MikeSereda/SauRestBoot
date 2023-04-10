package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.implementations.ParametersDAO;
import ru.sereda.saurestboot.DAO.interfaces.DeviceDAO;
import ru.sereda.saurestboot.businesslogic.Parameters;
import ru.sereda.saurestboot.service.interfaces.DeviceService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ParametersService {
    @Autowired
    ParametersDAO parametersDAO;

    @Autowired
    DeviceDAO deviceDAO;

    @Value("${sql.parameters.parameterset.limit}")
    int sqlParametersetLimit;
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    @Autowired
    DeviceService deviceService;

    // TODO: 10.04.2023 select timestamp_wotz, avg as eb_no from
    //    (select timestamp_wotz, avg(eb_no) over (partition by t.row) as avg, row, lag(row) over () as row_lag from
    //     (SELECT timestamp_wotz, eb_no, (row_number() over () -1)/5 as row FROM public.parameters
    //       WHERE device_id='cdm111' AND timestamp_wotz<='2023-04-10 05:57:45' AND timestamp_wotz>='2023-04-10 04:57:35.822762' ORDER BY timestamp_wotz)
    //  as T order by T.row ) as D where row<>row_lag
    //  Это функция усреднения. Нужно передать параметр усреднения (5) при начальном запросе и его же передавать из фронта для получения следующих отсчетов с усреднением
    //  Доработать для любого набора параметров
    //  Естественно усреднять можно только числовые значения
    public Map<String, List<Parameters>> getRequiredParameters(Set<String> requiredValues, String modemId, String startTime, String endTime, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        String columnNames = columnNamesGenerator(requiredValues);

        // TODO: 06.04.2023 deviceDAO.getPossibleParameters(modemId);

        Map<String, List<Parameters>> parameters = new HashMap<>();

        LocalDateTime requiredStartTime;
        if (startTime.equals(""))
            requiredStartTime = LocalDateTime.now().minusMinutes(150);
        else
            requiredStartTime = LocalDateTime.parse(startTime,formatter);
        LocalDateTime requiredEndTime;
        if (!endTime.equals(""))
            requiredEndTime = LocalDateTime.parse(endTime,formatter);
        else
            requiredEndTime = LocalDateTime.now();
        parameters.put(modemId,parametersDAO.getRequiredParameters(columnNames, modemId, requiredStartTime, requiredEndTime, limit));
        return parameters;
    }

    public Map<String, List<Parameters>> getRequiredParameters(Set<String> requiredValues, String startTime, String endTime, int limit) {
        Map<String, List<Parameters>> deviceParametersMap = new HashMap<>();
        List<String> deviceIds = deviceService.getActiveDeviceIds();
        for (String id : deviceIds){
            deviceParametersMap.put(id, getRequiredParameters(requiredValues, id, startTime, endTime, limit).get(id));
        }
        return deviceParametersMap;
    }

    public Map<String, List<Parameters>> getUpdates(HashMap<String, LocalDateTime> lastPairs, int limit) {
        Map<String, List<Parameters>> deviceParametersMap = new HashMap<>();
        for (String deviceId : lastPairs.keySet()){
            deviceParametersMap.put(deviceId, getRequiredParameters(updateParameterSet,deviceId,lastPairs.get(deviceId).toString(),"",limit).get(deviceId));
        }
        return deviceParametersMap;
    }

//    private Map<String, List<Parameters>> getApproximatedRequiredParameters

    private String[] possibleParameters = new String[]{
            "eb_no",
            "eb_no_remote"
    };
    private String[] nardaPossibleParameters = new String[]{
            "avg_json",
            "max_avg_json",
            "min_avg_json"
    };
    private Set<String> possibleParametersSet = new TreeSet<>(Set.of(possibleParameters));

    private Set<String> updateParameterSet = new TreeSet<>(Set.of(new String[]{
            "eb_no",
            "eb_no_remote"
    }));

    private final Set<String> defaultRequestedValues = new TreeSet<>(Set.of(new String[]{
            "eb_no",
            "eb_no_remote"
    }));

    private String columnNamesGenerator(Set<String> requiredValues){
        if (requiredValues == null){
            requiredValues = new TreeSet<>();
            requiredValues.addAll(defaultRequestedValues);
        }
        requiredValues.retainAll(possibleParametersSet);
        StringBuilder stringBuilder = new StringBuilder();
        // TODO: 07.04.2023 rework with iterator
        for (String parameter : requiredValues){
            stringBuilder.append(parameter).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
