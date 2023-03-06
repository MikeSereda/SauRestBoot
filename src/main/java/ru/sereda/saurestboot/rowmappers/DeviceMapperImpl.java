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
        deviceImpl.setActive(rs.getBoolean("active"));
        deviceImpl.setDeviceType(rs.getString("device_type"));
        deviceImpl.setProtocol(rs.getString("protocol"));
        deviceImpl.setDevAddress(rs.getString("dev_address"));
        deviceImpl.setLogin1(rs.getString("login"));
        deviceImpl.setPassword1(rs.getString("password"));
        deviceImpl.setLogin2(rs.getString("login_alter"));
        deviceImpl.setPassword2(rs.getString("password_alter"));
        return deviceImpl;
    }
}
