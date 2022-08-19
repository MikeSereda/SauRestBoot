package ru.sereda.saurestboot.DAO;

import ru.sereda.saurestboot.businesslogic.ParameterCarrier;

import java.util.List;

public interface ParameterSetDAO {

    List<ParameterCarrier> getParameters(boolean reduced);
    void getSessions();
}
