package ru.sereda.saurestboot.businesslogic;

import java.util.HashMap;

public interface ParameterCarrier {
    public HashMap<String, Object> getParametersMap();
    public String toString();
}
