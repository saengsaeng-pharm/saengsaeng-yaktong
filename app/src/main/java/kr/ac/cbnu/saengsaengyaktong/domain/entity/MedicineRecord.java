package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import java.util.Date;
import java.util.Objects;

public class MedicineRecord {
    private String id;
    private String userId;
    private String medicineId;
    private Date date;
    private Date breakfast, lunch, dinner;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final MedicineRecord other = (MedicineRecord) obj;
        return Objects.equals(other.id, id)
                && Objects.equals(other.userId, userId)
                && Objects.equals(other.medicineId, medicineId)
                && Objects.equals(other.date, date)
                && Objects.equals(other.breakfast, breakfast)
                && Objects.equals(other.lunch, lunch)
                && Objects.equals(other.dinner, dinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, medicineId, date, breakfast, lunch, dinner);
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

    public MedicineRecord withId(String id) {
        this.id = id;
        return this;
    }
}
