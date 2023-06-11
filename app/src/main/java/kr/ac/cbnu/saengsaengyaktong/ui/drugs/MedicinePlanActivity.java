package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityPlanBinding;

public class MedicinePlanActivity extends AppCompatActivity {
    private ActivityPlanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}