package ru.sereda.saurestboot.service;

import ru.sereda.saurestboot.businesslogic.PhoneRegion;

import java.util.List;

public interface PhoneService {
    List<String> getRegions();

    String getRegionCode(String city);

    List<PhoneRegion> getPhones();

    PhoneRegion getPhones(String city);
}
