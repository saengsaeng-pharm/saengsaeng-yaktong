package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import kr.ac.cbnu.saengsaengyaktong.utils.StringUtils;

public class DrugPermitInfo {
    @SerializedName("ITEM_SEQ")
    private String id;

    @SerializedName("ITEM_NAME")
    private String name;

    @SerializedName("ENTP_NAME")
    private String companyName;

    @SerializedName("ETC_OTC_CODE")
    private String type;

    @Nullable
    @SerializedName("CHART")
    private String description;

    @Nullable
    @SerializedName("MATERIAL_NAME")
    private String ingredients;

    @Nullable
    @SerializedName("STORAGE_METHOD")
    private String storageMethod;

    @Nullable
    @SerializedName("VALID_TERM")
    private String validTerm;

    @Nullable
    @SerializedName("PACK_UNIT")
    private String packUnit;

    @Nullable
    @SerializedName("GBN_NAME")
    private String changeHistory;

    @Nullable
    @SerializedName("TOTAL_CONTENT")
    private String totalContent;

    @Nullable
    @SerializedName("EE_DOC_DATA")
    private String efficacy;

    @Nullable
    @SerializedName("UD_DOC_DATA")
    private String usageDose;

    @Nullable
    @SerializedName("NB_DOC_DATA")
    private String notaBene;

    @Nullable
    @SerializedName("PN_DOC_DATA")
    private String professionalNotes;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getType() {
        return type;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getIngredients() {
        if (ingredients == null) return null;
        return ingredients.replace(';', '\n').trim();
    }

    @Nullable
    public String getStorageMethod() {
        return storageMethod;
    }

    @Nullable
    public String getValidTerm() {
        return validTerm;
    }

    @Nullable
    public String getPackUnit() {
        return packUnit;
    }

    @Nullable
    public String getChangeHistory() {
        return changeHistory;
    }

    @Nullable
    public String getTotalContent() {
        return totalContent;
    }

    @Nullable
    public String getEfficacy() {
        if (efficacy == null) return null;
        return StringUtils.removeHtml(efficacy).trim();
    }

    @Nullable
    public String getUsageDose() {
        if (usageDose == null) return null;
        return StringUtils.removeHtml(usageDose).trim();
    }

    @Nullable
    public String getNotaBene() {
        if (notaBene == null) return null;
        return StringUtils.removeHtml(notaBene).trim();
    }

    @Nullable
    public String getProfessionalNotes() {
        if (professionalNotes == null) return null;
        return StringUtils.removeHtml(professionalNotes).trim();
    }
}
