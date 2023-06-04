package kr.ac.cbnu.saengsaengyaktong.api.public_data_portal;

import java.time.LocalTime;

import javax.annotation.Nullable;

public interface InstituteInfo {
    String getAddress();

    String getDivision();

    String getDivisionName();

    String getEmergencyClass();

    String getEmergencyClassName();

    Boolean hasEmergencyRoom();

    @Nullable
    String getEtc();

    @Nullable
    String getInformation();

    @Nullable
    String getDirections();

    String getName();

    String getPhoneNumber();

    @Nullable
    String getEmergencyPhoneNumber();

    @Nullable
    LocalTime getCloseTimeMon();

    @Nullable
    LocalTime getCloseTimeTue();

    @Nullable
    LocalTime getCloseTimeWed();

    @Nullable
    LocalTime getCloseTimeThu();

    @Nullable
    LocalTime getCloseTimeFri();

    @Nullable
    LocalTime getCloseTimeSat();

    @Nullable
    LocalTime getCloseTimeSun();

    @Nullable
    LocalTime getCloseTimeHoliday();

    @Nullable
    LocalTime getOpenTimeMon();

    @Nullable
    LocalTime getOpenTimeTue();

    @Nullable
    LocalTime getOpenTimeWed();

    @Nullable
    LocalTime getOpenTimeThu();

    @Nullable
    LocalTime getOpenTimeFri();

    @Nullable
    LocalTime getOpenTimeSat();

    @Nullable
    LocalTime getOpenTimeSun();

    @Nullable
    LocalTime getOpenTimeHoliday();

    String getId();

    double getLongitude();

    double getLatitude();
}
