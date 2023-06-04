package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class HospitalListResponse {
    @Element()
    private Body body;

    public Body getBody() {
        return body;
    }

    @Root(name = "body", strict = false)
    public static class Body {
        @Path("items")
        @ElementList(entry = "item", inline = true, required = false)
        private List<HospitalInfo> items;

        @Element()
        private int totalCount;

        public List<HospitalInfo> getItems() {
            return items;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }
}
