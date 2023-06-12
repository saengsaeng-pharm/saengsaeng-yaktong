package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityDrugInfoBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.schedules.DrugScheduleActivity;

public class DrugInfoActivity extends AppCompatActivity {
    private static int REQUEST_ADD = 0;

    private ActivityDrugInfoBinding binding;

    private DrugOverviewFragment briefInfoFragment;
    private DrugDetailsFragment detailInfoFragment;

    private ViewPagerAdapter adapter;
    private DrugInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        info = getIntent().getParcelableExtra("drugInfo");

        briefInfoFragment = new DrugOverviewFragment(info);
        detailInfoFragment = new DrugDetailsFragment(info.getId());

        binding = ActivityDrugInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.appbarMedicineInfo.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float range = (float) -appBarLayout.getTotalScrollRange();
            binding.imageMedicine.setAlpha(1 - (verticalOffset / range));
        });

        binding.fabAddDrug.setOnClickListener((v) -> onAddClick());

        adapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayoutMedicineInfo, binding.viewPager,
                (tab, position) -> {
                    if (position == 0) tab.setText("요약 정보");
                    else tab.setText("상세 정보");
                }
        ).attach();

        binding.setInfo(info);
        setContentView(binding.getRoot());

        final Toolbar toolbar = findViewById(R.id.toolbar_medicine_info);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(info.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            finish();
        }
    }

    private void onAddClick() {
        final Intent intent = new Intent(this, DrugScheduleActivity.class);
        intent.putExtra("info", info);

        startActivityForResult(intent, REQUEST_ADD);
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(DrugInfoActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) return briefInfoFragment;
            return detailInfoFragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
