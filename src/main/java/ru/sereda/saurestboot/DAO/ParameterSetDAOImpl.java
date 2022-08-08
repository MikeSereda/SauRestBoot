package ru.sereda.saurestboot.DAO;

import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.ParameterCarrier;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParameterSetDAOImpl implements ParameterSetDAO{
    @Override
    public List<ParameterCarrier> getParameters(boolean reduced) {
        List<ParameterCarrier> parameterSetList = new ArrayList<>();
        for (int i=0;i<5;i++){
            if (reduced){
                parameterSetList.add(new ReducedParameterSet(7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
            }
            else
            {
                parameterSetList.add(new ParameterSet(7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),66,23,2.3f,4f,"None","None","None","None"));
            }
        }
        return parameterSetList;
    }
}
