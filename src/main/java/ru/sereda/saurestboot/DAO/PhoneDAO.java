package ru.sereda.saurestboot.DAO;

import ru.sereda.saurestboot.businesslogic.PhoneRegion;

import java.util.List;

public interface PhoneDAO {
    List<String> getRegions();
    PhoneRegion getPhoneRegion(String city);
    String getRegionCode(String city);
}
