package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class DeviceReducedParameterSetMapper implements RowMapper<ParameterSet> {
    @Override
    public ParameterSet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReducedParameterSet(
                rs.getFloat("eb_no"),
                rs.getFloat("eb_no_remote"),
                rs.getTimestamp("timestamp_wotz").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS)
        );
    }
}
