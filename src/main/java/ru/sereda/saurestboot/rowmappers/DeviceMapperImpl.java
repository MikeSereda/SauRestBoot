package ru.sereda.saurestboot.rowmappers;

import ru.sereda.saurestboot.businesslogic.DeviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceMapperImpl implements DeviceMapper{
    @Override
    public DeviceImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
        DeviceImpl deviceImpl = new DeviceImpl();
        deviceImpl.setId(rs.getString("id"));
        deviceImpl.setName(rs.getString("name"));
        deviceImpl.setIp(rs.getString("ip"));
        deviceImpl.setPort(rs.getInt("port"));
        deviceImpl.setLocation(rs.getString("location"));
        deviceImpl.setDescription(rs.getString("description"));
        return deviceImpl;
    }
}
