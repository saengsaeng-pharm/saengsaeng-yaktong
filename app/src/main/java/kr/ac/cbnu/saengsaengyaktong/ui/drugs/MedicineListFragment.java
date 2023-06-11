package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import kr.ac.cbnu.saengsaengyaktong.ui.detection.DrugDetectionActivity;
import kr.ac.cbnu.saengsaengyaktong.ui.foods.FoodInfoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineListFragment extends Fragment {
    private final DrugInfoServiceWrapper service = PublicDataPortalService.getInstance().getDrugInfoService();

    private FragmentMedicineListBinding binding;

    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int REQUEST_SPEECH = 1;

    private String currentPhotoPath;

    private MedicineListAdapter medicineListAdapter;
    private SearchSuggestionsAdapter suggestionsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedicineListBinding.inflate(getLayoutInflater());

        binding.openSearchBar.inflateMenu(R.menu.speech_menu);
        binding.openSearchBar.setOnMenuItemClickListener(this::onMenuItemClick);

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
        suggestionsAdapter.setOnClickListener(this::onItemClick);

        binding.recyclerSearchSuggestions.setAdapter(suggestionsAdapter);

        binding.fabAdd.setOnClickListener(this::onAddClick);

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

    private void onItemClick(SearchSuggestion info) {
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
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.e(getTag(), e.getMessage(), e);
            }

            if (photoFile != null) {
                final Uri photoURI = FileProvider.getUriForFile(getContext(), "kr.ac.cbnu.saengsaengyaktong.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) return;

        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                final Intent intent = new Intent(getActivity(), DrugDetectionActivity.class);
                intent.putExtra("photo_path", currentPhotoPath);

                startActivity(intent);
                break;
            case REQUEST_SPEECH:
                final List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                final String result = results.get(0);

                binding.openSearchView.setText(result);
                binding.openSearchBar.performClick();
                break;
        }
    }

    private boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        Log.i("list", "menu click: " + id);

        if (id == R.id.item_speech) {
            onSpeechMenuClick();
            return true;
        }

        return false;
    }

    private void onSpeechMenuClick() {
        System.out.println("onSpeech");

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        startActivityForResult(intent, REQUEST_SPEECH);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile("JPEG_" + timeStamp + "_", ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }
}