package kr.ac.cbnu.saengsaengyaktong.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import kr.ac.cbnu.saengsaengyaktong.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.Factory.from(SignInViewModel.initializer)).get(SignInViewModel.class);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
    }

    public void onSignUpClick(View view) {
        final Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onSignInClick(View view) {
        viewModel.signIn().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                finish();
            } else {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}