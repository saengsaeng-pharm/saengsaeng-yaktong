package kr.ac.cbnu.saengsaengyaktong.ui.detection;

import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;

public class DrugDetectionResult {
    private final DrugInfo drugInfo;
    private final float probability;

    public DrugDetectionResult(DrugInfo drugInfo, float probability) {
        this.drugInfo = drugInfo;
        this.probability = probability;
    }

    public DrugInfo getInfo() {
        return drugInfo;
    }

    public String getId() {
        return drugInfo.getId();
    }

    public String getName() {
        return drugInfo.getName();
    }

    public String getCompanyName() {
        return drugInfo.getCompanyName();
    }

    public String getImageUrl() {
        return drugInfo.getImageUrl();
    }

    public float getProbability() {
        return probability;
    }
}
