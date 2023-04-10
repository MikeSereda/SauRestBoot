package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.Parameters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParametersMapper implements RowMapper<Parameters> {
    private final List<String> valuesList = new ArrayList<>();
    public ParametersMapper(String requiredValues) {
        valuesList.add("timestamp_wotz");
        valuesList.addAll(Arrays.stream(requiredValues.split(", ")).toList());
    }

    @Override
    public Parameters mapRow(ResultSet rs, int rowNum) throws SQLException {
        HashMap<String, Object> valuesMap = new HashMap<>();
        for (String value : valuesList){
            valuesMap.put(value,rs.getObject(value));
        }
        Parameters parameters = new Parameters();
        parameters.setValues(valuesMap);
        return parameters;
    }
}
