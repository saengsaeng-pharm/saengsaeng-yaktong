package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;

public class DrugInfoServiceWrapper {
    private final DrugInfoService service;
    private final String serviceKey;

    public DrugInfoServiceWrapper(DrugInfoService service, String serviceKey) {
        this.service = service;
        this.serviceKey = serviceKey;
    }

    public Call<DrugListResponse> getDrugList(String name) {
        try {
            final String encodedName = URLEncoder.encode(name, "UTF-8");
            return service.getDrugList(serviceKey, encodedName, "json");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}