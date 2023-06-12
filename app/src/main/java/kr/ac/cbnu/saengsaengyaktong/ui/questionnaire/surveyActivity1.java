package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.cbnu.saengsaengyaktong.R;

public class surveyActivity1 extends AppCompatActivity{
    boolean is_O = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey1);

        Health health = new Health();

        findViewById(R.id.O_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.ironIncrementCount(); // count 값 증가
                is_O = true;
                Intent intent = new Intent(surveyActivity1.this, surveyActivity2.class);
                startActivity(intent);
                Log.d("iron",Integer.toString(health.getIron()));
            }
        });

        findViewById(R.id.X_btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                health.ironDecrementCount(); // count 값 감소
                Intent intent = new Intent(surveyActivity1.this, surveyActivity2.class);
                startActivity(intent);
                Log.d("iron",Integer.toString(health.getIron()));
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(is_O == true){
                    health.ironDecrementCount(); // count 값 감소
                }
                Intent intent = new Intent(surveyActivity1.this, CheckTest.class);
                startActivity(intent);
                Log.d("iron",Integer.toString(health.getIron()));
            }
        });
    }

}
