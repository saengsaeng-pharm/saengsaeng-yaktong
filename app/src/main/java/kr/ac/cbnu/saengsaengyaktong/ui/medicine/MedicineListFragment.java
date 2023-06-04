package kr.ac.cbnu.saengsaengyaktong.ui.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodInfo;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodsInfoResponse;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.FoodsInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.health_supplements.HealthSupplementsService;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugListResponse;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PublicDataPortalService;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentMedicineListBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.RecognitionActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineListFragment extends Fragment {
    private FragmentMedicineListBinding binding;

    private MedicineListAdapter medicineListAdapter;
    private SearchSuggestionsAdapter suggestionsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedicineListBinding.inflate(getLayoutInflater());

        binding.openSearchBar.inflateMenu(R.menu.scan_menu);
        binding.openSearchView.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                onSearchChange(editable.toString());
            }
        });

        binding.chipMedicine.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                performMedicineSearch();
            }
        });

        binding.chipHealthFood.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                performHealthFoodSearch();
            }
        });

        final View view = binding.getRoot();

        medicineListAdapter = new MedicineListAdapter();
        binding.medicineListRecyclerView.setAdapter(medicineListAdapter);

        suggestionsAdapter = new SearchSuggestionsAdapter();
        suggestionsAdapter.setOnClickListener(this::onItemSelect);

        binding.recyclerSearchSuggestions.setAdapter(suggestionsAdapter);

        final ExtendedFloatingActionButton addButton = view.findViewById(R.id.fab_add);
        addButton.setOnClickListener(this::onAddClick);

        return view;
    }

    public void onSearchChange(String query) {
        query = query.trim();
        binding.openSearchBar.setText(query);

        System.out.println("Medicine search: " + query);

        if (query.isEmpty()) {
            suggestionsAdapter.submitList(new ArrayList<>());
            return;
        }

        if (binding.chipMedicine.isChecked()) {
            performMedicineSearch();
        } else {
            performHealthFoodSearch();
        }
    }

    private void performMedicineSearch() {
        final String query = binding.openSearchView.getEditText().getText().toString().trim();
        System.out.println("performMedicineSearch: " + query);

        final DrugInfoServiceWrapper service = PublicDataPortalService.getInstance().getDrugInfoService();
        final Call<DrugListResponse> call = service.getDrugList(query);

        System.out.println(call.request().url());

        call.enqueue(new Callback<DrugListResponse>() {
            @Override
            public void onResponse(@NonNull Call<DrugListResponse> call, @NonNull Response<DrugListResponse> response) {
                System.out.println(call.request().url());

                final DrugListResponse.Body body = response.body().getBody();
                if (body.getTotalCount() == 0) return;

                final List<DrugInfo> items = body.getItems();
                suggestionsAdapter.setItems(items);
            }

            @Override
            public void onFailure(@NonNull Call<DrugListResponse> call, @NonNull Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });
    }

    private void performHealthFoodSearch() {
        final String query = binding.openSearchView.getEditText().getText().toString().trim();
        System.out.println("performHealthFoodSearch: " + query);

        final FoodsInfoServiceWrapper service = HealthSupplementsService.getInstance().getFoodsInfoService();
        final Call<FoodsInfoResponse> call = service.getFoodsInfo(query);

        System.out.println(call.request().url());

        call.enqueue(new Callback<FoodsInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodsInfoResponse> call, @NonNull Response<FoodsInfoResponse> response) {
                System.out.println(call.request().url());

                final List<FoodInfo> items = response.body().getItems();
                suggestionsAdapter.setItems(items);
            }

            @Override
            public void onFailure(@NonNull Call<FoodsInfoResponse> call, @NonNull Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });
    }

    private void onItemSelect(SearchSuggestion info) {
        final Intent intent;

        if (info instanceof DrugInfo) {
            intent = new Intent(getActivity(), MedicineInfoActivity.class);
            intent.putExtra("drugInfo", (DrugInfo) info);
        } else {
            intent = new Intent(getActivity(), FoodInfoActivity.class);
            intent.putExtra("foodInfo", (FoodInfo) info);
        }

        startActivity(intent);
    }

    private void onAddClick(View v) {
        final Intent intent = new Intent(getActivity(), RecognitionActivity.class);
        startActivity(intent);
    }
}