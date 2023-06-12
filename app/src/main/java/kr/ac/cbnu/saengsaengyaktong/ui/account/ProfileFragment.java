package kr.ac.cbnu.saengsaengyaktong.ui.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentProfileBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.questionnaire.CheckTest;
import kr.ac.cbnu.saengsaengyaktong.ui.questionnaire.Main2Activity;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         binding = FragmentProfileBinding.inflate(inflater, container, false);

         binding.layoutQuestionnaire.setOnClickListener(view -> {
             final Intent intent = new Intent(getContext(), CheckTest.class);
             startActivity(intent);
         });

         return binding.getRoot();
    }
}