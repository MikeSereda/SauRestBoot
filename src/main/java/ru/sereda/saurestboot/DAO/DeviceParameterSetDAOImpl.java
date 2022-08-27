package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.rowmappers.DeviceParameterSetMapper;
import ru.sereda.saurestboot.rowmappers.DeviceReducedParameterSetMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DeviceParameterSetDAOImpl implements DeviceParameterSetDAO {

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
                    SELECT * FROM(
                    SELECT modem_id, timestamp_wotz, eb_no, eb_no_remote FROM parameters WHERE modem_id=?
                    AND timestamp_wotz>? AND timestamp_wotz<? ORDER BY timestamp_wotz DESC LIMIT ?
                    ) AS T ORDER BY timestamp_wotz
                    """;
        }
        else {
            mapper = new DeviceParameterSetMapper();
            sql = """
                    SELECT * FROM(
                    SELECT * FROM parameters WHERE modem_id=? AND timestamp_wotz>? AND timestamp_wotz<?
                    ORDER BY timestamp_wotz DESC LIMIT ?
                    ) AS T ORDER BY timestamp_wotz
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
                    SELECT * FROM(
                    SELECT modem_id, timestamp_wotz, eb_no, eb_no_remote FROM parameters WHERE modem_id=?
                    AND timestamp_wotz>? ORDER BY timestamp_wotz DESC LIMIT ?
                    ) AS T ORDER BY timestamp_wotz
                    """;
        }
        else {
            mapper = new DeviceParameterSetMapper();
            sql = """
                    SELECT * FROM(
                    SELECT * FROM parameters WHERE modem_id=? AND timestamp_wotz>?
                    ORDER BY timestamp_wotz DESC LIMIT ?
                    ) AS T ORDER BY timestamp_wotz
                    """;
        }
        return jdbcTemplate.query(sql, mapper, modemId, startTime, limit);
    }
}
