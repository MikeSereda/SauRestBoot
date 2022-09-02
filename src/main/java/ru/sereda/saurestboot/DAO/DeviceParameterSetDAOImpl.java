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

    @Override
    public List<ParameterSet> getParametersApproximated(String modemId, LocalDateTime startTime, boolean reduced, int limit, int approximating) {
        String sql;
        DeviceReducedParameterSetMapper mapper;
        if (reduced){
            mapper = new DeviceReducedParameterSetMapper();
            sql = """
                    WITH counted_table AS
                        (SELECT modem_id, timestamp_wotz, eb_no, eb_no_remote, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters
                        WHERE modem_id=? AND timestamp_wotz>? ORDER BY timestamp_wotz DESC LIMIT ?)
                    SELECT * from counted_table WHERE row_num % ? = 0 ORDER BY timestamp_wotz LIMIT ?;
                    """;
        }
        else {
            mapper = new DeviceParameterSetMapper();
            sql = """
                    WITH counted_table AS
                        (SELECT *, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters
                        WHERE modem_id=? AND timestamp_wotz>? ORDER BY timestamp_wotz DESC LIMIT ?)
                    SELECT * from counted_table WHERE row_num % ? = 0 ORDER BY timestamp_wotz LIMIT ?;
                    """;
        }
        return jdbcTemplate.query(sql, mapper, modemId, startTime, limit*approximating, approximating, limit);
    }

    @Override
    public List<ParameterSet> getParametersApproximated(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced, int limit, int approximating) {
        String sql;
        DeviceReducedParameterSetMapper mapper;
        if (reduced){
            mapper = new DeviceReducedParameterSetMapper();
            sql = """
                    WITH counted_table AS
                        (SELECT modem_id, timestamp_wotz, eb_no, eb_no_remote, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters
                        WHERE modem_id=? AND timestamp_wotz>? AND timestamp_wotz<? ORDER BY timestamp_wotz DESC LIMIT ?)
                    SELECT * from counted_table WHERE row_num % ? = 0 ORDER BY timestamp_wotz LIMIT ?;
                    """;
        }
        else {
            mapper = new DeviceParameterSetMapper();
            sql = """
                    WITH counted_table AS
                        (SELECT *, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters
                        WHERE modem_id=? AND timestamp_wotz>? AND timestamp_wotz<? ORDER BY timestamp_wotz DESC LIMIT ?)
                    SELECT * from counted_table WHERE row_num % ? = 0 ORDER BY timestamp_wotz LIMIT ?;
                    """;
        }
        return jdbcTemplate.query(sql, mapper, modemId, startTime, endTime, limit*approximating, approximating, limit);
    }
}
