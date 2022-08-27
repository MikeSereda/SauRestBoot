package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sereda.saurestboot.businesslogic.Session;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class SessionDAOImpl implements SessionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.parameters.session.interval}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime lag;
    @Override
    public List<Session> getSessions(String modemId, LocalDateTime startTime) {
        String sql = """
                SELECT modem_id,  timestamp_wotz as start_time, timestamp_wotz + lead as end_time, lead as duration FROM (
                SELECT modem_id, timestamp_wotz, lead(timestamp_wotz)OVER(PARTITION BY modem_id ORDER BY timestamp_wotz) -timestamp_wotz as lead
                FROM parameters WHERE eb_no<'0' ORDER BY timestamp_wotz) AS T WHERE lead>? AND modem_id=? AND timestamp_wotz>=? ORDER BY start_time
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql, lag, modemId, startTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }

    @Override
    public List<Session> getSessions(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        String sql = """
                SELECT modem_id,  timestamp_wotz as start_time, timestamp_wotz + lead as end_time, lead as duration FROM (
                SELECT modem_id, timestamp_wotz, lead(timestamp_wotz)OVER(PARTITION BY modem_id ORDER BY timestamp_wotz) -timestamp_wotz as lead
                FROM parameters WHERE eb_no<'0' ORDER BY timestamp_wotz) AS T WHERE lead>? AND modem_id=? AND timestamp_wotz>=? AND timestamp_wotz+lead<=? ORDER BY start_time
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql, lag, modemId, startTime, endTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }

    @Override
    public List<Session> getSessions(LocalDateTime startTime) {
        String sql = """
                SELECT modem_id,  timestamp_wotz as start_time, timestamp_wotz + lead as end_time, lead as duration FROM (
                SELECT modem_id, timestamp_wotz, lead(timestamp_wotz)OVER(PARTITION BY modem_id ORDER BY timestamp_wotz) -timestamp_wotz as lead
                FROM parameters WHERE eb_no<'0' ORDER BY timestamp_wotz) AS T WHERE lead>? AND timestamp_wotz>=? ORDER BY start_time
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql, lag, startTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }

    @Override
    public List<Session> getSessions(LocalDateTime startTime, LocalDateTime endTime) {
        String sql = """
                SELECT modem_id,  timestamp_wotz as start_time, timestamp_wotz + lead as end_time, lead as duration FROM (
                SELECT modem_id, timestamp_wotz, lead(timestamp_wotz)OVER(PARTITION BY modem_id ORDER BY timestamp_wotz) -timestamp_wotz as lead
                FROM parameters WHERE eb_no<'0' ORDER BY timestamp_wotz) AS T WHERE lead>? AND timestamp_wotz>=? AND timestamp_wotz+lead<=? ORDER BY start_time
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql, lag, startTime, endTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }
}
