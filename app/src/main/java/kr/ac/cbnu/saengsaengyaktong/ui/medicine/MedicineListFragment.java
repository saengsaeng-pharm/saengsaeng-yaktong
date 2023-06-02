package kr.ac.cbnu.saengsaengyaktong.ui.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.DrugInfoService;
import kr.ac.cbnu.saengsaengyaktong.api.DrugItem;
import kr.ac.cbnu.saengsaengyaktong.api.DrugListResponse;
import kr.ac.cbnu.saengsaengyaktong.api.PublicDataPortalService;
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

        final View view = binding.getRoot();

        medicineListAdapter = new MedicineListAdapter();
        binding.medicineListRecyclerView.setAdapter(medicineListAdapter);

        suggestionsAdapter = new SearchSuggestionsAdapter();
        binding.recyclerSearchSuggestions.setAdapter(suggestionsAdapter);

        final ExtendedFloatingActionButton addButton = view.findViewById(R.id.fab_add);
        addButton.setOnClickListener(this::onAddClick);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onSearchChange(String query) {
        query = query.trim();
        binding.openSearchBar.setText(query);

        if (query.isEmpty()) {
            suggestionsAdapter.submitList(new ArrayList<>());
            return;
        }

        final DrugInfoService service = PublicDataPortalService.getInstance().getDrugInfoService();
        final Call<DrugListResponse> call = service.getDrugList(query);

        call.enqueue(new Callback<DrugListResponse>() {
            @Override
            public void onResponse(@NonNull Call<DrugListResponse> call, @NonNull Response<DrugListResponse> response) {
                final DrugListResponse.Body body = response.body().getBody();
                if (body.getTotalCount() == 0) return;

                final List<DrugItem> items = body.getItems();
                suggestionsAdapter.submitList(items);
            }

            @Override
            public void onFailure(@NonNull Call<DrugListResponse> call, @NonNull Throwable t) {
            }
        });
    }

    private void onAddClick(View v) {
        final Intent intent = new Intent(getActivity(), RecognitionActivity.class);
        startActivity(intent);
    }
/*
    @BindingAdapter("items")
    public static void setItems(@NonNull RecyclerView recyclerView, @NonNull MutableLiveData<List<Message>> listItemViewModels) {
        final MedicineListAdapter adapter = (MedicineListAdapter) recyclerView.getAdapter();
        assert adapter != null;
        adapter.setItems(listItemViewModels.getValue());
    }*/
}