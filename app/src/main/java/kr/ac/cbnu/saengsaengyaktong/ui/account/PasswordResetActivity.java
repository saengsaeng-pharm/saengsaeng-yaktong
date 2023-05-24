package kr.ac.cbnu.saengsaengyaktong.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityPasswordResetBinding;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivitySignInBinding;

public class PasswordResetActivity extends AppCompatActivity {
    private ActivityPasswordResetBinding binding;
    private PasswordResetViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.Factory.from(PasswordResetViewModel.initializer)).get(PasswordResetViewModel.class);

        binding = ActivityPasswordResetBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
    }
}