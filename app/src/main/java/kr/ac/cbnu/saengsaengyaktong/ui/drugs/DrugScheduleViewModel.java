package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public class DrugScheduleViewModel extends ViewModel {
    private static final String IMAGE_URL_KEY = "image_url";
    private static final String COMPANY_NAME_KEY = "company_name";
    private static final String NAME_KEY = "name";
    private static final String MEMO_KEY = "memo";
    private static final String BREAKFAST_TIME_KEY = "breakfast_time";
    private static final String LUNCH_TIME_KEY = "lunch_time";
    private static final String DINNER_TIME_KEY = "dinner_time";
    private static final String DAY_OF_WEEKS_KEY = "days";

    private final SavedStateHandle handle;

    public DrugScheduleViewModel(SavedStateHandle state) {
        this.handle = state;
    }

    public LiveData<String> getImageUrl() {
        return handle.getLiveData(IMAGE_URL_KEY);
    }

    public LiveData<String> getCompanyName() {
        return handle.getLiveData(COMPANY_NAME_KEY);
    }

    public LiveData<String> getName() {
        return handle.getLiveData(NAME_KEY);
    }

    public LiveData<String> getMemo() {
        return handle.getLiveData(MEMO_KEY);
    }

    public LiveData<LocalTime> getBreakfastTime() {
        return handle.getLiveData(BREAKFAST_TIME_KEY);
    }

    public LiveData<LocalTime> getLunchTime() {
        return handle.getLiveData(LUNCH_TIME_KEY);
    }

    public LiveData<LocalTime> getDinnerTime() {
        return handle.getLiveData(DINNER_TIME_KEY);
    }

    public LiveData<Set<DayOfWeek>> getDayOfWeeks() {
        return handle.getLiveData(DAY_OF_WEEKS_KEY);
    }

    public void setName(String value) {
        handle.set(NAME_KEY, value);
    }

    public void setMemo(String value) {
        handle.set(MEMO_KEY, value);
    }

    public void setBreakfastTime(LocalTime value) {
        handle.set(BREAKFAST_TIME_KEY, value);
    }

    public void setLunchTime(LocalTime value) {
        handle.set(LUNCH_TIME_KEY, value);
    }

    public void setDinnerTime(LocalTime value) {
        handle.set(DINNER_TIME_KEY, value);
    }

    public void setDayOfWeeks(Set<DayOfWeek> value) {
        handle.set(DAY_OF_WEEKS_KEY, value);
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