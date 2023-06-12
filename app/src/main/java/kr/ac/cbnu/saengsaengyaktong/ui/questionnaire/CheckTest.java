package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.cbnu.saengsaengyaktong.R;

public class CheckTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_test);

        Health health = new Health();

        final TextView text = (TextView) findViewById(R.id.regist_title);

        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        Button btn4 = (Button)findViewById(R.id.btn4);
        Button btn5 = (Button)findViewById(R.id.btn5);
        Button btn6 = (Button)findViewById(R.id.btn6);
        Button btn7 = (Button)findViewById(R.id.btn7);
        Button btn8 = (Button)findViewById(R.id.btn8);
        Button btn9 = (Button)findViewById(R.id.btn9);
        Button btn10 = (Button)findViewById(R.id.btn10);
        Button btn11 = (Button)findViewById(R.id.btn11);
        Button btn12 = (Button)findViewById(R.id.btn12);
        Button btn13 = (Button)findViewById(R.id.btn13);
        Button btn14 = (Button)findViewById(R.id.btn14);
        Button btn15 = (Button)findViewById(R.id.btn15);
        Button btn16 = (Button)findViewById(R.id.btn16);

        Button btn_survey = (Button) findViewById(R.id.btn_survey);
        Button btn_storage = (Button) findViewById(R.id.btn_storage);

        btn_storage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        btn_survey.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), surveyActivity1.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.diabetesIncrementCountCk(); // count 값 증가
                    btn1.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.diabetesDecrementCountCk(); // count 값 감소
                    btn1.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.rhinitisIncrementCountCk(); // count 값 증가
                    btn2.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경

                } else{
                    health.rhinitisDecrementCountCk(); // count 값 감소
                    btn2.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.anginaPectorisIncrementCountCk(); // count 값 증가
                    btn3.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.anginaPectorisDecrementCountCk(); // count 값 감소
                    btn3.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.hepatitisIncrementCountCk(); // count 값 증가
                    btn4.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.hepatitisDecrementCountCk(); // count 값 감소
                    btn4.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.asthmaIncrementCountCk(); // count 값 증가
                    btn5.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.asthmaDecrementCountCk(); // count 값 감소
                    btn5.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.depressionIncrementCountCk(); // count 값 증가
                    btn6.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.depressionDecrementCountCk(); // count 값 감소
                    btn6.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.arthritisIncrementCountCk(); // count 값 증가
                    btn7.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.arthritisDecrementCountCk(); // count 값 감소
                    btn7.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.astrokeIncrementCountCk(); // count 값 증가
                    btn8.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.astrokeDecrementCountCk(); // count 값 감소
                    btn8.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.ironIncrementCountCk(); // count 값 증가
                    btn9.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.ironDecrementCountCk(); // count 값 감소
                    btn9.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.Vitamin_DIncrementCountCk(); // count 값 증가
                    btn10.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.Vitamin_DDecrementCountCk(); // count 값 감소
                    btn10.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.Vitamin_BIncrementCountCk(); // count 값 증가
                    btn11.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.Vitamin_BDecrementCountCk(); // count 값 감소
                    btn11.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.Vitamin_AIncrementCountCk(); // count 값 증가
                    btn12.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.Vitamin_ADecrementCountCk(); // count 값 감소
                    btn12.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.Vitamin_CIncrementCountCk(); // count 값 증가
                    btn13.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.Vitamin_CDecrementCountCk(); // count 값 감소
                    btn13.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.Omega_3IncrementCountCk(); // count 값 증가
                    btn14.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.Omega_3DecrementCountCk(); // count 값 감소
                    btn14.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.calciumIncrementCountCk(); // count 값 증가
                    btn15.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.calciumDecrementCountCk(); // count 값 감소
                    btn15.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View view) {
                if(isClicked) {
                    health.magnesiumIncrementCountCk(); // count 값 증가
                    btn16.setBackgroundColor(Color.GRAY); // 버튼의 배경색을 변경
                } else{
                    health.magnesiumDecrementCountCk(); // count 값 감소
                    btn16.setBackgroundColor(Color.parseColor("#6750A4")); // 버튼의 배경색을 기본으로 변경
                }
                isClicked = !isClicked; // 클릭 상태 업데이트
            }
        });

    }

    public void onIconClick1(View view){
        Health health = (Health)getApplicationContext();
        ImageView add = findViewById(R.id.add1);
        EditText editText = findViewById(R.id.editText1);
        ImageView plus1 = findViewById(R.id.plus1);

        if(editText.getVisibility()==View.GONE){
            editText.setVisibility(View.VISIBLE);
            plus1.setVisibility(View.VISIBLE);
        }
        Log.d("checktest",editText.getText().toString());
        if(editText.getText().toString().length()==0){
            //
        }
        else{

            health.setEdit_dis_1(editText.getText().toString());
        }
    }

    public void onIconClick2(View view){
        ImageView plus1 = findViewById(R.id.plus1);
        EditText plusEditText2 = findViewById(R.id.plusEditText2);
        ImageView plus2 = findViewById(R.id.plus2);

        if(plusEditText2.getVisibility()==View.GONE){
            plusEditText2.setVisibility(View.VISIBLE);
            plus2.setVisibility(View.VISIBLE);
        }
    }

    public void onIconClick3(View view){
        ImageView plus2 = findViewById(R.id.plus2);
        EditText plusEditText3 = findViewById(R.id.plusEditText3);

        if(plusEditText3.getVisibility()==View.GONE){
            plusEditText3.setVisibility(View.VISIBLE);
        }
    }

    public void onIconClick4(View view){
        ImageView add = findViewById(R.id.add2);
        EditText editText = findViewById(R.id.editText2);
        ImageView plus1 = findViewById(R.id.plus4);

        if(editText.getVisibility()==View.GONE){
            editText.setVisibility(View.VISIBLE);
            plus1.setVisibility(View.VISIBLE);
        }
    }

    public void onIconClick5(View view){
        ImageView add = findViewById(R.id.plus4);
        EditText editText = findViewById(R.id.plusEditText4);
        ImageView plus1 = findViewById(R.id.plus5);

        if(editText.getVisibility()==View.GONE){
            editText.setVisibility(View.VISIBLE);
            plus1.setVisibility(View.VISIBLE);
        }
    }

    public void onIconClick6(View view){
        ImageView plus2 = findViewById(R.id.plus5);
        EditText plusEditText3 = findViewById(R.id.plusEditText5);

        if(plusEditText3.getVisibility()==View.GONE){
            plusEditText3.setVisibility(View.VISIBLE);
        }
    }

}