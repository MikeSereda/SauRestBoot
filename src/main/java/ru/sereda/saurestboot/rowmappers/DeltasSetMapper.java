package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.sereda.saurestboot.businesslogic.ReducedParameterSet;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class DeltasSetMapper implements RowMapper<ReducedParameterSet> {
    @Override
    public ReducedParameterSet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReducedParameterSet(
                rs.getFloat("eb_no_delta"),
                rs.getFloat("eb_no_remote_delta"),
                rs.getTimestamp("timestamp_wotz").toLocalDateTime().truncatedTo(ChronoUnit.SECONDS)
        );
    }
}