package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodDetailInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentDrugOverviewBinding;

public class DrugOverviewFragment extends Fragment {
    private final DrugInfo info;

    private FragmentDrugOverviewBinding binding;

    public DrugOverviewFragment(DrugInfo info) {
        this.info = info;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDrugOverviewBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setInfo(info);

        return binding.getRoot();
    }
}
