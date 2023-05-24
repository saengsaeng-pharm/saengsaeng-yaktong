package kr.ac.cbnu.saengsaengyaktong.model;

import androidx.annotation.NonNull;

public enum GenderType {
    MALE("남성"),
    FEMALE("여성");

    private final String label;

    GenderType(String label) {
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return label;
    }
}
