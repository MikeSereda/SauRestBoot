package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.ModemParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ModemReducedParameterSet;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ModemParameterSetDAOImpl implements ModemParameterSetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ParameterSet> getParameters(boolean reduced) {
        List<ParameterSet> parameterSetList = new ArrayList<>();
        for (int i=0;i<5;i++){
            if (reduced){
                parameterSetList.add(new ModemReducedParameterSet("cdm111",7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
            }
            else
            {
                parameterSetList.add(new ModemParameterSet(7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),66,23,2.3f,4f,"None","None","None","None", "cdm111"));
            }
        }
        return parameterSetList;
    }
}
