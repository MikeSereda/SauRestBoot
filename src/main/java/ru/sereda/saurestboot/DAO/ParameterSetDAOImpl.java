package ru.sereda.saurestboot.DAO;

import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParameterSetDAOImpl implements ParameterSetDAO{
    @Override
    public List<ReducedParameterSet> getParameters(boolean reduced) {
        List<ReducedParameterSet> parameterSetList = new ArrayList<>();
        for (int i=0;i<5;i++){
            if (reduced){
                parameterSetList.add(new ReducedParameterSet("param1 for "+i,"param2 for "+i,"param3 for "+i));
            }
            else
            {
                parameterSetList.add(new ParameterSet("param1 for "+i,"param2 for "+i,"param3 for "+i,"param4 for "+i,"param5 for "+i,"param6 for "+i));
            }
        }
        return parameterSetList;
    }
}
