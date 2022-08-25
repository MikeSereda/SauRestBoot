package ru.sereda.saurestboot.rowmappers;

import ru.sereda.saurestboot.businesslogic.DeviceParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class DeviceParameterSetMapper extends DeviceReducedParameterSetMapper{
    @Override
    public ParameterSet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeviceParameterSet(
                rs.getFloat("ebNo"),
                rs.getFloat("ebNoRemote"),
                rs.getTimestamp("timestampWotz").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS),
                rs.getInt("rsl"),
                rs.getInt("temperature"),
                rs.getFloat("txPowerLevelIncrease"),
                rs.getFloat("ber"),
                rs.getString("unitAlarm"),
                rs.getString("txAlarm"),
                rs.getString("rxAlarm"),
                rs.getString("oduAlarm"),
                rs.getString("modemId")
        );
    }
}
