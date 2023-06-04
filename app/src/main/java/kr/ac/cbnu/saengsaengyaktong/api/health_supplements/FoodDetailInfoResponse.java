package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import com.google.gson.annotations.SerializedName;

public class FoodDetailInfoResponse {
    @SerializedName("domestic")
    private FoodDetailInfo item;

    public FoodDetailInfo getItem() {
        return item;
    }
}
