package kr.ac.cbnu.saengsaengyaktong.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HospitalInfoService {
    @GET("B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire?serviceKey=" + PublicDataPortalService.SERVICE_KEY + "&_type=json")
    Call<HospitalInfo> getHospitalList(@Query("Q0") String province, @Query("Q1") String city, @Query("itemName") String name);
}
