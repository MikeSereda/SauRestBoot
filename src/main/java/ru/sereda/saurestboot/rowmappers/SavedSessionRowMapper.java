package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TreeSet;

public class SavedSessionRowMapper implements RowMapper<Session> {
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
        Session session = new Session(startTime,endTime,duration,carrierState);
        session.setDeviceId(rs.getString("device_id"));
        session.setSessionType(rs.getInt("type"));
        Timestamp[] stamps = (Timestamp[]) rs.getArray("concated_start_times").getArray();
        TreeSet<LocalDateTime> timestamps = new TreeSet<>();
        for (Timestamp stamp : stamps){
            timestamps.add(stamp.toLocalDateTime());
        }
        session.setConcatedStartTimes(timestamps);
        session.setMasterStation(rs.getString("master_station"));
        session.setSlaveStation(rs.getString("slave_station"));
        return session;
    }
}
