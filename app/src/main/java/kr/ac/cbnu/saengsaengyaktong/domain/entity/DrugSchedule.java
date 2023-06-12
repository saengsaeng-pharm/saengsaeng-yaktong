package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import android.os.Build;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.time.LocalTime;
import java.util.Objects;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.api.ProductType;

public class DrugSchedule {
    @Nullable
    private String id;
    private String userId;
    private ProductType drugType;
    private String drugId;
    private String name;
    @Nullable
    private String memo;
    @Nullable
    private String imageUrl;
    @Nullable
    private Integer breakfastTime, lunchTime, dinnerTime;

    public DrugSchedule() {
    }

    public DrugSchedule(String userId, ProductType drugType, String drugId) {
        this.userId = userId;
        this.drugType = drugType;
        this.drugId = drugId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DrugSchedule other = (DrugSchedule) obj;
        return Objects.equals(other.id, id)
                && Objects.equals(other.userId, userId)
                && Objects.equals(other.drugType, drugType)
                && Objects.equals(other.drugId, drugId)
                && Objects.equals(other.name, name)
                && Objects.equals(other.memo, memo)
                && Objects.equals(other.imageUrl, imageUrl)
                && Objects.equals(other.breakfastTime, breakfastTime)
                && Objects.equals(other.lunchTime, lunchTime)
                && Objects.equals(other.dinnerTime, dinnerTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, drugType, drugId, name, memo, imageUrl, breakfastTime, lunchTime, dinnerTime);
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

    public String getName() {
        return name;
    }

    @Nullable
    public String getMemo() {
        return memo;
    }

    @PropertyName("image_url")
    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @PropertyName("breakfast_time")
    @Nullable
    public Integer getBreakfastTime() {
        return breakfastTime;
    }

    @PropertyName("lunch_time")
    @Nullable
    public Integer getLunchTime() {
        return lunchTime;
    }

    @PropertyName("dinner_time")
    @Nullable
    public Integer getDinnerTime() {
        return dinnerTime;
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

    public void setName(String value) {
        name = value;
    }

    public void setMemo(@Nullable String value) {
        memo = value;
    }

    @PropertyName("image_url")
    public void setImageUrl(@Nullable String value) {
        imageUrl = value;
    }

    @PropertyName("breakfast_time")
    public void setBreakfastTime(@Nullable Integer value) {
        breakfastTime = value;
    }

    @Exclude
    public void setBreakfastTime(@Nullable LocalTime value) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        if (value == null) breakfastTime = null;
        breakfastTime = value.getHour() * 100 + value.getMinute();
    }

    @PropertyName("lunch_time")
    public void setLunchTime(@Nullable Integer value) {
        lunchTime = value;
    }

    @Exclude
    public void setLunchTime(@Nullable LocalTime value) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        if (value == null) lunchTime = null;
        lunchTime = value.getHour() * 100 + value.getMinute();
    }

    @PropertyName("dinner_time")
    public void setDinnerTime(@Nullable Integer value) {
        dinnerTime = value;
    }

    @Exclude
    public void setDinnerTime(@Nullable LocalTime value) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        if (value == null) dinnerTime = null;
        dinnerTime = value.getHour() * 100 + value.getMinute();
    }

    public DrugSchedule withId(String id) {
        this.id = id;
        return this;
    }
}
