package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import android.os.Build;

import java.time.LocalTime;
import java.util.Objects;

import javax.annotation.Nullable;

public class MedicinePlan {
    @Nullable
    private String id;
    private final String userId;
    private final String medicineId;
    private String medicineName;
    @Nullable
    private String memo;
    @Nullable
    private String imageUrl;
    @Nullable
    private Integer breakfastTime, lunchTime, dinnerTime;

    public MedicinePlan(String userId, String medicineId, String medicineName, @Nullable String memo, @Nullable String imageUrl, @Nullable LocalTime breakfastTime, @Nullable LocalTime lunchTime, @Nullable LocalTime dinnerTime) {
        this.userId = userId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.memo = memo;
        this.imageUrl = imageUrl;
        setBreakfastTime(breakfastTime);
        setLunchTime(lunchTime);
        setDinnerTime(dinnerTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final MedicinePlan other = (MedicinePlan) obj;
        return Objects.equals(other.id, id)
                && Objects.equals(other.userId, userId)
                && Objects.equals(other.medicineId, medicineId)
                && Objects.equals(other.medicineName, medicineName)
                && Objects.equals(other.memo, memo)
                && Objects.equals(other.imageUrl, imageUrl)
                && Objects.equals(other.breakfastTime, breakfastTime)
                && Objects.equals(other.lunchTime, lunchTime)
                && Objects.equals(other.dinnerTime, dinnerTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, medicineId, medicineName, memo, imageUrl, breakfastTime, lunchTime, dinnerTime);
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    @Nullable
    public String getMemo() {
        return memo;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @Nullable
    public LocalTime getBreakfastTime() {
        if (breakfastTime != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.ofSecondOfDay(breakfastTime);
        }

        return null;
    }

    @Nullable
    public LocalTime getLunchTime() {
        if (lunchTime != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.ofSecondOfDay(lunchTime);
        }

        return null;
    }

    @Nullable
    public LocalTime getDinnerTime() {
        if (dinnerTime != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.ofSecondOfDay(dinnerTime);
        }

        return null;
    }

    public void setMedicineName(String value) {
        medicineName = value;
    }

    public void setMemo(@Nullable String value) {
        memo = value;
    }

    public void setImageUrl(@Nullable String value) {
        imageUrl = value;
    }

    public void setBreakfastTime(@Nullable LocalTime value) {
        if (value != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            breakfastTime = value.toSecondOfDay();
        }
    }

    public void setLunchTime(@Nullable LocalTime value) {
        if (value != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lunchTime = value.toSecondOfDay();
        }
    }

    public void setDinnerTime(@Nullable LocalTime value) {
        if (value != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dinnerTime = value.toSecondOfDay();
        }
    }

    public MedicinePlan withId(String id) {
        this.id = id;
        return this;
    }
}
