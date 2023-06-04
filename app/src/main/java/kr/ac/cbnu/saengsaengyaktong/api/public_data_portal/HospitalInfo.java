package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import android.os.Build;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.time.LocalTime;

import javax.annotation.Nullable;

@Root(name = "item", strict = false)
public class HospitalInfo implements InstituteInfo {
    @Element(name = "dutyAddr")
    private String address;

    @Element(name = "dutyDiv")
    private String division;

    @Element(name = "dutyDivNam")
    private String divisionName;

    @Element(name = "dutyEmcls")
    private String emergencyClass;

    @Element(name = "dutyEmclsName")
    private String emergencyClassName;

    @Element(name = "dutyEryn")
    private int emergencyRoom;

    @Nullable
    @Element(name = "dutyEtc", required = false)
    private String etc;

    @Nullable
    @Element(name = "dutyInf", required = false)
    private String information;

    @Nullable
    @Element(name = "dutyMapimg", required = false)
    private String directions;

    @Element(name = "dutyName")
    private String name;

    @Element(name = "dutyTel1")
    private String phoneNumber;

    @Nullable
    @Element(name = "dutyTel3", required = false)
    private String emergencyPhoneNumber;

    @Nullable
    @Element(name = "dutyTime1c", required = false)
    private Integer closeTimeMon;

    @Nullable
    @Element(name = "dutyTime2c", required = false)
    private Integer closeTimeTue;

    @Nullable
    @Element(name = "dutyTime3c", required = false)
    private Integer closeTimeWed;

    @Nullable
    @Element(name = "dutyTime4c", required = false)
    private Integer closeTimeThu;

    @Nullable
    @Element(name = "dutyTime5c", required = false)
    private Integer closeTimeFri;

    @Nullable
    @Element(name = "dutyTime6c", required = false)
    private Integer closeTimeSat;

    @Nullable
    @Element(name = "dutyTime7c", required = false)
    private Integer closeTimeSun;

    @Nullable
    @Element(name = "dutyTime8c", required = false)
    private Integer closeTimeHoliday;

    @Nullable
    @Element(name = "dutyTime1s", required = false)
    private Integer openTimeMon;

    @Nullable
    @Element(name = "dutyTime2s", required = false)
    private Integer openTimeTue;

    @Nullable
    @Element(name = "dutyTime3s", required = false)
    private Integer openTimeWed;

    @Nullable
    @Element(name = "dutyTime4s", required = false)
    private Integer openTimeThu;

    @Nullable
    @Element(name = "dutyTime5s", required = false)
    private Integer openTimeFri;

    @Nullable
    @Element(name = "dutyTime6s", required = false)
    private Integer openTimeSat;

    @Nullable
    @Element(name = "dutyTime7s", required = false)
    private Integer openTimeSun;

    @Nullable
    @Element(name = "dutyTime8s", required = false)
    private Integer openTimeHoliday;

    @Element(name = "hpid")
    private String id;

    @Element(name = "wgs84Lon")
    private double longitude;

    @Element(name = "wgs84Lat")
    private double latitude;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDivision() {
        return division;
    }

    @Override
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public String getEmergencyClass() {
        return emergencyClass;
    }

    @Override
    public String getEmergencyClassName() {
        return emergencyClassName;
    }

    @Override
    public Boolean hasEmergencyRoom() {
        return emergencyRoom == 1;
    }

    @Nullable
    public String getEtc() {
        return etc;
    }

    @Nullable
    @Override
    public String getInformation() {
        return null;
    }

    @Nullable
    @Override
    public String getDirections() {
        return directions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Nullable
    @Override
    public String getEmergencyPhoneNumber() {
        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeMon() {
        if (closeTimeMon != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeMon / 100, closeTimeMon % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeTue() {
        if (closeTimeTue != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeTue / 100, closeTimeTue % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeWed() {
        if (closeTimeWed != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeWed / 100, closeTimeWed % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeThu() {
        if (closeTimeThu != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeThu / 100, closeTimeThu % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeFri() {
        if (closeTimeFri != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeFri / 100, closeTimeFri % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeSat() {
        if (closeTimeSat != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeSat / 100, closeTimeSat % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeSun() {
        if (closeTimeSun != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeSun / 100, closeTimeSun % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getCloseTimeHoliday() {
        if (closeTimeHoliday != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(closeTimeHoliday / 100, closeTimeHoliday % 100);
        }

        return null;
    }
    @Nullable
    @Override
    public LocalTime getOpenTimeMon() {
        if (openTimeMon != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeMon / 100, openTimeMon % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeTue() {
        if (openTimeTue != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeTue / 100, openTimeTue % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeWed() {
        if (openTimeWed != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeWed / 100, openTimeWed % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeThu() {
        if (openTimeThu != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeThu / 100, openTimeThu % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeFri() {
        if (openTimeFri != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeFri / 100, openTimeFri % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeSat() {
        if (openTimeSat != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeSat / 100, openTimeSat % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeSun() {
        if (openTimeSun != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeSun / 100, openTimeSun % 100);
        }

        return null;
    }

    @Nullable
    @Override
    public LocalTime getOpenTimeHoliday() {
        if (openTimeHoliday != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalTime.of(openTimeHoliday / 100, openTimeHoliday % 100);
        }

        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }
}
