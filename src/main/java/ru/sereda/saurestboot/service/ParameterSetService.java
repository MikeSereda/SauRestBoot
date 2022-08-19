package ru.sereda.saurestboot.service;

import ru.sereda.saurestboot.businesslogic.ParameterCarrier;

import java.util.List;

public interface ParameterSetService {
    List<ParameterCarrier> getParameters(boolean reduced);
    void getSessions();
}
