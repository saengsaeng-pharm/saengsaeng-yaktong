package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import javax.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PublicDataPortalService {
    @Nullable
    private static PublicDataPortalService instance;

    private static final String BASE_URL = "http://apis.data.go.kr";
    public static final String SERVICE_KEY = "fRGLmRrMbekkyWJoLkchb9OMIcBw6aXDlc51P9qSL3E16zs88mFLJwrc1Td8EKm7DHBXCltZI1eGkQZYHY/0Bg==";

    private final Retrofit retrofitJson, retrofitXml;

    @Nullable
    private DrugInfoServiceWrapper drugInfoService;
    @Nullable
    private DrugPermitInfoServiceWrapper drugPermitInfoService;
    @Nullable
    private HospitalInfoServiceWrapper hospitalInfoService;
    @Nullable
    private PharmacyInfoServiceWrapper pharmacyInfoService;

    public static synchronized PublicDataPortalService getInstance() {
        if (instance == null) {
            instance = new PublicDataPortalService();
        }

        return instance;
    }

    public PublicDataPortalService() {
        retrofitJson = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitXml = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    public DrugInfoServiceWrapper getDrugInfoService() {
        if (drugInfoService == null) {
            drugInfoService = new DrugInfoServiceWrapper(retrofitJson.create(DrugInfoService.class), SERVICE_KEY);
        }

        return drugInfoService;
    }

    public DrugPermitInfoServiceWrapper getDrugPermitInfoService() {
        if (drugPermitInfoService == null) {
            drugPermitInfoService = new DrugPermitInfoServiceWrapper(retrofitJson.create(DrugPermitInfoService.class), SERVICE_KEY);
        }

        return drugPermitInfoService;
    }

    public HospitalInfoServiceWrapper getHospitalInfoService() {
        if (hospitalInfoService == null) {
            hospitalInfoService = new HospitalInfoServiceWrapper(retrofitXml.create(HospitalInfoService.class), SERVICE_KEY);
        }

        return hospitalInfoService;
    }

    public PharmacyInfoServiceWrapper getPharmacyInfoService() {
        if (pharmacyInfoService == null) {
            pharmacyInfoService = new PharmacyInfoServiceWrapper(retrofitXml.create(PharmacyInfoService.class), SERVICE_KEY);
        }

        return pharmacyInfoService;
    }
}
