package kr.ac.cbnu.saengsaengyaktong.ui.schedules;

import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.google.firebase.auth.FirebaseAuth;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.rxjava3.core.Completable;
import kr.ac.cbnu.saengsaengyaktong.api.ProductType;
import kr.ac.cbnu.saengsaengyaktong.domain.entity.DrugSchedule;
import kr.ac.cbnu.saengsaengyaktong.domain.repository.DrugSchedulesRepository;

public class DrugScheduleViewModel extends ViewModel {
    private final DrugSchedulesRepository repository = DrugSchedulesRepository.getInstance();

    private static final String PRODUCT_TYPE_KEY = "type";
    private static final String PRODUCT_ID_KEY = "product_id";
    private static final String NAME_KEY = "name";
    private static final String IMAGE_URL_KEY = "image_url";
    private static final String MEMO_KEY = "memo";
    private static final String BREAKFAST_ENABLED_KEY = "breakfast_enabled";
    private static final String BREAKFAST_TIME_KEY = "breakfast_time";
    private static final String LUNCH_ENABLED_KEY = "lunch_enabled";
    private static final String LUNCH_TIME_KEY = "lunch_time";
    private static final String DINNER_ENABLED_KEY = "dinner_enabled";
    private static final String DINNER_TIME_KEY = "dinner_time";
    private static final String DAY_OF_WEEKS_KEY = "days";

    private final SavedStateHandle handle;

    public DrugScheduleViewModel(SavedStateHandle state) {
        this.handle = state;

        setBreakfastEnabled(true);
        setLunchEnabled(true);
        setDinnerEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setBreakfastTime(LocalTime.of(8, 0));
            setLunchTime(LocalTime.of(13, 0));
            setDinnerTime(LocalTime.of(19, 0));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            final Set<DayOfWeek> dayOfWeeks = new HashSet<>(EnumSet.allOf(DayOfWeek.class));
            setDayOfWeeks(dayOfWeeks);
        }
    }

    public MutableLiveData<ProductType> getProductType() {
        return handle.getLiveData(PRODUCT_TYPE_KEY);
    }

    public MutableLiveData<String> getProductId() {
        return handle.getLiveData(PRODUCT_ID_KEY);
    }

    public MutableLiveData<String> getName() {
        return handle.getLiveData(NAME_KEY);
    }

    public LiveData<String> getImageUrl() {
        return handle.getLiveData(IMAGE_URL_KEY);
    }

    public MutableLiveData<String> getMemo() {
        return handle.getLiveData(MEMO_KEY);
    }

    public MutableLiveData<Boolean> getBreakfastEnabled() {
        return handle.getLiveData(BREAKFAST_ENABLED_KEY);
    }

    public LiveData<LocalTime> getBreakfastTime() {
        return handle.getLiveData(BREAKFAST_TIME_KEY);
    }

    public MutableLiveData<Boolean> getLunchEnabled() {
        return handle.getLiveData(LUNCH_ENABLED_KEY);
    }

    public LiveData<LocalTime> getLunchTime() {
        return handle.getLiveData(LUNCH_TIME_KEY);
    }

    public MutableLiveData<Boolean> getDinnerEnabled() {
        return handle.getLiveData(DINNER_ENABLED_KEY);
    }

    public LiveData<LocalTime> getDinnerTime() {
        return handle.getLiveData(DINNER_TIME_KEY);
    }

    public LiveData<Set<DayOfWeek>> getDayOfWeeks() {
        return handle.getLiveData(DAY_OF_WEEKS_KEY);
    }

    public void setProductType(ProductType value) {
        handle.set(PRODUCT_TYPE_KEY, value);
    }

    public void setProductId(String value) {
        handle.set(PRODUCT_ID_KEY, value);
    }

    public void setName(String value) {
        handle.set(NAME_KEY, value);
    }

    public void setImageUrl(String value) {
        handle.set(IMAGE_URL_KEY, value);
    }

    public void setMemo(String value) {
        handle.set(MEMO_KEY, value);
    }

    public void setBreakfastEnabled(boolean value) {
        handle.set(BREAKFAST_ENABLED_KEY, value);
    }

    public void setBreakfastTime(LocalTime value) {
        handle.set(BREAKFAST_TIME_KEY, value);
    }

    public void setLunchEnabled(boolean value) {
        handle.set(LUNCH_ENABLED_KEY, value);
    }

    public void setLunchTime(LocalTime value) {
        handle.set(LUNCH_TIME_KEY, value);
    }

    public void setDinnerEnabled(boolean value) {
        handle.set(DINNER_ENABLED_KEY, value);
    }

    public void setDinnerTime(LocalTime value) {
        handle.set(DINNER_TIME_KEY, value);
    }

    public void setDayOfWeeks(Set<DayOfWeek> value) {
        handle.set(DAY_OF_WEEKS_KEY, value);
    }

    public Completable register() {
        final String userId = FirebaseAuth.getInstance().getUid();

        final DrugSchedule schedule = new DrugSchedule(userId, getProductType().getValue(), getProductId().getValue());
        schedule.setName(getName().getValue());
        schedule.setMemo(getMemo().getValue());
        schedule.setImageUrl(getImageUrl().getValue());

        if (getBreakfastEnabled().getValue()) {
            schedule.setBreakfastTime(getBreakfastTime().getValue());
        } else {
            schedule.setBreakfastTime((Integer) null);
        }

        if (getLunchEnabled().getValue()) {
            schedule.setLunchTime(getLunchTime().getValue());
        } else {
            schedule.setLunchTime((Integer) null);
        }

        if (getDinnerEnabled().getValue()) {
            schedule.setDinnerTime(getDinnerTime().getValue());
        } else {
            schedule.setDinnerTime((Integer) null);
        }

        return repository.set(schedule);
    }

    public static final ViewModelInitializer<DrugScheduleViewModel> initializer = new ViewModelInitializer<>(
            DrugScheduleViewModel.class,
            creationExtras -> {
                Application app = creationExtras.get(APPLICATION_KEY);
                assert app != null;
                SavedStateHandle savedStateHandle = createSavedStateHandle(creationExtras);

                return new DrugScheduleViewModel(savedStateHandle);
            }
    );
}