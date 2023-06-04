package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

import com.google.gson.annotations.SerializedName;

public class FoodDetailInfo {
    @SerializedName("PRDLST_REPORT_NO")
    private String id;

    @SerializedName("PRDLST_NM")
    private String name;

    @SerializedName("IFTKN_ATNT_MATR_CN")
    private String cautions;

    @SerializedName("BSSH_NM")
    private String companyName;

    @SerializedName("ETC_RAWMTRL_NM")
    private String etcRawMaterials;

    @SerializedName("STDR_STND")
    private String standards;

    @SerializedName("DISPOS")
    private String description;

    @SerializedName("PRIMARY_FNCLTY")
    private String functionalities;

    @SerializedName("FRMLC_MTRQLT")
    private String packingMaterial;

    @SerializedName("POG_DAYCNT")
    private String expiryDate;

    @SerializedName("CSTDY_MTHD")
    private String custodyMethod;

    @SerializedName("INDIV_RAWMTRL_NM")
    private String otherMaterials;

    @SerializedName("NTK_MTHD")
    private String intakeMethod;

    @SerializedName("RAWMTRL_NM")
    private String functionalityMaterials;

    @SerializedName("CAP_RAWMTRL_NM")
    private String capsuleMaterials;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCautions() {
        return cautions;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEtcRawMaterials() {
        return etcRawMaterials;
    }

    public String getStandards() {
        return standards;
    }

    public String getDescription() {
        return description;
    }

    public String getFunctionalities() {
        return functionalities;
    }

    public String getPackingMaterial() {
        return packingMaterial;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCustodyMethod() {
        return custodyMethod;
    }

    public String getOtherMaterials() {
        return otherMaterials;
    }

    public String getIntakeMethod() {
        return intakeMethod;
    }

    public String getFunctionalityMaterials() {
        return functionalityMaterials;
    }

    public String getCapsuleMaterials() {
        return capsuleMaterials;
    }
}
