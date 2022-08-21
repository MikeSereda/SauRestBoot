package ru.sereda.saurestboot.businesslogic;

import java.util.HashMap;

public interface ParameterSet {
    HashMap<String, Object> getParametersMap();
    String toString();
}
