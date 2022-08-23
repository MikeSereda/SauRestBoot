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

    private List<String> getDeviceIds(){
        String sql = "SELECT DISTINCT id FROM devices";
        List<String> deviceIds = jdbcTemplate.queryForList(sql,String.class);
        return deviceIds;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime) {
        String sql = "SELECT * FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceParameterSetMapper(), modemId, startTime, defaultLimit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced) {
        String sql = "SELECT \"modemId\", \"timestampWotz\", \"ebNo\", \"ebNoRemote\" FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(), modemId, startTime, defaultLimit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        String sql = "SELECT * FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? AND \"timestampWotz\"<=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(),modemId, startTime, endTime, defaultLimit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced) {
        String sql = "SELECT \"modemId\", \"timestampWotz\", \"ebNo\", \"ebNoRemote\" FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? AND \"timestampWotz\"<=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(),modemId, startTime, endTime, defaultLimit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, int limit) {
        String sql = "SELECT * FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? AND \"timestampWotz\"<=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(), modemId, startTime, endTime, limit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, int limit, boolean reduced) {
        String sql = "SELECT \"modemId\", \"timestampWotz\", \"ebNo\", \"ebNoRemote\" FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? AND \"timestampWotz\"<=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(),modemId, startTime, endTime, limit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, int limit) {
        String sql = "SELECT * FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(),modemId, startTime, limit);
        return parameterSetList;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit) {
        String sql = "SELECT \"modemId\", \"timestampWotz\", \"ebNo\", \"ebNoRemote\" FROM parameters WHERE \"modemId\"=? AND \"timestampWotz\">=? ORDER BY \"timestampWotz\" DESC LIMIT ?";
        List<ParameterSet> parameterSetList = jdbcTemplate.query(sql, new DeviceReducedParameterSetMapper(),modemId, startTime, limit);
        return parameterSetList;
    }
}
