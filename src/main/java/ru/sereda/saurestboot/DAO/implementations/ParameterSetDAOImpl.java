package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.DAO.interfaces.ParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterSet;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;
import ru.sereda.saurestboot.rowmappers.DeviceParameterSetMapper;
import ru.sereda.saurestboot.rowmappers.DeviceReducedParameterSetMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;

@Repository
public class ParameterSetDAOImpl implements ParameterSetDAO {

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
                    SELECT timestamp_wotz, eb_no, eb_no_remote FROM parameters WHERE modem_id=?
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
                    SELECT timestamp_wotz, eb_no, eb_no_remote FROM parameters WHERE modem_id=?
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
                        (SELECT timestamp_wotz, eb_no, eb_no_remote, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters
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
                        (SELECT timestamp_wotz, eb_no, eb_no_remote, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters
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

    @Override
    public int parameterSetCount(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        String sql = "SELECT count(*) FROM parameters WHERE modem_id=? AND timestamp_wotz>? AND timestamp_wotz<?";
        return jdbcTemplate.queryForObject(sql,int.class,modemId,startTime,endTime);
    }

    @Override
    public int parameterSetCount(String modemId, LocalDateTime startTime) {
        String sql = "SELECT count(*) FROM parameters WHERE modem_id=? AND timestamp_wotz>?";
        return jdbcTemplate.queryForObject(sql,int.class,modemId,startTime);
    }

    @Override
    public TreeMap<LocalDateTime, LocalDateTime> approximatingTimestamps(String modemId, int approximatingValue) {
        String sql = """
                WITH counted_table AS 
                (SELECT timestamp_wotz, ROW_NUMBER() OVER (ORDER BY timestamp_wotz) AS row_num FROM parameters 
                WHERE modem_id=? ORDER BY timestamp_wotz DESC LIMIT ?) 
                SELECT timestamp_wotz as start, lag(timestamp_wotz) OVER () as end from counted_table WHERE row_num % ? = 0 ORDER BY timestamp_wotz LIMIT ?;
                """;
        return null;
    }

    @Override
    public ParameterSet getApproximatedSet(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        String sql = """
                SELECT AVG(eb_no) as eb_no, AVG(eb_no_remote) as eb_no_remote FROM parameters
                WHERE modem_id='cdm111' AND timestamp_wotz > ? AND timestamp_wotz < ?
                """;
        return new ReducedParameterSet(0,0, LocalDateTime.now());
    }

    @Override
    public ParameterSet getApproximatedSet(String modemId, LocalDateTime startTime) {
        return null;
    }

    @Override
    public LocalDateTime getLastUpdateTime() {
        String sql = "SELECT timestamp_wotz FROM parameters ORDER BY timestamp_wotz DESC LIMIT 1;";
        return jdbcTemplate.queryForObject(sql,LocalDateTime.class);
    }

    @Override
    public LocalDateTime getLastUpdateTime(String modemId) {
        String sql = "SELECT timestamp_wotz FROM parameters WHERE modem_id=? ORDER BY timestamp_wotz DESC LIMIT 1;";
        return jdbcTemplate.queryForObject(sql,LocalDateTime.class,modemId);
    }
}
