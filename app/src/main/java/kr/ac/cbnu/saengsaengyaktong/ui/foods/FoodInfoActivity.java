package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;

import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodDetailInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodDetailInfoResponse;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodsInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.HealthSupplementsService;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityFoodInfoBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.schedules.DrugScheduleActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodInfoActivity extends AppCompatActivity {
    private static int REQUEST_ADD = 0;

    private final FoodsInfoServiceWrapper foodsInfoService = HealthSupplementsService.getInstance().getFoodsInfoService();

    private ActivityFoodInfoBinding binding;
    private FoodInfo info;

    private FoodInfoFragment infoFragment;
    private FoodReviewsFragment reviewsFragment;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        info = getIntent().getParcelableExtra("foodInfo");

        infoFragment = new FoodInfoFragment(info);
        reviewsFragment = new FoodReviewsFragment();

        binding = ActivityFoodInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.appbarMedicineInfo.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float range = (float) -appBarLayout.getTotalScrollRange();
            binding.imageMedicine.setAlpha(1 - (verticalOffset / range));
        });

        foodsInfoService.getFoodDetails(info.getId()).enqueue(new Callback<FoodDetailInfoResponse>() {
            @Override
            public void onResponse(Call<FoodDetailInfoResponse> call, Response<FoodDetailInfoResponse> response) {
                System.out.println(call.request().url());

                final FoodDetailInfo info = response.body().getItem();
                infoFragment.setDetailInfo(info);
            }

            @Override
            public void onFailure(Call<FoodDetailInfoResponse> call, Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });

        binding.fabAddFood.setOnClickListener((v) -> onAddClick());

        adapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayoutMedicineInfo, binding.viewPager,
                (tab, position) -> {
                    if (position == 0) tab.setText("정보");
                    else tab.setText("리뷰");
                }
        ).attach();

        binding.setInfo(info);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarFoodInfo);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(info.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            finish();
        }
    }

    private void onAddClick() {
        final Intent intent = new Intent(this, DrugScheduleActivity.class);
        intent.putExtra("info", info);

        startActivityForResult(intent, REQUEST_ADD);
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(FoodInfoActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) return infoFragment;
            return reviewsFragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}