package com.mstfcmrl.devicemanagement.client;

import com.mstfcmrl.devicemanagement.model.TimeseriesDataPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "TimeSeriesClient", url = "${timeseries.service.url}")
public interface TimeSeriesClient {
    @PostMapping("/api/timeseries") // Adjust the endpoint path if needed
    ResponseEntity<String> writeData(@RequestBody TimeseriesDataPayload payload);
}
