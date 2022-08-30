package ru.sereda.saurestboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.PhoneDAO;
import ru.sereda.saurestboot.businesslogic.PhoneRegion;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneDAO phoneDAO;

    @Override
    public List<String> getRegions() {
        return phoneDAO.getRegions();
    }

    @Override
    public String getRegionCode(String city) {
        return phoneDAO.getRegionCode(city);
    }

    @Override
    public List<PhoneRegion> getPhones() {
        List<PhoneRegion> phoneRegions = new ArrayList<>();
        for (String city: getRegions()){
            phoneRegions.add(getPhones(city));
        }
        return phoneRegions;
    }

    @Override
    public PhoneRegion getPhones(String city) {
        return phoneDAO.getPhoneRegion(city);
    }
}
