package kr.ac.cbnu.saengsaengyaktong.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;

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
        boolean isError = false;

        if (!viewModel.isEmailValid()) {
            binding.edittextEmail.setError("유효한 이메일 주소를 입력하세요.");
            isError = true;
        }

        if (!viewModel.isPasswordValid()) {
            binding.edittextPassword.setError("비밀번호는 최소 6자 이상이어야 합니다.");
            isError = true;
        }

        if (isError) return;

        viewModel.signIn().addOnCompleteListener(this, task -> {
            if (!task.isSuccessful()) {
                final String message = task.getException().getLocalizedMessage();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                return;
            }

            finish();
        });
    }
}