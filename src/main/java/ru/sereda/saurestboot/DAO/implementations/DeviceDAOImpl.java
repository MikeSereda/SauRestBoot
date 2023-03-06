package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sereda.saurestboot.DAO.interfaces.DeviceDAO;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.rowmappers.DeviceMapperImpl;

import java.util.List;

@Transactional
@Repository
public class DeviceDAOImpl implements DeviceDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public List<Device> getDevices(String deviceType) {
        String sql = "SELECT * FROM devices WHERE device_type=?";
        List<Device> devices = jdbcTemplate.query(sql, new DeviceMapperImpl(), deviceType);
        return devices;
    }

    @Override
    public List<String> getDeviceTypes() {
        String sql = "SELECT DISTINCT device_type FROM devices order by device_type";
        List<String> types = jdbcTemplate.queryForList(sql,String.class);
        return types;
    }

    @Override
    public List<String> getDeviceIds() {
        String sql = "SELECT DISTINCT id FROM devices order by id";
        List<String> deviceIds = jdbcTemplate.queryForList(sql,String.class);
        return deviceIds;
    }

    @Override
    public List<String> getActiveDeviceIds() {
        String sql = "SELECT DISTINCT id FROM devices WHERE active=true order by id";
        List<String> deviceIds = jdbcTemplate.queryForList(sql,String.class);
        return deviceIds;
    }

    @Override
    public List<Device> addDevices(List<Device> addingDevices) {
        return null; // TODO: 06.03.2023 add sql for adding devices
    }

    @Override
    public Device removeDevice(String deviceId) {
        return null; // TODO: 06.03.2023 add sql for deleting devices
    }

    public List<Device> getDevices(){
        String sql = "SELECT * FROM devices";
        List<Device> devices = jdbcTemplate.query(sql, new DeviceMapperImpl());
        return devices;
    }

    @Override
    public Device getDevice(String id) {
        String sql = "SELECT * FROM devices WHERE id=?";
        List<Device> devices = jdbcTemplate.query(sql, new DeviceMapperImpl(),id);
        if (devices.size()==1){
            return devices.get(0);
        }
        else {
            return null;
        }
    }
}
