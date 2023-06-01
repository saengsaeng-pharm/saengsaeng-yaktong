package kr.ac.cbnu.saengsaengyaktong.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrugListResponse {
    public static class Body {
        @SerializedName("items")
        private List<DrugItem> items;

        public List<DrugItem> getItems() {
            return items;
        }
    }

    public static class DrugItem {
        @SerializedName("entpName")
        private String entpName;

        @SerializedName("itemName")
        private String itemName;

        @SerializedName("itemSeq")
        private String itemSeq;

        @SerializedName("efcyQesitm")
        private String efcyQesitm;

        @SerializedName("useMethodQesitm")
        private String useMethodQesitm;

        @SerializedName("atpnWarnQesitm")
        private String atpnWarnQesitm;

        @SerializedName("atpnQesitm")
        private String atpnQesitm;

        @SerializedName("intrcQesitm")
        private String intrcQesitm;

        @SerializedName("seQesitm")
        private String seQesitm;

        @SerializedName("depositMethodQesitm")
        private String depositMethodQesitm;

        @SerializedName("itemImage")
        private String itemImage;

        public String getEntpName() {
            return entpName;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemSeq() {
            return itemSeq;
        }

        public String getEfcyQesitm() {
            return efcyQesitm;
        }

        public String getUseMethodQesitm() {
            return useMethodQesitm;
        }

        public String getAtpnWarnQesitm() {
            return atpnWarnQesitm;
        }

        public String getAtpnQesitm() {
            return atpnQesitm;
        }

        public String getIntrcQesitm() {
            return intrcQesitm;
        }

        public String getSeQesitm() {
            return seQesitm;
        }

        public String getDepositMethodQesitm() {
            return depositMethodQesitm;
        }

        public String getItemImage() {
            return itemImage;
        }
    }
}