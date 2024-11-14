package com.mstfcmrl.devicemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import com.mstfcmrl.devicemanagement.entity.Device;

public interface DeviceManagementRepository extends JpaRepository<Device, UUID> {
    List<Device> findBySerialNumber(String serialNumber);
}
