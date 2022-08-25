package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.DeviceParameterSet;
import ru.sereda.saurestboot.businesslogic.DeviceReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.rowmappers.DeviceMapperImpl;
import ru.sereda.saurestboot.rowmappers.DeviceParameterSetMapper;
import ru.sereda.saurestboot.rowmappers.DeviceReducedParameterSetMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceParameterSetDAOImpl implements DeviceParameterSetDAO {

    @Value("${sql.parameters.session.limit}")
    int defaultLimit;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ParameterSet> getParameters(
            String modemId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            boolean reduced,
            int limit)
    {
        String sql;
        DeviceReducedParameterSetMapper mapper;
        if (reduced){
            mapper = new DeviceReducedParameterSetMapper();
            sql = """
                    SELECT "modemId", "timestampWotz", "ebNo", "ebNoRemote" FROM parameters WHERE "modemId"=?
                    AND "timestampWotz">=? AND "timestampWotz"<=? ORDER BY "timestampWotz" DESC LIMIT ?
                    """;
        }
        else {
            mapper = new DeviceParameterSetMapper();
            sql = """
                    SELECT * FROM parameters WHERE "modemId"=? AND "timestampWotz">=? AND "timestampWotz"<=?
                    ORDER BY "timestampWotz" DESC LIMIT ?
                    """;
        }
        return jdbcTemplate.query(sql, mapper, modemId, startTime, endTime, limit);
    }

    @Override
    public List<ParameterSet> getParameters(
            String modemId,
            LocalDateTime startTime,
            boolean reduced,
            int limit)
    {
        String sql;
        DeviceReducedParameterSetMapper mapper;
        if (reduced){
            mapper = new DeviceReducedParameterSetMapper();
            sql = """
                    SELECT "modemId", "timestampWotz", "ebNo", "ebNoRemote" FROM parameters WHERE "modemId"=?
                    AND "timestampWotz">=? ORDER BY "timestampWotz" DESC LIMIT ?
                    """;
        }
        else {
            mapper = new DeviceParameterSetMapper();
            sql = """
                    SELECT * FROM parameters WHERE "modemId"=? AND "timestampWotz">=?
                    ORDER BY "timestampWotz" DESC LIMIT ?
                    """;
        }
        return jdbcTemplate.query(sql, mapper, modemId, startTime, limit);
    }
}
