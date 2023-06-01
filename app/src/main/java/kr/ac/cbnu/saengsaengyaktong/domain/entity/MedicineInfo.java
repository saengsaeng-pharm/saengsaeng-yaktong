package kr.ac.cbnu.saengsaengyaktong.domain.entity;

public class MedicineInfo {
    private final String id;
    private final String companyName;
    private final String name;
    private final String effect, method, warning, attention, interactions, sideEffects, depositMethod;
    private final String image;

    public MedicineInfo(String id, String companyName, String name, String effect, String method, String warning, String attention, String interactions, String sideEffects, String depositMethod, String image) {
        this.id = id;
        this.companyName = companyName;
        this.name = name;
        this.effect = effect;
        this.method = method;
        this.warning = warning;
        this.attention = attention;
        this.interactions = interactions;
        this.sideEffects = sideEffects;
        this.depositMethod = depositMethod;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public String getMethod() {
        return method;
    }

    public String getWarning() {
        return warning;
    }

    public String getAttention() {
        return attention;
    }

    public String getInteractions() {
        return interactions;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public String getDepositMethod() {
        return depositMethod;
    }

    public String getImage() {
        return image;
    }
}
