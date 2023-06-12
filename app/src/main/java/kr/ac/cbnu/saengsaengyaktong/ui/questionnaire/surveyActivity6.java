package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.ac.cbnu.saengsaengyaktong.R;

public class surveyActivity6 extends AppCompatActivity {


    boolean is_O = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey6);

        Health health = new Health();

        findViewById(R.id.O_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.ironIncrementCount(); // count 값 증가
                is_O = true;
                health.Vitamin_BIncrementCount(); // count 값 증가
                Intent intent = new Intent(surveyActivity6.this, surveyActivity7.class);
                startActivity(intent);
                Log.d("iron", Integer.toString(health.getIron()));
                Log.d("Vitamin_B",Integer.toString(health.getVitaminB()));
            }
        });

        findViewById(R.id.X_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.ironDecrementCount(); // count 값 감소
                health.Vitamin_BDecrementCount(); // count 값 감소
                Intent intent = new Intent(surveyActivity6.this, surveyActivity7.class);
                startActivity(intent);
                Log.d("iron", Integer.toString(health.getIron()));
                Log.d("Vitamin_B",Integer.toString(health.getVitaminB()));
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(is_O == true){
                    health.ironDecrementCount(); // count 값 감소
                    health.Vitamin_BDecrementCount(); // count 값 감소
                }
                Intent intent = new Intent(surveyActivity6.this, surveyActivity5.class);
                startActivity(intent);
                Log.d("iron", Integer.toString(health.getIron()));
                Log.d("Vitamin_B",Integer.toString(health.getVitaminB()));
            }
        });
    }
}