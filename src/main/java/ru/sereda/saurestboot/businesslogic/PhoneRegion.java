package ru.sereda.saurestboot.businesslogic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class PhoneRegion {
    private String city;
    private String cityCode;
    private Map<String, String> subscribers;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Map<String, String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Map<String, String> subscribers) {
        this.subscribers = subscribers;
    }

    public PhoneRegion() {
        this.subscribers = new HashMap<>();
    }

    public PhoneRegion(String city, String cityCode, Map<String, String> subscribers) {
        this.city = city;
        this.cityCode = cityCode;
        this.subscribers = subscribers;
    }

    @JsonIgnore
    public void addSubscriber(String subscriber, String phoneNumber){
        this.subscribers.put(subscriber,phoneNumber);
    }

    @JsonIgnore
    public String getFullPhone(String subscriber){
        return cityCode+subscribers.get(subscriber);
    }
}
