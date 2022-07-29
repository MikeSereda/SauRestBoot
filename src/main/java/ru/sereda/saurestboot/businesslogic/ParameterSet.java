package ru.sereda.saurestboot.businesslogic;

import java.util.HashMap;

public class ParameterSet extends ReducedParameterSet{
    public String param4 = "param4value";
    private String param5 = "param5value";
    public String param6 = "param6value";

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

    //    public ParameterSet(HashMap<String, Object> valuesMap) {
//        super(valuesMap);
//    }
}
