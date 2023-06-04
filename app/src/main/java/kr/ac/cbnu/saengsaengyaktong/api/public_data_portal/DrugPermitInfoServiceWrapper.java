package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import retrofit2.Call;

public class DrugPermitInfoServiceWrapper {
    private final DrugPermitInfoService service;
    private final String serviceKey;

    public DrugPermitInfoServiceWrapper(DrugPermitInfoService service, String serviceKey) {
        this.service = service;
        this.serviceKey = serviceKey;
    }

    public Call<DrugPermitInfoResponse> getDrugPermitInfo(String itemId) {
        return service.getDrugPermitInfo(serviceKey, itemId, "json");
    }
}
