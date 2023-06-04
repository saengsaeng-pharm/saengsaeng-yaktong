package kr.ac.cbnu.saengsaengyaktong.api.naver;

import javax.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaverCloudService {
    @Nullable
    private static NaverCloudService instance;

    private static final String BASE_URL = "https://naveropenapi.apigw.ntruss.com";
    private static final String CLIENT_ID = "96kmjwxr19";
    private static final String CLIENT_SECRET = "uz6wA1zSnslFcJwrJw70UAhxiVtjTxlQYRemKdBW";

    private final Retrofit retrofit;

    @Nullable
    private ReverseGeocodeServiceWrapper reverseGeocodingService;

    public static synchronized NaverCloudService getInstance() {
        if (instance == null) {
            instance = new NaverCloudService();
        }

        return instance;
    }

    public NaverCloudService() {
        final GsonConverterFactory gsonFactory = GsonConverterFactory.create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonFactory)
                .build();
    }

    public ReverseGeocodeServiceWrapper getReverseGeocodingService() {
        if (reverseGeocodingService == null) {
            reverseGeocodingService = new ReverseGeocodeServiceWrapper(retrofit.create(ReverseGeocodeService.class), CLIENT_ID, CLIENT_SECRET);
        }

        return reverseGeocodingService;
    }
}
