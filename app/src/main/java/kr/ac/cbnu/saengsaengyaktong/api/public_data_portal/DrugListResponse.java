package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import java.util.List;

import javax.annotation.Nullable;

public class DrugListResponse {
    private Body body;

    public Body getBody() {
        return body;
    }

    public static class Body {
        private int totalCount;

        @Nullable
        private List<DrugInfo> items;

        public int getTotalCount() {
            return totalCount;
        }

        @Nullable
        public List<DrugInfo> getItems() {
            return items;
        }
    }
}