package ru.sereda.saurestboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        String sql = "SELECT * FROM devices WHERE type=?";
        List<Device> devices = jdbcTemplate.query(sql, new DeviceMapperImpl(), deviceType);
        return devices;
    }

    @Override
    public List<String> getDeviceTypes() {
        String sql = "SELECT DISTINCT type FROM devices order by type";
        List<String> types = jdbcTemplate.queryForList(sql,String.class);
        return types;
    }

    @Override
    public List<String> getDeviceIds() {
        String sql = "SELECT DISTINCT id FROM devices order by id";
        List<String> deviceIds = jdbcTemplate.queryForList(sql,String.class);
        return deviceIds;
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
