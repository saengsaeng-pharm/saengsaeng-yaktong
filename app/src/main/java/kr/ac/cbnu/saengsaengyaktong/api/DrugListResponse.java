package kr.ac.cbnu.saengsaengyaktong.api;

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
        private List<DrugItem> items;

        public int getTotalCount() {
            return totalCount;
        }

        @Nullable
        public List<DrugItem> getItems() {
            return items;
        }
    }
}