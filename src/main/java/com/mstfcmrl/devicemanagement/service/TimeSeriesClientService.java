package com.mstfcmrl.devicemanagement.service;

import com.mstfcmrl.devicemanagement.client.TimeSeriesClient;
import com.mstfcmrl.devicemanagement.model.TimeseriesDataPayload;
import com.mstfcmrl.devicemanagement.model.TimeseriesSensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimeSeriesClientService {

    private final TimeSeriesClient timeSeriesClient;

    @Autowired
    public TimeSeriesClientService(TimeSeriesClient timeSeriesClient) {
        this.timeSeriesClient = timeSeriesClient;
    }

    public void writeSensorData(String deviceId, List<TimeseriesSensorData> values) {
        TimeseriesDataPayload payload = new TimeseriesDataPayload();
        payload.setDeviceId(deviceId);
        payload.setValues(values);

        // Call the Feign client to write data
        timeSeriesClient.writeData(payload);
    }
}