package kr.ac.cbnu.saengsaengyaktong.ui.schedules;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.ProductInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityDrugScheduleBinding;

public class DrugScheduleActivity extends AppCompatActivity {
    private final static String TAG = "DrugScheduleActivity";

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ActivityDrugScheduleBinding binding;
    private DrugScheduleViewModel viewModel;

    private ProductInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.Factory.from(DrugScheduleViewModel.initializer)).get(DrugScheduleViewModel.class);

        binding = ActivityDrugScheduleBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.switchBreakfast.setOnClickListener(this::onSwitchChecked);
        binding.switchLunch.setOnClickListener(this::onSwitchChecked);
        binding.switchDinner.setOnClickListener(this::onSwitchChecked);

        binding.setViewModel(viewModel);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("a h:mm");
            binding.setTimeFormat(timeFormat);
        }

        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarDrugSchedule);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("복약 등록");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        info = getIntent().getParcelableExtra("info");

        viewModel.setProductType(info.getType());
        viewModel.setProductId(info.getId());
        viewModel.setName(info.getName());
        viewModel.setImageUrl(info.getImageUrl());

        binding.toggleDays.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                final DayOfWeek day;

                if (checkedId == R.id.button_sunday) {
                    day = DayOfWeek.SUNDAY;
                } else if (checkedId == R.id.button_monday) {
                    day = DayOfWeek.MONDAY;
                } else if (checkedId == R.id.button_tuesday) {
                    day = DayOfWeek.TUESDAY;
                } else if (checkedId == R.id.button_wednesday) {
                    day = DayOfWeek.WEDNESDAY;
                } else if (checkedId == R.id.button_thursday) {
                    day = DayOfWeek.THURSDAY;
                } else if (checkedId == R.id.button_friday) {
                    day = DayOfWeek.FRIDAY;
                } else if (checkedId == R.id.button_saturday) {
                    day = DayOfWeek.SATURDAY;
                } else {
                    return;
                }

                final Set<DayOfWeek> days = viewModel.getDayOfWeeks().getValue();

                if (isChecked) {
                    days.add(day);
                } else {
                    days.remove(day);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        } else if (id == R.id.item_confirm) {
            compositeDisposable.add(viewModel.register().subscribe(() -> {
                setResult(RESULT_OK);
                finish();
            }));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void onSwitchChecked(View view) {
        Log.i(TAG, "switch: " + view);

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) return;
        if (!((MaterialSwitch) view).isChecked()) return;

        LocalTime oldTime;

        if (view.getId() == R.id.switch_breakfast) {
            oldTime = viewModel.getBreakfastTime().getValue();
        } else if (view.getId() == R.id.switch_lunch) {
            oldTime = viewModel.getLunchTime().getValue();
        } else if (view.getId() == R.id.switch_dinner) {
            oldTime = viewModel.getDinnerTime().getValue();
        } else {
            return;
        }

        final MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(oldTime.getHour())
                .setMinute(oldTime.getMinute())
                .setTitleText("복약 시각 설정")
                .build();

        timePicker.addOnPositiveButtonClickListener(v -> {
            final LocalTime time = LocalTime.of(timePicker.getHour(), timePicker.getMinute());

            if (view.getId() == R.id.switch_breakfast) {
                viewModel.setBreakfastTime(time);
            } else if (view.getId() == R.id.switch_lunch) {
                viewModel.setLunchTime(time);
            } else if (view.getId() == R.id.switch_dinner) {
                viewModel.setDinnerTime(time);
            }
        });

        timePicker.show(getSupportFragmentManager(), TAG);
    }

}
