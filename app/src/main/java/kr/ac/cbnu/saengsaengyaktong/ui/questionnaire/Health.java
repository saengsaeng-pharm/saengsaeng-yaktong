package kr.ac.cbnu.saengsaengyaktong.ui.questionnaire;

import android.app.Application;

// my_page 부분에서 표시하기 위해 사용
// 0을 기준으로 0이 아니면 이 정보를 이용하여 질병 혹은 상태명 표시하여 사용
public class Health extends Application {

    private int diabetes_ck; // 당뇨
    private int rhinitis_ck; // 비염
    private int angina_pectoris_ck; // 협심증
    private int hepatitis_ck; // 간염
    private int asthma_ck; // 천식
    private int depression_ck; // 우울증
    private int arthritis_ck; // 관절염
    private int a_stroke_ck; // 뇌졸증
    private int iron;
    private int Vitamin_D;
    private int Vitamin_B;
    private int Vitamin_A;
    private int Vitamin_C;
    private int Omega_3;
    private int calcium;
    private int magnesium;

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
    public void onCreate(){
        // private 생성자
        // 설문조사에서 얻은 정보
        iron = 0;
        Vitamin_D = 0;
        Vitamin_B = 0;
        Vitamin_A = 0;
        Vitamin_C = 0;
        Omega_3 = 0;
        calcium = 0;
        magnesium = 0;

        // 자가진단에서 자신이 직접 체크하는 부분
        diabetes_ck = 0; // 당뇨
        rhinitis_ck = 0; // 비염
        angina_pectoris_ck = 0; // 협심증
        hepatitis_ck = 0; // 간염
        asthma_ck = 0; // 천식
        depression_ck = 0; // 우울증
        arthritis_ck = 0; // 관절염
        a_stroke_ck = 0; // 뇌졸증
        iron_ck = 0;
        Vitamin_D_ck = 0;
        Vitamin_B_ck = 0;
        Vitamin_A_ck = 0;
        Vitamin_C_ck = 0;
        Omega_3_ck = 0;
        calcium_ck = 0;
        magnesium_ck = 0;

        // 자가 진단 페이지에서 자신이 직접 기입하는 부분
        edit_dis_1 = null;
        edit_dis_2 = null;
        edit_dis_3 = null;

        edit_ing_1 = null;
        edit_ing_2 = null;
        edit_ing_3 = null;
        super.onCreate();
    }

    public int getIron(){ return iron;}
    public int getVitaminD() { return Vitamin_D; }
    public int getVitaminB() { return Vitamin_B; }
    public int getVitaminA() { return Vitamin_A; }
    public int getVitaminC() { return Vitamin_C; }
    public int getOmega_3() { return Omega_3; }
    public int getCalcium() { return calcium; }
    public int getMagnesium() { return magnesium; }

    public int getDiabetesCk(){ return diabetes_ck; }
    public int getRhinitisCk() { return rhinitis_ck; }
    public int getAnginaPectorisCk() { return angina_pectoris_ck; }
    public int getHepatitisCk() { return hepatitis_ck; }
    public int getAsthmaCk() { return asthma_ck; }
    public int getDepressionCk() { return depression_ck; }
    public int getArthritisCk() { return arthritis_ck; }
    public int getAStrokeCk() { return a_stroke_ck; }

    public int getIronCk(){ return iron_ck; }
    public int getVitaminDCk() { return Vitamin_D_ck; }
    public int getVitaminBCk() { return Vitamin_B_ck; }
    public int getVitaminACk() { return Vitamin_A_ck; }
    public int getVitaminCCk() { return Vitamin_C_ck; }
    public int getOmega_3Ck() { return Omega_3_ck; }
    public int getCalciumCk() { return calcium_ck; }
    public int getMagnesiumCk() { return magnesium_ck; }

