package ru.sereda.saurestboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.DeviceParameterSetDAO;
import ru.sereda.saurestboot.businesslogic.ParameterSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceParameterSetServiceImpl implements DeviceParameterSetService {

    @Autowired
    DeviceParameterSetDAO parameterSetDAO;

    @Autowired
    DeviceService deviceService;


    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, int limit) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, limit));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, endTime));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime, int limit) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id, startTime, endTime, limit));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, boolean reduced) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, reduced));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, boolean reduced, int limit) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, reduced, limit));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime, boolean reduced) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, endTime, reduced));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(LocalDateTime startTime, LocalDateTime endTime, int limit, boolean reduced) {
        List<ParameterSet> deviceParameterSets = new ArrayList<>();
        List<String> deviceIds = deviceService.getDeviceIds();
        for (String id : deviceIds){
            deviceParameterSets.addAll(getParameters(id,startTime, endTime, limit, reduced));
        }
        return deviceParameterSets;
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime) {
        return parameterSetDAO.getParameters(modemId, startTime);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, int limit) {
        return parameterSetDAO.getParameters(modemId, startTime, limit);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime) {
        return parameterSetDAO.getParameters(modemId, startTime, endTime);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, int limit) {
        return parameterSetDAO.getParameters(modemId, startTime, endTime, limit);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced) {
        return parameterSetDAO.getParameters(modemId, startTime, reduced);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, boolean reduced, int limit) {
        return parameterSetDAO.getParameters(modemId, startTime, reduced, limit);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, boolean reduced) {
        return parameterSetDAO.getParameters(modemId, startTime, endTime, reduced);
    }

    @Override
    public List<ParameterSet> getParameters(String modemId, LocalDateTime startTime, LocalDateTime endTime, int limit, boolean reduced) {
        return parameterSetDAO.getParameters(modemId, startTime, endTime, limit, reduced);
    }
}
