package kr.ac.cbnu.saengsaengyaktong.ui.medicine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentMedicineBriefInfoBinding;

public class MedicineBriefInfoFragment extends Fragment {
    private final DrugInfo info;

    private FragmentMedicineBriefInfoBinding binding;

    public MedicineBriefInfoFragment(DrugInfo info) {
        this.info = info;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedicineBriefInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setInfo(info);

        return binding.getRoot();
    }
}
