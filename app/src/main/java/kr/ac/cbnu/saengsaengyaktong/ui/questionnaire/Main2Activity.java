package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.ac.cbnu.saengsaengyaktong.R;

public class Main2Activity extends AppCompatActivity {

    private int iron;
    private int Vitamin_D;
    private int Vitamin_B;
    private int Vitamin_A;
    private int Vitamin_C;
    private int Omega_3;
    private int calcium;
    private int magnesium;

    private int diabetes_ck;
    private int rhinitis_ck;
    private int angina_pectoris_ck;
    private int hepatitis_ck;
    private int asthma_ck;
    private int depression_ck;
    private int arthritis_ck;
    private int a_stroke_ck;

    private int iron_ck;
    private int Vitamin_D_ck;
    private int Vitamin_B_ck;
    private int Vitamin_A_ck;
    private int Vitamin_C_ck;
    private int Omega_3_ck;
    private int calcium_ck;
    private int magnesium_ck;

    private String edit_dis_1;
    private String edit_dis_2;
    private String edit_dis_3;

    private String edit_ing_1;
    private String edit_ing_2;
    private String edit_ing_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Health health = new Health();

        Button bt = (Button) findViewById(R.id.bt1);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), CheckTest.class);
                startActivity(intent);
            }
        });

        iron = health.getIron();
        Vitamin_D = health.getVitaminD();
        Vitamin_B = health.getVitaminB();
        Vitamin_A = health.getVitaminA();
        Vitamin_C = health.getVitaminC();
        Omega_3 = health.getOmega_3();
        calcium = health.getCalcium();
        magnesium = health.getMagnesium();
        iron_ck = health.getIronCk();
        Vitamin_D_ck = health.getVitaminDCk();
        Vitamin_B_ck = health.getVitaminBCk();
        Vitamin_A_ck = health.getVitaminACk();
        Vitamin_C_ck = health.getVitaminCCk();
        Omega_3_ck = health.getOmega_3Ck();
        calcium_ck = health.getCalciumCk();
        magnesium_ck = health.getMagnesiumCk();
        edit_dis_1 = health.getEditDisCk1();
        edit_dis_2 = health.getEditDisCk2();
        edit_dis_3 = health.getEditDisCk3();
        edit_ing_1 = health.getEditIngCk1();
        edit_ing_2 = health.getEditIngCk2();
        edit_ing_3 = health.getEditIngCk3();
        diabetes_ck = health.getDiabetesCk();
        rhinitis_ck = health.getRhinitisCk();
        angina_pectoris_ck = health.getAnginaPectorisCk();
        hepatitis_ck = health.getHepatitisCk();
        asthma_ck = health.getAsthmaCk();
        depression_ck = health.getDepressionCk();
        arthritis_ck = health.getArthritisCk();
        a_stroke_ck = health.getAStrokeCk();

        String[] text = new String[8];
        String[] text1 = new String[8];

        TextView textView = findViewById(R.id.disease);
        textView.setText(edit_dis_1);
        //Log.d("edit_dis_1",edit_dis_1);
        if(diabetes_ck != 0) {
            text[0] = "당뇨";
        }
        if(rhinitis_ck != 0){
            text[1] = "비염";
        }
        if(angina_pectoris_ck != 0){
            text[2] = "협심증";
        }
        if(hepatitis_ck != 0){
            text[3] = "간염";
        }
        if(asthma_ck != 0){
            text[4] = "천식";
        }
        if(depression_ck != 0){
            text[5] = "우울증";
        }
        if(arthritis_ck != 0){
            text[6] = "관절염";
        }
        if(a_stroke_ck != 0){
            text[7] = "뇌졸증";
        }

        String combined_text = "";

        // 가지고 있는 부족 성분 출력
        for(int i = 0; i < 8; i++){
            if(text[i] != null){
                combined_text = text[i] + "";
            }
        }
        textView.setText(combined_text);

        TextView textView1 = findViewById(R.id.ingredient);
        if(iron != 0 || iron_ck != 0) {
            text1[0] = "철분";
        }
        if(Vitamin_D != 0 || Vitamin_D_ck != 0){
            text1[1] = "비타민D";
        }
        if(Vitamin_B != 0 || Vitamin_B_ck != 0){
            text1[2] = "비타민B";
        }
        if(Vitamin_A != 0 || Vitamin_A_ck != 0){
            text1[3] = "비타민A";
        }
        if(Vitamin_C != 0 || Vitamin_C_ck != 0){
            text1[4] = "비타민C";
        }
        if(Omega_3 != 0 || Omega_3_ck != 0){
            text1[5] = "오메가3";
        }
        if(calcium != 0 || calcium_ck != 0){
            text1[6] = "칼륨";
        }
        if(magnesium != 0 || magnesium_ck != 0){
            text1[7] = "칼슘";
        }

        String combined_text1 = "";

        // 가지고 있는 부족 성분 출력
        for(int i = 0; i < 8; i++){
            if(text1[i] != null){
                Log.d("text1", String.valueOf(text1[i]));
                combined_text1 += text1[i] + " ";
            }
        }
        textView1.setText(combined_text1);



    }

}