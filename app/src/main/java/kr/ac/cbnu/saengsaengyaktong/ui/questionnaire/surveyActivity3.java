package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.ac.cbnu.saengsaengyaktong.R;

public class surveyActivity3 extends AppCompatActivity {

    boolean is_O = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey3);

        Health health = new Health();

        findViewById(R.id.O_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.Vitamin_BIncrementCount(); // count 값 증가
                is_O = true;
                Intent intent = new Intent(surveyActivity3.this, surveyActivity4.class);
                startActivity(intent);
                Log.d("Vitamin_B",Integer.toString(health.getVitaminB()));
            }
        });

        findViewById(R.id.X_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.Vitamin_BDecrementCount(); // count 값 감소
                Intent intent = new Intent(surveyActivity3.this, surveyActivity4.class);
                startActivity(intent);
                Log.d("Vitamin_B",Integer.toString(health.getVitaminB()));
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(is_O == true){
                    health.Vitamin_BDecrementCount(); // count 값 감소
                }
                Intent intent = new Intent(surveyActivity3.this, surveyActivity2.class);
                startActivity(intent);
                Log.d("Vitamin_B",Integer.toString(health.getVitaminB()));
            }
        });
    }
}