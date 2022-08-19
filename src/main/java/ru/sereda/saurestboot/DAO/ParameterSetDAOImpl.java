package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParameterSetDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ParameterCarrier> getParameters(boolean reduced) {
        List<ParameterCarrier> parameterSetList = new ArrayList<>();
        for (int i=0;i<5;i++){
            if (reduced){
                parameterSetList.add(new ReducedParameterSet("cdm111",7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
            }
            else
            {
                parameterSetList.add(new ParameterSet(7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),66,23,2.3f,4f,"None","None","None","None", "cdm111"));
            }
        }
        return parameterSetList;
    }

    @Override
    public void getSessions() {

    }
}
