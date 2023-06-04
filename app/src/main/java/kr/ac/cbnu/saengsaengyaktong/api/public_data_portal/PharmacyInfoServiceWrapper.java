package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import retrofit2.Call;

public class PharmacyInfoServiceWrapper {
    private final PharmacyInfoService service;
    private final String serviceKey;

    public PharmacyInfoServiceWrapper(PharmacyInfoService service, String serviceKey) {
        this.service = service;
        this.serviceKey = serviceKey;
    }

    public Call<PharmacyListResponse> getPharmacyList(String province, String city, int numOfRows) {
        return service.getPharmacyList(serviceKey, province, city, numOfRows);
    }
}