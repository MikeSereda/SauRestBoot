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
                SELECT "modemId",  "timestampWotz" as "startTime", "timestampWotz" + "lead" as "endTime", "lead" as "duration" FROM (
                SELECT "modemId", "timestampWotz", lead("timestampWotz")OVER(PARTITION BY "modemId" ORDER BY "timestampWotz") -"timestampWotz"  as "lead"
                FROM parameters WHERE "ebNo"<'0' ORDER BY "timestampWotz")
                as T WHERE "lead">? AND "modemId"=? AND "timestampWotz">=? ORDER BY "startTime"
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql,
                lag, modemId, startTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }

    @Override
    public List<Session> getSessions(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        String sql = """
                SELECT "modemId",  "timestampWotz" as "startTime", "timestampWotz" + "lead" as "endTime", "lead" as "duration" FROM (
                    SELECT "modemId", "timestampWotz", lead("timestampWotz")OVER(PARTITION BY "modemId" ORDER BY "timestampWotz") -"timestampWotz"  as "lead"
                    FROM parameters WHERE "ebNo"<'0' ORDER BY "timestampWotz")
                as T WHERE "lead">? AND "modemId"=? AND "timestampWotz">=? AND "timestampWotz"+"lead"<=? ORDER BY "startTime"
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql,
                lag, modemId, startTime, endTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }

    @Override
    public List<Session> getSessions(LocalDateTime startTime) {
        String sql = """
                SELECT "modemId",  "timestampWotz" as "startTime", "timestampWotz" + "lead" as "endTime", "lead" as "duration" FROM (
                    SELECT "modemId", "timestampWotz", lead("timestampWotz")OVER(PARTITION BY "modemId" ORDER BY "timestampWotz") -"timestampWotz"  as "lead"
                    FROM parameters WHERE "ebNo"<'0' ORDER BY "timestampWotz")
                as T WHERE "lead">? AND "timestampWotz">=? ORDER BY "startTime"
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql,
                lag, startTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }

    @Override
    public List<Session> getSessions(LocalDateTime startTime, LocalDateTime endTime) {
        String sql = """
                SELECT "modemId",  "timestampWotz" as "startTime", "timestampWotz" + "lead" as "endTime", "lead" as "duration" FROM (
                    SELECT "modemId", "timestampWotz", lead("timestampWotz")OVER(PARTITION BY "modemId" ORDER BY "timestampWotz") -"timestampWotz"  as "lead"
                    FROM parameters WHERE "ebNo"<'0' ORDER BY "timestampWotz")
                as T WHERE "lead">? AND "timestampWotz">=? AND "timestampWotz"+"lead"<=? ORDER BY "startTime"
                """;
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql,
                lag, startTime, endTime);
        List<Session> sessions = new ArrayList<>();
        for (Map<String,Object> map : maps){
            sessions.add(Session.sessionWrapper(map));
        }
        return sessions;
    }
}
