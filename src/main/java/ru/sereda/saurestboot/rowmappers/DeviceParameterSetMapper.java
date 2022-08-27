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
                rs.getFloat("eb_no"),
                rs.getFloat("eb_no_remote"),
                rs.getTimestamp("timestamp_wotz").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS),
                rs.getInt("rsl"),
                rs.getInt("temperature"),
                rs.getFloat("tx_power_level_increase"),
                rs.getFloat("ber"),
                rs.getString("unit_alarm"),
                rs.getString("tx_alarm"),
                rs.getString("rx_alarm"),
                rs.getString("odu_alarm"),
                rs.getString("modem_id"),
                rs.getBoolean("reachable"),
                rs.getString("asker_version")
        );
    }
}
