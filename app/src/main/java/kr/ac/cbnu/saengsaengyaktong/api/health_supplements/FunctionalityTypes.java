package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

public class FunctionalityTypes {
    public static FunctionalityType getById(String id) {
        for (FunctionalityType type : FunctionalityType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }

        return null;
    }
}
