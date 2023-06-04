package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrugPermitInfoService {

    @GET("1471000/DrugPrdtPrmsnInfoService04/getDrugPrdtPrmsnDtlInq03")
    Call<DrugPermitInfoResponse> getDrugPermitInfo(@Query("serviceKey") String serviceKey, @Query("item_seq") String drugId, @Query("type") String type);
}
