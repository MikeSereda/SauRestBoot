package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sereda.saurestboot.DAO.interfaces.PhoneDAO;
import ru.sereda.saurestboot.businesslogic.PhoneRegion;
import ru.sereda.saurestboot.rowmappers.PhoneMapMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class PhoneDAOImpl implements PhoneDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getRegions(){
        String sql = "SELECT city FROM cities";
        return jdbcTemplate.queryForList(sql,String.class);
    }

    public String getRegionCode(String city){
        String sql = "SELECT phone_code FROM cities WHERE city=?";
        List<String> regionCode = jdbcTemplate.queryForList(sql,String.class,city);
        if (regionCode.size()==1){
            return regionCode.get(0);
        }
        else {
            return null;
        }
    }


    public List<PhoneRegion> getPhoneRegions() {
        List<PhoneRegion> phoneRegions = new ArrayList<>();
        for (String city: getRegions()){
            PhoneRegion phoneRegion = new PhoneRegion();
            phoneRegion.setCity(city);
            phoneRegion.setCityCode(getRegionCode(city));
            String sql = "SELECT name, phone FROM phonebook WHERE city=?";
            for(Map<String,String> map : jdbcTemplate.query(sql,new PhoneMapMapper(),city)){
                phoneRegion.getSubscribers().putAll(map);
            }
            phoneRegions.add(phoneRegion);
        }
        return phoneRegions;
    }

    @Override
    public PhoneRegion getPhoneRegion(String city) {
        PhoneRegion phoneRegion = new PhoneRegion();
        phoneRegion.setCity(city);
        String regionCode = getRegionCode(city);
        if (regionCode==null){
            return null;
        }
        else{
            phoneRegion.setCityCode(getRegionCode(city));
            String sql = "SELECT name, phone FROM phonebook WHERE city=?";
            for (Map<String, String> map : jdbcTemplate.query(sql, new PhoneMapMapper(), city)) {
                phoneRegion.getSubscribers().putAll(map);
            }
            return phoneRegion;
        }
    }
}
