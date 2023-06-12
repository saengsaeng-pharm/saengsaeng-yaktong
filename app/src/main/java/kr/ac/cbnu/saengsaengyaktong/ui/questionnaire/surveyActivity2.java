package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.ac.cbnu.saengsaengyaktong.R;

public class surveyActivity2 extends AppCompatActivity {

    boolean is_O = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey2);

        Health health = new Health();

        findViewById(R.id.O_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.Vitamin_DIncrementCount(); // count 값 증가
                is_O = true;
                Intent intent = new Intent(surveyActivity2.this, surveyActivity3.class);
                startActivity(intent);
                Log.d("Vitamin_D",Integer.toString(health.getVitaminD()));
            }
        });

        findViewById(R.id.X_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.Vitamin_DDecrementCount(); // count 값 감소
                Intent intent = new Intent(surveyActivity2.this, surveyActivity3.class);
                startActivity(intent);
                Log.d("Vitamin_D",Integer.toString(health.getVitaminD()));
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(is_O == true){
                    health.Vitamin_DDecrementCount(); // count 값 감소
                }
                Intent intent = new Intent(surveyActivity2.this, surveyActivity1.class);
                startActivity(intent);
                Log.d("Vitamin_D",Integer.toString(health.getVitaminD()));
            }
        });
    }
}