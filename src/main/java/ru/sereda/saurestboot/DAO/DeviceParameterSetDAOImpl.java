package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.businesslogic.DeviceParameterSet;
import ru.sereda.saurestboot.businesslogic.DeviceReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.rowmappers.DeviceMapperImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceParameterSetDAOImpl implements DeviceParameterSetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ParameterSet> getParameters(boolean reduced) {
        List<ParameterSet> parameterSetList = new ArrayList<>();
        for (int i=0;i<5;i++){
            if (reduced){
                parameterSetList.add(new DeviceReducedParameterSet("cdm111",7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
            }
            else
            {
                parameterSetList.add(new DeviceParameterSet(7.5f, 7.2f, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),66,23,2.3f,4f,"None","None","None","None", "cdm111"));
            }
        }
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, int limit) {
        String sql = "SELECT * FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? AND \"timestampWotz\"<=? ORDER BY \"timestampWotz\" ASC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceMapperImpl(),modemId, startTime, endTime, limit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime, int limit) {
        String sql = "SELECT * FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? AND \"timestampWotz\"<=? ORDER BY \"timestampWotz\" ASC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceMapperImpl(), startTime, endTime, limit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime) {
        String sql = "SELECT * FROM parameters WHERE \"timestampWotz\">=?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceMapperImpl(), startTime);
        return parameterSetList;
    }

}
