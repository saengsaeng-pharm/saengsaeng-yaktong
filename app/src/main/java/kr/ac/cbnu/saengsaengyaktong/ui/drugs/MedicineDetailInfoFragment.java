package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugPermitInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugPermitInfoResponse;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugPermitInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PublicDataPortalService;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentMedicineDetailInfoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineDetailInfoFragment extends Fragment {
    private final DrugPermitInfoServiceWrapper permitInfoService = PublicDataPortalService.getInstance().getDrugPermitInfoService();

    private FragmentMedicineDetailInfoBinding binding;

    private final String id;
    private DrugPermitInfo info;

    public MedicineDetailInfoFragment(String id) {
        this.id = id;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedicineDetailInfoBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        permitInfoService.getDrugPermitInfo(id).enqueue(new Callback<DrugPermitInfoResponse>() {
            @Override
            public void onResponse(Call<DrugPermitInfoResponse> call, Response<DrugPermitInfoResponse> response) {
                System.out.println(call.request().url());

                final DrugPermitInfoResponse.Body body = response.body().getBody();
                if (body.getTotalCount() == 0) return;

                info = body.getItems().get(0);
                binding.setInfo(info);
            }

            @Override
            public void onFailure(Call<DrugPermitInfoResponse> call, Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });

        return binding.getRoot();
    }
}