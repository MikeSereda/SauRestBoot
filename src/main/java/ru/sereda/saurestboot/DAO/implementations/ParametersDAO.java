package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.businesslogic.Parameters;
import ru.sereda.saurestboot.rowmappers.ParametersMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ParametersDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public LocalDateTime getLastUpdateTime() {
        String sql = "SELECT timestamp_wotz FROM parameters ORDER BY timestamp_wotz DESC LIMIT 1;";
        return jdbcTemplate.queryForObject(sql,LocalDateTime.class);
    }

    public LocalDateTime getLastUpdateTime(String modemId) {
        String sql = "SELECT timestamp_wotz FROM parameters WHERE device_id=? ORDER BY timestamp_wotz DESC LIMIT 1;";
        return jdbcTemplate.queryForObject(sql,LocalDateTime.class,modemId);
    }

    public List<Parameters> getRequiredParameters(
            String requiredValues, String modemId, LocalDateTime requiredStartTime,
            LocalDateTime requiredEndTime, int limit) {
        ParametersMapper mapper = new ParametersMapper(requiredValues);
        String sql = "SELECT * FROM( SELECT timestamp_wotz, "+
                requiredValues+
                " FROM parameters WHERE device_id=? AND timestamp_wotz>? AND timestamp_wotz<? ORDER BY timestamp_wotz DESC LIMIT ?) AS T ORDER BY timestamp_wotz";
        return jdbcTemplate.query(sql, mapper, modemId, requiredStartTime, requiredEndTime, limit);
    }
}
