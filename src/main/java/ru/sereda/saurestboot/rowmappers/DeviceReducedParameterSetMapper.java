package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.DeviceParameterSet;
import ru.sereda.saurestboot.businesslogic.DeviceReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class DeviceReducedParameterSetMapper implements RowMapper<ParameterSet> {
    @Override
    public ParameterSet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DeviceReducedParameterSet(
                rs.getString("modemId"),
                rs.getFloat("ebNo"),
                rs.getFloat("ebNoRemote"),
                rs.getTimestamp("timestampWotz").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS)
        );
    }
}
