package com.mvp.spotfind.OpenCageMapsServices;


import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service

public class GeocodingService {
    private final JOpenCageGeocoder geocoder;

    // Load API Key from application.properties
    @Value("${openCage.apikey}")
    private String apikey;
    public GeocodingService() {
        this.geocoder = new JOpenCageGeocoder(apikey);
    }

    public Double[] getCoordinates(String address) {
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);

        JOpenCageResponse response = geocoder.forward(request);

        if (!response.getResults().isEmpty()) {
            JOpenCageResult result = response.getResults().get(0);

            return new Double[]{result.getGeometry().getLat(), result.getGeometry().getLng()};
        }
        return new Double[]{};
    }
}