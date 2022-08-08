package ru.sereda.saurestboot.service;

import ru.sereda.saurestboot.businesslogic.ParameterCarrier;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.util.List;

public interface ParameterSetService {
    public List<ParameterCarrier> getParameters(boolean reduced);
}
