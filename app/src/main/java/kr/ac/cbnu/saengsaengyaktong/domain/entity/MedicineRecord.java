package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class MedicineRecord {
    private String id;
    private String userId;
    private Date date;
    private Map<String, Date> breakfast, lunch, dinner;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final MedicineRecord other = (MedicineRecord) obj;
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

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Date> getBreakfast() {
        return breakfast;
    }

    public Map<String, Date> getLunch() {
        return lunch;
    }

    public Map<String, Date> getDinner() {
        return dinner;
    }

    public void addBreakfastRecord(String medicineId) {
        breakfast.put(medicineId, new Date());
    }

    public void addLunchRecord(String medicineId) {
        dinner.put(medicineId, new Date());
    }

    public void addDinnerRecord(String medicineId) {
        dinner.put(medicineId, new Date());
    }

    public void removeBreakfastRecord(String medicineId) {
        breakfast.remove(medicineId);
    }

    public void removeLunchRecord(String medicineId) {
        lunch.remove(medicineId);
    }

    public void removeDinnerRecord(String medicineId) {
        dinner.remove(medicineId);
    }

    public MedicineRecord withId(String id) {
        this.id = id;
        return this;
    }
}
