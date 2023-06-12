package kr.ac.cbnu.saengsaengyaktong.ui.foods;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FunctionalityType;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentSelectFunctionalityBinding;

public class SelectFunctionalityFragment extends Fragment {
    private FragmentSelectFunctionalityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectFunctionalityBinding.inflate(inflater, container, false);

        binding.buttonNext.setOnClickListener(v -> {
            final Set<FunctionalityType> types = new HashSet<>();

            if (binding.chipIntestineHealth.isChecked()) {
                types.add(FunctionalityType.INTESTINAL_HEALTH);
            } else if (binding.chipBoneHealth.isChecked()) {
                types.add(FunctionalityType.JOINT_BONE_HEALTH);
            } else if (binding.chipFatReduction.isChecked()) {
                types.add(FunctionalityType.BODY_FAT_REDUCTION);
            } else if (binding.chipImmuneImprovement.isChecked()) {
                types.add(FunctionalityType.IMMUNE_FUNCTION_IMPROVEMENT);
            } else if (binding.chipSkinHealth.isChecked()) {
                types.add(FunctionalityType.SKIN_HEALTH);
            } else if (binding.chipMemoryImprovement.isChecked()) {
                types.add(FunctionalityType.MEMORY_IMPROVEMENT);
            } else if (binding.chipLiverHealth.isChecked()) {
                types.add(FunctionalityType.LIVER_HEALTH);
            } else if (binding.chipEyeHealth.isChecked()) {
                types.add(FunctionalityType.EYE_HEALTH);
            } else if (binding.chipCalciumAbsorption.isChecked()) {
                types.add(FunctionalityType.CALCIUM_ABSORPTION_PROMOTION);
            } else if (binding.chipExerciseAbility.isChecked()) {
                types.add(FunctionalityType.EXERCISE_PERFORMANCE);
            } else if (binding.chipFatigueImprovement.isChecked()) {
                types.add(FunctionalityType.FATIGUE_IMPROVEMENT);
            } else if (binding.chipSleepImprovement.isChecked()) {
                types.add(FunctionalityType.SLEEP_QUALITY_IMPROVEMENT);
            } else if (binding.chipMuscleImprovement.isChecked()) {
                types.add(FunctionalityType.MUSCLE_STRENGTH_IMPROVEMENT);
            } else if (binding.chipOralHealth.isChecked()) {
                types.add(FunctionalityType.ORAL_HEALTH);
            }

            final ArrayList<String> enumStrings = new ArrayList<>();

            for (FunctionalityType type : types) {
                enumStrings.add(type.name());
            }

            final Bundle bundle = new Bundle();
            bundle.putStringArrayList("types", enumStrings);

            Navigation.findNavController(v).navigate(R.id.navigation_recommendations, bundle);
        });

        return binding.getRoot();
    }
}
