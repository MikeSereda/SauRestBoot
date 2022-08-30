package ru.sereda.saurestboot.rowmappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PhoneMapMapper implements RowMapper<Map<String,String>> {
    @Override
    public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String,String> map = new HashMap<>();
        map.put(rs.getString("name"), rs.getString("phone"));
        return map;
    }
}
