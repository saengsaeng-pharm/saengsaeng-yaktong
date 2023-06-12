package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.api.ProductInfo;
import kr.ac.cbnu.saengsaengyaktong.api.ProductType;
import kr.ac.cbnu.saengsaengyaktong.utils.StringUtils;

public class DrugInfo implements ProductInfo, Parcelable {
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
    @SerializedName("itemImage")
    private String imageUrl;

    private DrugInfo(Parcel in) {
        companyName = in.readString();
        name = in.readString();
        id = in.readString();
        efficacy = in.readString();
        useMethod = in.readString();
        warning = in.readString();
        attention = in.readString();
        interactions = in.readString();
        sideEffects = in.readString();
        depositMethod = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public ProductType getType() {
        return ProductType.DRUG;
    }

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
        if (efficacy == null) return null;
        return StringUtils.removeHtml(efficacy).trim();
    }

    @Nullable
    public String getUseMethod() {
        if (useMethod == null) return null;
        return StringUtils.removeHtml(useMethod).trim();
    }

    @Nullable
    public String getWarning() {
        if (warning == null) return null;
        return StringUtils.removeHtml(warning).trim();
    }

    @Nullable
    public String getAttention() {
        if (attention == null) return null;
        return StringUtils.removeHtml(attention).trim();
    }

    @Nullable
    public String getInteractions() {
        if (interactions == null) return null;
        return StringUtils.removeHtml(interactions).trim();
    }

    @Nullable
    public String getSideEffects() {
        if (sideEffects == null) return null;
        return StringUtils.removeHtml(sideEffects).trim();
    }

    @Nullable
    public String getDepositMethod() {
        if (depositMethod == null) return null;
        return StringUtils.removeHtml(depositMethod).trim();
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(companyName);
        out.writeString(name);
        out.writeString(id);
        out.writeString(efficacy);
        out.writeString(useMethod);
        out.writeString(warning);
        out.writeString(attention);
        out.writeString(interactions);
        out.writeString(sideEffects);
        out.writeString(depositMethod);
        out.writeString(imageUrl);
    }

    public static final Parcelable.Creator<DrugInfo> CREATOR = new Parcelable.Creator<DrugInfo>() {
        public DrugInfo createFromParcel(Parcel in) {
            return new DrugInfo(in);
        }

        public DrugInfo[] newArray(int size) {
            return new DrugInfo[size];
        }
    };
}
