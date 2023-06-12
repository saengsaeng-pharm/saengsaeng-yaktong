package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.api.ProductType;

public class DailyDrugRecord {
    @Nullable
    private String id;
    private String userId;
    private ProductType drugType;
    private String drugId;
    private Date date;
    @Nullable
    private Date breakfast, lunch, dinner;

    public DailyDrugRecord() {
    }

    public DailyDrugRecord(String userId, ProductType drugType, String drugId, Date date) {
        this.userId = userId;
        this.drugType = drugType;
        this.drugId = drugId;
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DailyDrugRecord other = (DailyDrugRecord) obj;
        return Objects.equals(other.id, id)
                && Objects.equals(other.userId, userId)
                && Objects.equals(other.date, date)
                && Objects.equals(other.breakfast, breakfast)
                && Objects.equals(other.lunch, lunch)
                && Objects.equals(other.dinner, dinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, breakfast, lunch, dinner);
    }

    @Exclude
    public String getId() {
        return id;
    }

    @PropertyName("user_id")
    public String getUserId() {
        return userId;
    }

    @PropertyName("drug_type")
    public ProductType getDrugType() {
        return drugType;
    }

    @PropertyName("drug_id")
    public String getDrugId() {
        return drugId;
    }

    public Date getDate() {
        return date;
    }

    public Date getBreakfast() {
        return breakfast;
    }

    public Date getLunch() {
        return lunch;
    }

    public Date getDinner() {
        return dinner;
    }

    @PropertyName("user_id")
    public void setUserId(String value) {
        userId = value;
    }

    @PropertyName("drug_type")
    public void setDrugType(ProductType value) {
        drugType = value;
    }

    @PropertyName("drug_id")
    public void setDrugId(String value) {
        drugId = value;
    }

    public void setBreakfast(Date value) {
        breakfast = value;
    }

    public void setLunch(Date value) {
        lunch = value;
    }

    public void setDinner(Date value) {
        dinner = value;
    }

    public DailyDrugRecord withId(String id) {
        this.id = id;
        return this;
    }
}
