package com.mstfcmrl.devicemanagement.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mstfcmrl.devicemanagement.model.TimeseriesSensorData;
import com.mstfcmrl.devicemanagement.service.DeviceManagementService;
import com.mstfcmrl.devicemanagement.entity.Device;

@RestController
@RequestMapping("/api/devicemanagement")
public class DeviceManagementController {

    @Autowired
    private DeviceManagementService deviceManagemnetService;

    @GetMapping("/test")
    public String publicHello() {
        String hostname = System.getenv("HOSTNAME");
        return "Hello, this is a public endpoint!" + hostname;
    }

    @GetMapping("/devices")
    public List<Device> getAllDevices() {
        return deviceManagemnetService.getAllDevices();
    }

    @GetMapping("/devices/{id}")
    public Device getDeviceById(@PathVariable String id) {
        return deviceManagemnetService.getDeviceById(id);
    }

    @PostMapping("/devices")
    //@PreAuthorize("hasScope('create:device')")
    public Device createDevice(@RequestBody Device device) {
        return deviceManagemnetService.createDevice(device);
    }

    @PutMapping("/devices/{id}")
    public Device updateDevice(@PathVariable String id, @RequestBody Device deviceDetails) {
        return deviceManagemnetService.updateDevice(id, deviceDetails);
    }

    @DeleteMapping("/devices/{id}")
    public void deleteDevice(@PathVariable String id) {
        deviceManagemnetService.deleteDevice(id);
    }

    @PostMapping("/devices/{id}/timeseries")
    public ResponseEntity<String> uploadTimeseries(@PathVariable String id, @Valid @RequestBody List<TimeseriesSensorData> payload) {

        try {
            deviceManagemnetService.uploadTimeSeries(id, payload);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to write data: " + e.getMessage());
        }
    }
}
