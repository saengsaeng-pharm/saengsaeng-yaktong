package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PharmacyInfoService {
    @GET("B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire")
    Call<PharmacyListResponse> getPharmacyList(@Query("serviceKey") String serviceKey, @Query("Q0") String province, @Query("Q1") String city, @Query("numOfRows") int numOfRows);
}
