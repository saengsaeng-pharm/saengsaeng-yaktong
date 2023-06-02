package kr.ac.cbnu.saengsaengyaktong.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PharmacyInfoService {
    @GET("B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire?serviceKey=" + PublicDataPortalService.SERVICE_KEY + "&_type=json")
    Call<DrugListResponse> getPharmacyList(@Query("Q0") String province, @Query("Q1") String city, @Query("itemName") String name);
}
