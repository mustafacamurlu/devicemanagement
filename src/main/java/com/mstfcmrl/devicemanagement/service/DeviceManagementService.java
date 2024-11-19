package com.mstfcmrl.devicemanagement.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mstfcmrl.devicemanagement.model.TimeseriesSensorData;
import com.mstfcmrl.devicemanagement.entity.Device;
import com.mstfcmrl.devicemanagement.repository.DeviceManagementRepository;

@Service
@Transactional
public class DeviceManagementService {

    @Autowired
    private DeviceManagementRepository deviceRepository;

    @Autowired
    private TimeSeriesClientService timeSeriesClientService;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(String id) {

        Device device = deviceRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RuntimeException("Device not found"));
        device.setVehicleName("MyTruck");
        return device;
    }

    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device updateDevice(String id, Device deviceDetails) {
        Device device = getDeviceById(id);
        device.setSerialNumber(deviceDetails.getSerialNumber());
        device.setDeviceName(deviceDetails.getDeviceName());
        device.setVehicleId(deviceDetails.getVehicleId());
        device.setVehicleType(deviceDetails.getVehicleType());
        return deviceRepository.save(device);
    }

    public void deleteDevice(String id) {
        Device device = getDeviceById(id);
        deviceRepository.delete(device);
    }

    public void uploadTimeSeries(String id, List<TimeseriesSensorData> payload) {
        deviceRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Device not found"));
        timeSeriesClientService.writeSensorData(id, payload);
    }
}