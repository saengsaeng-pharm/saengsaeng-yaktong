package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodDetailInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentFoodInfoBinding;

public class FoodInfoFragment extends Fragment {
    private final FoodInfo info;

    private FragmentFoodInfoBinding binding;

    public FoodInfoFragment(FoodInfo info) {
        this.info = info;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFoodInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setInfo(info);

        return binding.getRoot();
    }

    public void setDetailInfo(FoodDetailInfo info) {
        binding.setDetailInfo(info);
    }
}
