package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class ParameterSet extends ReducedParameterSet{
    public String param4 = "param4value";
    public String param5 = "param5value";
    public String param6 = "param6value";

    public ParameterSet(String param1, String param2, String param3, String param4, String param5, String param6) {
        super(param1, param2, param3);
        this.param4 = param4;
        this.param5 = param5;
        this.param6 = param6;
    }

    @Override
    public HashMap<String, Object> getParameters() {
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("param1",super.param1);
        parametersMap.put("param2",super.param2);
        parametersMap.put("param3",super.param3);
        parametersMap.put("param4",param4);
        parametersMap.put("param5",param5);
        parametersMap.put("param6",param6);
        return parametersMap;
    }

    @Override
    public String toString() {
        return "ParameterSet{" +
                "param4='" + param4 + '\'' +
                ", param5='" + param5 + '\'' +
                ", param6='" + param6 + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                '}';
    }

    //    public ParameterSet(HashMap<String, Object> valuesMap) {
//        super(valuesMap);
//    }
}
