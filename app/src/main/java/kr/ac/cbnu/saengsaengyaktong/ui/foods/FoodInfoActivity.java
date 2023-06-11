package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodDetailInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodDetailInfoResponse;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodsInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.HealthSupplementsService;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityFoodInfoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodInfoActivity extends AppCompatActivity {
    private final FoodsInfoServiceWrapper foodsInfoService = HealthSupplementsService.getInstance().getFoodsInfoService();

    private ActivityFoodInfoBinding binding;
    private FoodInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        info = intent.getParcelableExtra("foodInfo");

        binding = ActivityFoodInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);
        foodsInfoService.getFoodDetails(info.getId()).enqueue(new Callback<FoodDetailInfoResponse>() {
            @Override
            public void onResponse(Call<FoodDetailInfoResponse> call, Response<FoodDetailInfoResponse> response) {
                System.out.println(call.request().url());

                final FoodDetailInfo info = response.body().getItem();
                binding.setDetailInfo(info);
            }

            @Override
            public void onFailure(Call<FoodDetailInfoResponse> call, Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });

        binding.setInfo(info);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarFoodInfo);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(info.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}