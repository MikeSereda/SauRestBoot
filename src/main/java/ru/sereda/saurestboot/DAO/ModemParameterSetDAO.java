package ru.sereda.saurestboot.DAO;

import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.util.List;

public interface ModemParameterSetDAO {

    List<ParameterSet> getParameters(boolean reduced);
}
