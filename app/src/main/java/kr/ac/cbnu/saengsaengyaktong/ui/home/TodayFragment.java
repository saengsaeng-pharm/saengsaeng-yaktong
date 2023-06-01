package kr.ac.cbnu.saengsaengyaktong.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentTodayBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.RecognitionActivity;

public class TodayFragment extends Fragment {
    private FragmentTodayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentTodayBinding.inflate(inflater, container, false);

        final View view = binding.getRoot();

        final ExtendedFloatingActionButton addButton = view.findViewById(R.id.fab_add);
        addButton.setOnClickListener(this::onAddClick);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onAddClick(View v) {
        final Intent intent = new Intent(getActivity(), RecognitionActivity.class);
        startActivity(intent);
    }
}