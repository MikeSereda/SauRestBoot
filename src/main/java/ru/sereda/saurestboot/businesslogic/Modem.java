package ru.sereda.saurestboot.businesslogic;

public class Modem extends Device{
    private static String deviceType="cdm 570l";
    private String name;
    private String id;
    private String ip;
    private int port;
    private String description;
    private String location;

    public Modem() {
    }

    public static String getDeviceType() {
        return deviceType;
    }

    public static void setDeviceType(String deviceType) {
        Modem.deviceType = deviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
