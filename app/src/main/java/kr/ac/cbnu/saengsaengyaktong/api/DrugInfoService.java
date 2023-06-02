package kr.ac.cbnu.saengsaengyaktong.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrugInfoService {
    @GET("1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=" + PublicDataPortalService.SERVICE_KEY + "&type=json")
    Call<DrugListResponse> getDrugList(@Query("itemName") String name);
}