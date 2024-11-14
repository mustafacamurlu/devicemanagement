package com.mstfcmrl.devicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DevicemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevicemanagementApplication.class, args);
	}

}
