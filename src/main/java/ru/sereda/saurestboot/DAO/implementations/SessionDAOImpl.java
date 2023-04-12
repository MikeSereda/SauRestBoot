package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sereda.saurestboot.DAO.interfaces.SessionDAO;
import ru.sereda.saurestboot.businesslogic.Session;
import ru.sereda.saurestboot.rowmappers.SessionMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional
public class SessionDAOImpl implements SessionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.parameters.parameterset.interval}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime lag;

    @Override
    public List<Session> getSessions(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        String sql =
        """
        SELECT timestamp_wotz AS start_time, timestamp_wotz_lag AS end_time, timestamp_wotz_lag-timestamp_wotz AS duration, carrier FROM
        (SELECT *, LAG(timestamp_wotz) over () as timestamp_wotz_lag FROM
            (SELECT timestamp_wotz, eb_no, lag(carrier) over () as carrier FROM public.parameters
                WHERE device_id=? AND timestamp_wotz>? AND timestamp_wotz<? ORDER BY timestamp_wotz desc)
            as T WHERE T.eb_no<0)
        AS D WHERE timestamp_wotz_lag-timestamp_wotz>?
        """;
        return jdbcTemplate.query(sql,new SessionMapper(),modemId, startTime, endTime, lag);
    }
}