    public String getEditDisCk1() { return edit_dis_1; }
    public String getEditDisCk2() { return edit_dis_2; }
    public String getEditDisCk3() { return edit_dis_3; }
    public String getEditIngCk1() { return edit_ing_1; }
    public String getEditIngCk2() { return edit_ing_2; }
    public String getEditIngCk3() { return edit_ing_3; }



    // 설문조사에서 참고하는 부분
    public void ironIncrementCount(){
        iron++;
    }

    public void ironDecrementCount(){
        if(iron > 0){
            iron--;
        }
    }

    public void Vitamin_DIncrementCount(){ Vitamin_D++; }

    public void Vitamin_DDecrementCount(){
        if(Vitamin_D > 0) {
            Vitamin_D--;
        }
    }


     public void Vitamin_BIncrementCount(){ Vitamin_B++; }

     public void Vitamin_BDecrementCount() {
         if (Vitamin_B > 0) {
             Vitamin_B--;
         }
     }

     public void Vitamin_AIncrementCount(){ Vitamin_A++; }

     public void Vitamin_ADecrementCount(){
        if(Vitamin_A > 0){
            Vitamin_A--;
        }
    }

    public void Vitamin_CIncrementCount(){ Vitamin_C++; }

    public void Vitamin_CDecrementCount(){
        if(Vitamin_C > 0){
            Vitamin_C--;
        }
    }




    // 스스로 체크하는 부분
    public void diabetesIncrementCountCk(){ diabetes_ck = 1;}
    public void diabetesDecrementCountCk(){ diabetes_ck = 0; }
    public void rhinitisIncrementCountCk(){ rhinitis_ck = 1;}
    public void rhinitisDecrementCountCk(){ rhinitis_ck = 0; }
    public void anginaPectorisIncrementCountCk(){ angina_pectoris_ck = 1;}
    public void anginaPectorisDecrementCountCk(){ angina_pectoris_ck = 0; }
    public void hepatitisIncrementCountCk(){ hepatitis_ck = 1;}
    public void hepatitisDecrementCountCk(){ hepatitis_ck = 0; }
    public void asthmaIncrementCountCk(){ asthma_ck = 1;}
    public void asthmaDecrementCountCk(){ asthma_ck = 0; }
    public void depressionIncrementCountCk(){ depression_ck = 1;}
    public void depressionDecrementCountCk(){ depression_ck = 0; }
    public void arthritisIncrementCountCk(){ arthritis_ck = 1;}
    public void arthritisDecrementCountCk(){ arthritis_ck = 0; }
    public void astrokeIncrementCountCk(){ a_stroke_ck = 1;}
    public void astrokeDecrementCountCk(){ a_stroke_ck = 0; }

    public void ironIncrementCountCk(){ iron_ck = 1;}

    public void ironDecrementCountCk(){ iron_ck = 0; }

    public void Vitamin_DIncrementCountCk(){ Vitamin_D_ck = 1; }

    public void Vitamin_DDecrementCountCk(){ Vitamin_D_ck = 0; }


    public void Vitamin_BIncrementCountCk(){ Vitamin_B_ck = 1; }

    public void Vitamin_BDecrementCountCk(){ Vitamin_B_ck = 0; }


    public void Vitamin_AIncrementCountCk(){ Vitamin_A_ck = 1; }

    public void Vitamin_ADecrementCountCk(){ Vitamin_A_ck = 0; }


    public void Vitamin_CIncrementCountCk(){ Vitamin_C_ck = 1; }

    public void Vitamin_CDecrementCountCk(){ Vitamin_C_ck = 0; }

    public void Omega_3IncrementCountCk(){ Omega_3_ck = 1; }

    public void Omega_3DecrementCountCk(){ Omega_3_ck = 0; }

    public void calciumIncrementCountCk(){ calcium_ck = 1; }

    public void calciumDecrementCountCk(){ calcium_ck = 0; }


    public void magnesiumIncrementCountCk(){ magnesium_ck = 1; }

    public void magnesiumDecrementCountCk(){ magnesium_ck = 0; }

    public void setEdit_dis_1(String S){
        edit_dis_1 += S;
    }



}
