package kr.ac.cbnu.saengsaengyaktong.api;

import java.util.List;

public class PharmacyInfoResponse {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public static class Response {
        private Body body;

        public Body getBody() {
            return body;
        }

        public static class Body {
            private Items items;

            private int totalCount;

            public Items getItems() {
                return items;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public static class Items {
                private List<PharmacyInfo> item;

                public List<PharmacyInfo> getItem() {
                    return item;
                }
            }
        }
    }
}
