package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.ExtendedParameterSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ExtendedParameterSetMapper implements RowMapper<ExtendedParameterSet> {

    private final List<String> valuesList = new ArrayList<>();

    public ExtendedParameterSetMapper(String requiredValues) {
        valuesList.addAll(Arrays.stream(requiredValues.split(", ")).toList());
    }

    @Override
    public ExtendedParameterSet mapRow(ResultSet rs, int rowNum) throws SQLException {
        HashMap<String, Object> valuesMap = new HashMap<>();
        for (String value : valuesList){
            valuesMap.put(value,rs.getObject(value));
        }
        ExtendedParameterSet parameterSet = new ExtendedParameterSet(
                (Float) valuesMap.get("eb_no"),
                (Float) valuesMap.get("eb_no_remote"),
                ((LocalDateTime) valuesMap.get("timestamp_wotz")).truncatedTo(ChronoUnit.SECONDS),
                (Integer) valuesMap.get("rsl"),
                (Integer) valuesMap.get("temperature"),
                (Float) valuesMap.get("tx_power_level_increase"),
                (Float) valuesMap.get("ber"),
                (String) valuesMap.get("unit_alarm"),
                (String) valuesMap.get("tx_alarm"),
                (String) valuesMap.get("rx_alarm"),
                (String) valuesMap.get("odu_alarm"),
                (Boolean) valuesMap.get("reachable"),
                (String) valuesMap.get("asker_version"));
        parameterSet.setTxPowerLevel((Float) valuesMap.get("power_level"));
        parameterSet.setFaults((String) valuesMap.get("faults"));
        parameterSet.setFrequencyOffset((Float) valuesMap.get("freq_offset")) ;
        parameterSet.setTestMode((Integer) valuesMap.get("test"));
        parameterSet.setFramingMode((String) valuesMap.get("framing"));
        parameterSet.setDelay((Integer) valuesMap.get("delay"));
        parameterSet.setCarrierState((String) valuesMap.get("carrier"));
        parameterSet.setEbNoDelta((Float) valuesMap.get("eb_no_delta"));
        parameterSet.setEbNoDelta((Float) valuesMap.get("eb_no_remote_delta"));
        return parameterSet;
    }
}
