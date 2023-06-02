package kr.ac.cbnu.saengsaengyaktong.api;

import javax.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicDataPortalService {
    @Nullable
    private static PublicDataPortalService instance;

    private static final String BASE_URL = "http://apis.data.go.kr";
    public static final String SERVICE_KEY = "lQc9IJs3H%2B6%2BT%2BxpRQewYUMQNlVm%2FWyIJmekxYS9CczKDzBqgiZ6nvLW5HBLa2fsxOrjJ02Cd9kUn1G8jB2YxA%3D%3D";

    private final Retrofit retrofit;

    @Nullable
    private DrugInfoService drugInfoService;
    @Nullable
    private HospitalInfoService hospitalInfoService;
    @Nullable
    private PharmacyInfoService pharmacyInfoService;

    public static synchronized PublicDataPortalService getInstance() {
        if (instance == null) {
            instance = new PublicDataPortalService();
        }

        return instance;
    }

    public PublicDataPortalService() {
        final GsonConverterFactory gsonFactory = GsonConverterFactory.create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonFactory)
                .build();
    }

    public DrugInfoService getDrugInfoService() {
        if (drugInfoService == null) {
            drugInfoService = retrofit.create(DrugInfoService.class);
        }

        return drugInfoService;
    }

    public HospitalInfoService getHospitalInfoService() {
        if (hospitalInfoService == null) {
            hospitalInfoService = retrofit.create(HospitalInfoService.class);
        }

        return hospitalInfoService;
    }

    public PharmacyInfoService getPharmacyInfoService() {
        if (pharmacyInfoService == null) {
            pharmacyInfoService = retrofit.create(PharmacyInfoService.class);
        }

        return pharmacyInfoService;
    }
}
