package kr.ac.cbnu.saengsaengyaktong.api;

import com.google.gson.annotations.SerializedName;

public class PharmacyInfo {
    @SerializedName("dutyAddr")
    private String address;

    @SerializedName("dutyEtc")
    private String etc;

    @SerializedName("dutyMapimg")
    private String directions;

    @SerializedName("dutyName")
    private String name;

    @SerializedName("dutyTel1")
    private String telephone;

    @SerializedName("dutyTime1c")
    private int closeTimeMon;

    @SerializedName("dutyTime2c")
    private int closeTimeTue;

    @SerializedName("dutyTime3c")
    private int closeTimeWed;

    @SerializedName("dutyTime4c")
    private int closeTimeThu;

    @SerializedName("dutyTime5c")
    private int closeTimeFri;

    @SerializedName("dutyTime6c")
    private int closeTimeSat;

    @SerializedName("dutyTime7c")
    private int closeTimeSun;

    @SerializedName("dutyTime8c")
    private int closeTimeHoliday;

    @SerializedName("dutyTime1s")
    private String openTimeMon;

    @SerializedName("dutyTime2s")
    private String openTimeTue;

    @SerializedName("dutyTime3s")
    private String openTimeWed;

    @SerializedName("dutyTime4s")
    private String openTimeThu;

    @SerializedName("dutyTime5s")
    private String openTimeFri;

    @SerializedName("dutyTime6s")
    private String openTimeSat;

    @SerializedName("dutyTime7s")
    private String openTimeSun;

    @SerializedName("dutyTime8s")
    private String openTimeHoliday;

    @SerializedName("hpid")
    private String id;

    @SerializedName("wgs84Lon")
    private double longitude;

    @SerializedName("wgs84Lat")
    private double latitude;

    public String getAddress() {
        return address;
    }

    public String getEtc() {
        return etc;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getCloseTimeMon() {
        return closeTimeMon;
    }

    public int getCloseTimeTue() {
        return closeTimeTue;
    }

    public int getCloseTimeWed() {
        return closeTimeWed;
    }

    public int getCloseTimeThu() {
        return closeTimeThu;
    }

    public int getCloseTimeFri() {
        return closeTimeFri;
    }

    public int getCloseTimeSat() {
        return closeTimeSat;
    }

    public int getCloseTimeSun() {
        return closeTimeSun;
    }

    public int getCloseTimeHoliday() {
        return closeTimeHoliday;
    }

    public String getOpenTimeMon() {
        return openTimeMon;
    }

    public String getOpenTimeTue() {
        return openTimeTue;
    }

    public String getOpenTimeWed() {
        return openTimeWed;
    }

    public String getOpenTimeThu() {
        return openTimeThu;
    }

    public String getOpenTimeFri() {
        return openTimeFri;
    }

    public String getOpenTimeSat() {
        return openTimeSat;
    }

    public String getOpenTimeSun() {
        return openTimeSun;
    }

    public String getOpenTimeHoliday() {
        return openTimeHoliday;
    }

    public String getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
