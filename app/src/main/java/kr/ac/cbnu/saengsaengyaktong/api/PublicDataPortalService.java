package kr.ac.cbnu.saengsaengyaktong.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicDataPortalService {
    private static final String BASE_URL = "http://apis.data.go.kr";
    public static final String SERVICE_KEY = "lQc9IJs3H%2B6%2BT%2BxpRQewYUMQNlVm%2FWyIJmekxYS9CczKDzBqgiZ6nvLW5HBLa2fsxOrjJ02Cd9kUn1G8jB2YxA%3D%3D";

    private final GsonConverterFactory gsonFactory;
    private final DrugInfoService drugInfoService;

    public PublicDataPortalService() {
        gsonFactory = GsonConverterFactory.create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonFactory)
                .build();

        drugInfoService = retrofit.create(DrugInfoService.class);
    }

    public DrugInfoService getDrugInfoService() {
        return drugInfoService;
    }
}
