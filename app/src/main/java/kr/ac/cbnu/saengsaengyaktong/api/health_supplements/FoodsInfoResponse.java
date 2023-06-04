package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodsInfoResponse {
    @SerializedName("total")
    private int totalCount;

    @SerializedName("data")
    private List<FoodInfo> items;

    public int getTotalCount() {
        return totalCount;
    }

    public List<FoodInfo> getItems() {
        return items;
    }
}
