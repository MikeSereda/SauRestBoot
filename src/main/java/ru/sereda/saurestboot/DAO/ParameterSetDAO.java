package ru.sereda.saurestboot.DAO;

import ru.sereda.saurestboot.businesslogic.ParameterCarrier;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.util.List;

public interface ParameterSetDAO {

    public List<ParameterCarrier> getParameters(boolean reduced);
}
