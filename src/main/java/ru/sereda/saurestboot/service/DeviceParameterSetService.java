package ru.sereda.saurestboot.service;

import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.util.List;

public interface DeviceParameterSetService {
    List<ParameterSet> getParameters(boolean reduced);
}
