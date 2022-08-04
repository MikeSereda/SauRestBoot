package ru.sereda.saurestboot.service;

import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.util.List;

public interface ParameterSetService {
    public List<ReducedParameterSet> getParameters(boolean reduced);
}
