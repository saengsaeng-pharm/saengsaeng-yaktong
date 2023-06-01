package kr.ac.cbnu.saengsaengyaktong.domain.entity;

import java.util.Objects;

public class UserProfile {
    private String id;
    private String name;
    private String birthDate;
    private String gender;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final UserProfile other = (UserProfile) obj;
        return Objects.equals(other.id, id)
                && Objects.equals(other.name, name)
                && Objects.equals(other.birthDate, birthDate)
                && Objects.equals(other.gender, gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, gender);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }
}
