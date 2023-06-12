package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentFoodReviewsBinding;

public class FoodReviewsFragment extends Fragment {
    private FragmentFoodReviewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFoodReviewsBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}
