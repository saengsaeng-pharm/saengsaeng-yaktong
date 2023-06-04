package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;

public class FoodsInfoServiceWrapper {
    private final FoodsInfoService service;

    public FoodsInfoServiceWrapper(FoodsInfoService service) {
        this.service = service;
    }

    public Call<FoodsInfoResponse> getFoodsInfo(List<FunctionalityType> functionalities) {
        final List<String> ids = functionalities.stream().map(FunctionalityType::getId).collect(Collectors.toList());
        final String idsString = String.join(",", ids);

        return service.getFoodsInfo(null, idsString, 1, 10);
    }

    public Call<FoodsInfoResponse> getFoodsInfo(String query) {
        return service.getFoodsInfo(query, null, 1, 10);
    }

    public Call<FoodDetailInfoResponse> getFoodDetails(String id) {
        return service.getFoodDetailInfo(id);
    }
}
