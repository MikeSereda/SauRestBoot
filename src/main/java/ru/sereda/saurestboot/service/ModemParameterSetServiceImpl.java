package ru.sereda.saurestboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.ModemParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.util.List;

@Service
public class ModemParameterSetServiceImpl implements ModemParameterSetService {

    @Autowired
    ModemParameterSetDAO parameterSetDAO;

    @Override
    public List<ParameterSet> getParameters(boolean reduced) {
        return parameterSetDAO.getParameters(reduced);
    }

}
