package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import java.util.Date;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.DrugSchedule;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.DailyDrugRecord;
import kr.ac.cbnu.saengsaengyaktong.domain.repository.DailyDrugRecordsRepository;
import kr.ac.cbnu.saengsaengyaktong.utils.DateUtils;

public class DailyDrugRecordViewModel {

    private static final DailyDrugRecordsRepository repository = DailyDrugRecordsRepository.getInstance();

    private final DrugSchedule schedule;
    @Nullable
    private DailyDrugRecord record;

    public DailyDrugRecordViewModel(DrugSchedule plan, @Nullable DailyDrugRecord record) {
        this.schedule = plan;
        this.record = record;
    }

    public boolean isBreakfastEnabled() {
        return schedule.getBreakfastTime() != null;
    }

    public boolean isLunchEnabled() {
        return schedule.getLunchTime() != null;
    }

    public boolean isDinnerEnabled() {
        return schedule.getDinnerTime() != null;
    }

    public String getId() {
        return schedule.getId();
    }

    public String getName() {
        return schedule.getName();
    }

    public String getMemo() {
        return schedule.getMemo();
    }

    public String getImageUrl() {
        return schedule.getImageUrl();
    }

    public Boolean getBreakfast() {
        if (!isBreakfastEnabled()) return null;

        if (record == null) return false;
        return record.getBreakfast() != null;
    }

    public Boolean getLunch() {
        if (!isLunchEnabled()) return null;

        if (record == null) return false;
        return record.getLunch() != null;
    }

    public Boolean getDinner() {
        if (!isDinnerEnabled()) return null;

        if (record == null) return false;
        return record.getDinner() != null;
    }

    public void setBreakfast(boolean value) {
        if (!isBreakfastEnabled()) return;

        if (record == null) {
            record = new DailyDrugRecord(schedule.getUserId(), schedule.getDrugType(), schedule.getDrugId(), DateUtils.getTodayDate());
        }

        if (value) record.setBreakfast(new Date());
        else record.setBreakfast(null);

        repository.set(record);
    }

    public void setLunch(boolean value) {
        if (!isLunchEnabled()) return;

        if (record == null) {
            record = new DailyDrugRecord(schedule.getUserId(), schedule.getDrugType(), schedule.getDrugId(), DateUtils.getTodayDate());
        }

        if (value) record.setLunch(new Date());
        else record.setLunch(null);

        repository.set(record);
    }

    public void setDinner(boolean value) {
        if (!isDinnerEnabled()) return;

        if (record == null) {
            record = new DailyDrugRecord(schedule.getUserId(), schedule.getDrugType(), schedule.getDrugId(), DateUtils.getTodayDate());
        }

        if (value) record.setDinner(new Date());
        else record.setDinner(null);

        repository.set(record);
    }
}
