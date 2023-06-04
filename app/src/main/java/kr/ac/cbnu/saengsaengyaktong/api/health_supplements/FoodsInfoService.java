package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodsInfoService {
    @GET("api/foods")
    Call<FoodsInfoResponse> getFoodsInfo(@Nullable @Query("query") String query, @Nullable @Query("functionalities") String functionalities, @Query("page") int page, @Query("limit") int limit);

    @GET("api/foods/d{id}")
    Call<FoodDetailInfoResponse> getFoodDetailInfo(@Path("id") String id);
}
