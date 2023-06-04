package kr.ac.cbnu.saengsaengyaktong.api.naver;

import java.util.List;

public class ReverseGeocodeResponse {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private Region region;

        public Region getRegion() {
            return region;
        }

        public static class Region {
            private Area area0, area1, area2, area3, area4;

            public Area getArea0() {
                return area0;
            }

            public Area getArea1() {
                return area1;
            }

            public Area getArea2() {
                return area2;
            }

            public Area getArea3() {
                return area3;
            }

            public Area getArea4() {
                return area4;
            }

            public static class Area {
                private String name;

                public String getName() {
                    return name;
                }
            }
        }
    }
}
