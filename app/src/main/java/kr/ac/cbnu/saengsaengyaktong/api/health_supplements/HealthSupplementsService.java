package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import javax.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HealthSupplementsService {
    @Nullable
    private static HealthSupplementsService instance;

    private static final String BASE_URL = "https://www.hffinfo.com";

    private final Retrofit retrofit;

    @Nullable
    private FoodsInfoServiceWrapper foodsInfoService;

    public static synchronized HealthSupplementsService getInstance() {
        if (instance == null) {
            instance = new HealthSupplementsService();
        }

        return instance;
    }

    public HealthSupplementsService() {
        final GsonConverterFactory gsonFactory = GsonConverterFactory.create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonFactory)
                .build();
    }

    public FoodsInfoServiceWrapper getFoodsInfoService() {
        if (foodsInfoService == null) {
            foodsInfoService = new FoodsInfoServiceWrapper(retrofit.create(FoodsInfoService.class));
        }

        return foodsInfoService;
    }
}
