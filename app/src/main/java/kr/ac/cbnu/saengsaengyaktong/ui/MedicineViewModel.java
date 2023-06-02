package kr.ac.cbnu.saengsaengyaktong.ui;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import javax.annotation.Nullable;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicinePlan;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicineRecord;
import kr.ac.cbnu.saengsaengyaktong.domain.repository.MedicineRecordsRepository;

public class MedicineViewModel {

    private static final MedicineRecordsRepository repository = MedicineRecordsRepository.getInstance();

    private final MedicinePlan plan;

    @Nullable
    private final MedicineRecord record;

    public MedicineViewModel(MedicinePlan plan, MedicineRecord record) {
        this.plan = plan;
        this.record = record;
    }

    public String getId() {
        return plan.getId();
    }

    public String getName() {
        return plan.getMedicineName();
    }

    public String getMemo() {
        return plan.getMemo();
    }

    public String getImageUrl() {
        return plan.getImageUrl();
    }

    public boolean getBreakfast() {
        if (record == null) return false;
        return record.getBreakfast() != null;
    }

    public boolean getLunch() {
        if (record == null) return false;
        return record.getLunch() != null;
    }

    public boolean getDinner() {
        if (record == null) return false;
        return record.getDinner() != null;
    }
}
