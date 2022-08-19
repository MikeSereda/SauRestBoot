package ru.sereda.saurestboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.ParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterCarrier;

import java.util.List;

@Service
public class ParameterSetServiceImpl implements ParameterSetService{

    @Autowired
    ParameterSetDAO parameterSetDAO;

    @Override
    public List<ParameterCarrier> getParameters(boolean reduced) {
        return parameterSetDAO.getParameters(reduced);
    }

    @Override
    public void getSessions() {
//        return  parameterSetDAO.getSessions();
    }
}
