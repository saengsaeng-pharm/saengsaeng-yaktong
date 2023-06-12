package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.ProductInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodsInfoResponse;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodsInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FunctionalityType;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.HealthSupplementsService;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugListResponse;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentFoodRecommendationsBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.schedules.DrugSchedulesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRecommendationsFragment extends Fragment {
    final FoodsInfoServiceWrapper service = HealthSupplementsService.getInstance().getFoodsInfoService();

    private FragmentFoodRecommendationsBinding binding;
    private FoodRecommendationsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFoodRecommendationsBinding.inflate(inflater, container, false);

        final List<String> enumStrings = getArguments().getStringArrayList("types");
        final List<FunctionalityType> types = enumStrings.stream().map((s) -> FunctionalityType.valueOf(s)).collect(Collectors.toList());

        adapter = new FoodRecommendationsAdapter();
        adapter.setOnClickListener(item -> {
            final Intent intent = new Intent(getActivity(), FoodInfoActivity.class);
            intent.putExtra("foodInfo", (FoodInfo)item);

            startActivity(intent);
        });

        binding.recyclerSearchSuggestions.setAdapter(adapter);

        final Call<FoodsInfoResponse> call = service.getFoodsInfo(types);

        System.out.println(call.request().url());

        call.enqueue(new Callback<FoodsInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodsInfoResponse> call, @NonNull Response<FoodsInfoResponse> response) {
                System.out.println(call.request().url());

                final List<FoodInfo> items = response.body().getItems();
                adapter.setItems(items);
            }

            @Override
            public void onFailure(@NonNull Call<FoodsInfoResponse> call, @NonNull Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });

        return binding.getRoot();
    }
}