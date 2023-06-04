package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import retrofit2.Call;

public class HospitalInfoServiceWrapper {
    private final HospitalInfoService service;
    private final String serviceKey;

    public HospitalInfoServiceWrapper(HospitalInfoService service, String serviceKey) {
        this.service = service;
        this.serviceKey = serviceKey;
    }

    public Call<HospitalListResponse> getHospitalList(String province, String city, int numOfRows) {
        return service.getHospitalList(serviceKey, province, city, numOfRows);
    }
}