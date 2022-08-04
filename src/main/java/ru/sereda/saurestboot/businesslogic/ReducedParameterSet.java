package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.HashMap;

public class ReducedParameterSet {
    public String param1 = "param1value";
    public String param2 = "param2value";
    public String param3 = "param3value";

    public ReducedParameterSet(String param1, String param2, String param3) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    @JsonIgnore
    public HashMap<String, Object> getParameters(){
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("param1",param1);
        parametersMap.put("param2",param2);
        parametersMap.put("param3",param3);
        return parametersMap;
    }

    @Override
    public String toString() {
        return "ReducedParameterSet{" +
                "param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                '}';
    }

    //    public ReducedParameterSet(HashMap<String,Object> valuesMap) {
//        this.param1 = param1;
//        this.param2 = param2;
//        this.param3 = param3;
//    }
}
