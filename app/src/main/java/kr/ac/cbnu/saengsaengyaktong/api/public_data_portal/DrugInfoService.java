package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrugInfoService {
    @GET("1471000/DrbEasyDrugInfoService/getDrbEasyDrugList")
    Call<DrugListResponse> getDrugList(@Query("serviceKey") String serviceKey, @Query("itemName") String name, @Query("type") String type);
}
