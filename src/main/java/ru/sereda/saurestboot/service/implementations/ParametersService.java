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

    // TODO: 06.04.2023 deviceDAO.getPossibleParameters(modemId);

    public Map<String, List<Parameters>> getRequiredParameters(Set<String> requiredValues, String modemId, String startTime, String endTime, int limit) {
        if (limit>sqlParametersetLimit){
            limit = sqlParametersetLimit;
        }
        String columnNames = columnNamesGenerator(requiredValues);

        Map<String, List<Parameters>> parameters = new HashMap<>();

        LocalDateTime requiredStartTime = null;
        LocalDateTime requiredEndTime = null;

        if (startTime.equals("") && endTime.equals("")){
            requiredStartTime = LocalDateTime.now().minusMinutes(150);
            requiredEndTime = LocalDateTime.now();
        }
        else {
            if (!startTime.equals("") && !endTime.equals("")){
                requiredStartTime = LocalDateTime.parse(startTime,formatter);
                requiredEndTime = LocalDateTime.parse(endTime,formatter);
            }
            else {
                if (startTime.equals("")){
                    requiredEndTime = LocalDateTime.parse(endTime,formatter);
                    requiredStartTime = requiredEndTime.minusMinutes(150);
                }
                if (endTime.equals("")){
                    requiredStartTime = LocalDateTime.parse(startTime,formatter);
                    requiredEndTime = requiredStartTime.plusMinutes(150);
                }
            }
        }
//        if (startTime.equals(""))
//            requiredStartTime = LocalDateTime.now().minusMinutes(150);
//        else
//            requiredStartTime = LocalDateTime.parse(startTime,formatter);
//        if (!endTime.equals(""))
//            requiredEndTime = LocalDateTime.parse(endTime,formatter);
//        else
//            requiredEndTime = LocalDateTime.now();
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

    private final Set<String> nardaPossibleParametersSet = new TreeSet<>(Set.of(
            "act_json",
            "max_json",
            "min_json",
            "avg_json",
            "max_avg_json",
            "min_avg_json"
    ));
    private final Set<String> possibleParametersSet = new TreeSet<>(Set.of(
            "eb_no",
            "eb_no_remote",
            "eb_no_delta",
            "eb_no_remote_delta",
            "carrier",
            "faults",
            "ber",
            "temperature",
            "power_level",
            "power_level_increase",
            "rsl",
            "freq_offset",
            "reachable",
            "test",
            "framing",
            "delay",
            "tx_alarm",
            "rx_alarm",
            "odu_alarm",
            "unit_alarm"));

    private final Set<String> updateParameterSet = new TreeSet<>(Set.of(
            "eb_no",
            "eb_no_remote"
    ));

    private final Set<String> defaultRequestedValues = new TreeSet<>(Set.of(
            "eb_no",
            "eb_no_remote"
    ));

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

//      select timestamp_wotz, avg as eb_no from
//          (select timestamp_wotz, avg(eb_no) over (partition by t.row) as avg, row, lag(row) over () as row_lag from
//              (SELECT timestamp_wotz, eb_no, (row_number() over () -1)/5 as row FROM public.parameters
//          WHERE device_id='cdm111' AND timestamp_wotz<='2023-04-10 05:57:45' AND timestamp_wotz>='2023-04-10 04:57:35.822762' ORDER BY timestamp_wotz)
//      as T order by T.row ) as D where row<>row_lag
//
//  Это функция усреднения. Нужно передать параметр усреднения (5) при начальном запросе и его же передавать из фронта для получения следующих отсчетов с усреднением
//  Доработать для любого набора параметров
//  Естественно усреднять можно только числовые значения