package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

public enum FunctionalityCategory {
    DISEASE_RISK_REDUCTION("질병발생위험감소기능"),

    NUTRITIONAL_FUNCTION("영양소 기능"),

    BIOLOGICAL_ACTIVITY("생리활성기능");

    private final String name;

    FunctionalityCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
