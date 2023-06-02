package kr.ac.cbnu.saengsaengyaktong.api;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class DrugItem {
    @SerializedName("entpName")
    private String companyName;

    @SerializedName("itemName")
    private String name;

    @SerializedName("itemSeq")
    private String id;

    @Nullable
    @SerializedName("efcyQesitm")
    private String efficacy;

    @Nullable
    @SerializedName("useMethodQesitm")
    private String useMethod;

    @Nullable
    @SerializedName("atpnWarnQesitm")
    private String warning;

    @Nullable
    @SerializedName("atpnQesitm")
    private String attention;

    @Nullable
    @SerializedName("intrcQesitm")
    private String interactions;

    @Nullable
    @SerializedName("seQesitm")
    private String sideEffects;

    @Nullable
    @SerializedName("depositMethodQesitm")
    private String depositMethod;

    @Nullable
    private String itemImage;

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public String getEfficacy() {
        return efficacy;
    }

    @Nullable
    public String getUseMethod() {
        return useMethod;
    }

    @Nullable
    public String getWarning() {
        return warning;
    }

    @Nullable
    public String getAttention() {
        return attention;
    }

    @Nullable
    public String getInteractions() {
        return interactions;
    }

    @Nullable
    public String getSideEffects() {
        return sideEffects;
    }

    @Nullable
    public String getDepositMethod() {
        return depositMethod;
    }

    @Nullable
    public String getItemImage() {
        return itemImage;
    }
}
