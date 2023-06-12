package kr.ac.cbnu.saengsaengyaktong.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    private SimpleDateFormat datePickerFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.Factory.from(SignUpViewModel.initializer)).get(SignUpViewModel.class);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        final SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_format));
        dateFormat.setTimeZone(UTC_TIMEZONE);
        binding.setDateFormat(dateFormat);

        setContentView(binding.getRoot());
    }

    public void onBirthDateClick(View view) {
        datePickerFormat = new SimpleDateFormat(getString(R.string.date_picker_format));
        datePickerFormat.setTimeZone(UTC_TIMEZONE);

        final MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("생년월일 선택")
                .setTextInputFormat(datePickerFormat)
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            final Calendar calendar = Calendar.getInstance(UTC_TIMEZONE);
            calendar.setTimeInMillis(selection);

            viewModel.setBirthDate(calendar.getTime());
        });

        datePicker.show(getSupportFragmentManager(), TAG);
    }

    public void onComplete(View view) {
        boolean isError = false;

        try {
            if (!viewModel.isEmailValid()) {
                binding.edittextEmail.setError("유효한 이메일 주소를 입력하세요.");
                isError = true;
            }

            if (!viewModel.isPasswordValid()) {
                binding.edittextPassword.setError("비밀번호는 최소 6자 이상이어야 합니다.");
                isError = true;
            }

            if (!viewModel.isPasswordConfirmValid()) {
                binding.edittextPasswordConfirm.setError("비밀번호 확인은 비밀번호와 동일해야 합니다.");
                isError = true;
            }

            if (isError) return;

            viewModel.signUp().addOnCompleteListener(this, task -> {
                if (!task.isSuccessful()) {
                    final String message = task.getException().getLocalizedMessage();
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    return;
                }

                viewModel.registerProfile();

                new MaterialAlertDialogBuilder(this)
                        .setTitle("회원 가입 완료")
                        .setMessage("회원 가입이 완료되었습니다.\n이메일로 발송된 인증 링크로 인증 후 로그인하세요.")
                        .show();
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}