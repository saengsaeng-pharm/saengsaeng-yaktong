package kr.ac.cbnu.saengsaengyaktong.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityMainBinding;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityRecognitionBinding;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityTestBinding;

public class RecognitionActivity extends AppCompatActivity {
    private ActivityRecognitionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecognitionBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_recognition);
    }
}
