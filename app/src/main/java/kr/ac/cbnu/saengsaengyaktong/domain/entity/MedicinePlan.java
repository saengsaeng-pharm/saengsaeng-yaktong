package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import java.util.Objects;

public class MedicinePlan {
    private String id;
    private String userId;
    private String medicineId;
    private String medicineName;
    private String memo;
    private String imageUrl;
    private Integer breakfastDelay, lunchDelay, dinnerDelay;

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
                && Objects.equals(other.breakfastDelay, breakfastDelay)
                && Objects.equals(other.lunchDelay, lunchDelay)
                && Objects.equals(other.dinnerDelay, dinnerDelay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, medicineId, medicineName, memo, imageUrl, breakfastDelay, lunchDelay, dinnerDelay);
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

    public String getMemo() {
        return memo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getBreakfastDelay() {
        return breakfastDelay;
    }

    public Integer getLunchDelay() {
        return lunchDelay;
    }

    public Integer getDinnerDelay() {
        return dinnerDelay;
    }

    public MedicinePlan withId(String id) {
        this.id = id;
        return this;
    }
}
