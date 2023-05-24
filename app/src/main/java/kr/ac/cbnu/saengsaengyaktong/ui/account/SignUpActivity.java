package kr.ac.cbnu.saengsaengyaktong.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.Factory.from(SignUpViewModel.initializer)).get(SignUpViewModel.class);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);

        final SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_format), Locale.KOREAN);
        binding.setDateFormat(dateFormat);

        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
    }

    public void onBirthDateClick(View view) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_picker_format), Locale.KOREAN);

        final MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("생년월일 선택")
                .setTextInputFormat(dateFormat)
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);

            viewModel.setBirthDate(calendar.getTime());
        });

        datePicker.show(getSupportFragmentManager(), "");
    }
}