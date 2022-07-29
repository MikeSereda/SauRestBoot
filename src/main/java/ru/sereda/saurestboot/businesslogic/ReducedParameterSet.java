package ru.sereda.saurestboot.businesslogic;

import java.util.HashMap;

public class ReducedParameterSet {
    public String param1 = "param1value";
    protected String param2 = "param2value";
    public String param3 = "param3value";

    public HashMap<String, Object> getParameters(){
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("param1",param1);
        parametersMap.put("param2",param2);
        parametersMap.put("param3",param3);
        return parametersMap;
    }

//    public ReducedParameterSet(HashMap<String,Object> valuesMap) {
//        this.param1 = param1;
//        this.param2 = param2;
//        this.param3 = param3;
//    }
}
