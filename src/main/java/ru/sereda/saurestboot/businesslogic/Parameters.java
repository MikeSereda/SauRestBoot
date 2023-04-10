package ru.sereda.saurestboot.businesslogic;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Parameters {
    private final Map<String, Object> values = new HashMap<>();

    public Parameters() {
    }
    public void setValues(Map<String, Object> inputValues){
        for (String valueName : inputValues.keySet()){
            if (valueName.equals("timestamp_wotz"))
                values.put(valueName, ((Timestamp)inputValues.get(valueName)).toLocalDateTime().truncatedTo(ChronoUnit.SECONDS));
            else
                values.put(valueName, inputValues.get(valueName));
        }
    }


    public Map<String, Object> getValues() {
        return values;
    }
}
