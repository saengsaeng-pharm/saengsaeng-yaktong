package kr.ac.cbnu.saengsaengyaktong.ui.medicine;

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
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityMedicineInfoBinding;

public class MedicineInfoActivity extends AppCompatActivity {
    private ActivityMedicineInfoBinding binding;

    private MedicineBriefInfoFragment briefInfoFragment;
    private MedicineDetailInfoFragment detailInfoFragment;

    private ViewPagerAdapter adapter;
    private DrugInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        info = intent.getParcelableExtra("drugInfo");

        briefInfoFragment = new MedicineBriefInfoFragment(info);
        detailInfoFragment = new MedicineDetailInfoFragment(info.getId());

        binding = ActivityMedicineInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        binding.appbarMedicineInfo.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float range = (float) -appBarLayout.getTotalScrollRange();
            binding.imageMedicine.setAlpha(1 - (verticalOffset / range));
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_medicine) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MedicineBriefInfoFragment(info);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }*/

    private class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(MedicineInfoActivity activity) {
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
