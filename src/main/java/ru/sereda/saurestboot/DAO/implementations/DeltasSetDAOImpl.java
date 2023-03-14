package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;
import ru.sereda.saurestboot.rowmappers.DeltasSetMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DeltasSetDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReducedParameterSet> getParameters(
            String modemId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            int limit)
    {
        String sql;
        DeltasSetMapper mapper = new DeltasSetMapper();
        sql = """
            SELECT * FROM(
            SELECT timestamp_wotz, eb_no_delta, eb_no_remote_delta FROM parameters WHERE device_id=?
            AND timestamp_wotz>? AND timestamp_wotz<? ORDER BY timestamp_wotz DESC LIMIT ?
            ) AS T ORDER BY timestamp_wotz
            """;
        return jdbcTemplate.query(sql, mapper, modemId, startTime, endTime, limit);
    }


    public List<ReducedParameterSet> getParameters(
            String modemId,
            LocalDateTime startTime,
            int limit)
    {
        String sql;
        DeltasSetMapper mapper = new DeltasSetMapper();
        sql = """
            SELECT * FROM(
            SELECT timestamp_wotz, eb_no_delta, eb_no_remote_delta FROM parameters WHERE device_id=?
            AND timestamp_wotz>? ORDER BY timestamp_wotz DESC LIMIT ?
            ) AS T ORDER BY timestamp_wotz
            """;
        return jdbcTemplate.query(sql, mapper, modemId, startTime, limit);
    }
}
