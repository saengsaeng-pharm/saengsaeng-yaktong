package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import java.util.List;

public class DrugPermitInfoResponse {
    private Body body;

    public Body getBody() {
        return body;
    }

    public static class Body {
        private int totalCount;

        private List<DrugPermitInfo> items;

        public int getTotalCount() {
            return totalCount;
        }

        public List<DrugPermitInfo> getItems() {
            return items;
        }
    }
}
