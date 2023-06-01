package kr.ac.cbnu.saengsaengyaktong.ui;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicinePlan;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.MedicineRecord;

public class MedicineViewModel {
    private final MedicinePlan plan;
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
        return record.getBreakfast() != null;
    }

    public boolean getLunch() {
        return record.getLunch() != null;
    }

    public boolean getDinner() {
        return record.getDinner() != null;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .into(view);
    }
}
