package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        LocalDateTime startTime = rs.getObject("start_time", Timestamp.class).toLocalDateTime();
        LocalDateTime endTime = rs.getObject("end_time", Timestamp.class).toLocalDateTime();
        long duration = ChronoUnit.MINUTES.between(startTime,endTime);
        String carrier = rs.getString("carrier");
        byte carrierState;
        if (carrier.equals("Off"))
            carrierState=0;
        else
            carrierState=1;
        return new Session(startTime,endTime,duration,carrierState);
    }
}
