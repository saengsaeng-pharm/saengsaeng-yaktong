package kr.ac.cbnu.saengsaengyaktong.api.naver;

import retrofit2.Call;

public class ReverseGeocodeServiceWrapper {
    private final ReverseGeocodeService service;
    private final String clientId, clientSecret;

    public ReverseGeocodeServiceWrapper(ReverseGeocodeService service, String clientId, String clientSecret) {
        this.service = service;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Call<ReverseGeocodeResponse> reverseGeocode(double latitude, double longitude) {
        return service.reverseGeocode(clientId, clientSecret, longitude + "," + latitude, "legalcode", "json");
    }
}