package kr.ac.cbnu.saengsaengyaktong.ui.nearby;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skt.tmap.TMapTapi;

import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.InstituteInfo;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentInstituteInfoBottomSheetBinding;

public class InstituteInfoBottomSheetFragment extends Fragment {
    private FragmentInstituteInfoBottomSheetBinding binding;

    private InstituteInfo info;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInstituteInfoBottomSheetBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        binding.buttonCall.setOnClickListener((v) -> onCallClick());
        binding.buttonDirectionsCar.setOnClickListener((v) -> onDirectionsClick("car"));
        binding.buttonNavigation.setOnClickListener((v) -> onNavigationClick());
        binding.buttonDirectionsBus.setOnClickListener((v) -> onDirectionsClick("public"));
        binding.buttonDirectionsBike.setOnClickListener((v) -> onDirectionsClick("bicycle"));
        binding.buttonDirectionsWalk.setOnClickListener((v) -> onDirectionsClick("walk"));

        return binding.getRoot();
    }

    private void onCallClick() {
        if (info == null) return;

        final Uri uri = Uri.parse("tel:" + info.getPhoneNumber());
        final Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    private void onDirectionsClick(String method) {
        if (info == null) return;

        final Uri.Builder builder = new Uri.Builder();
        builder.scheme("nmap")
                .authority("route")
                .appendPath(method)
                .appendQueryParameter("dlat", String.valueOf(info.getLatitude()))
                .appendQueryParameter("dlng", String.valueOf(info.getLongitude()))
                .appendQueryParameter("dname", info.getName())
                .appendQueryParameter("appname", getActivity().getPackageName());

        final Intent intent = new Intent(Intent.ACTION_VIEW, builder.build());
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        startActivity(intent);
    }

    private void onNavigationClick() {
        if (info == null) return;

        final TMapTapi tMapApi = new TMapTapi(getActivity());
        tMapApi.setSKTmapAuthentication("l7xx292c0b05f0d441a1821282cb08660d40");
        tMapApi.invokeRoute(info.getName(), (float) info.getLongitude(), (float) info.getLatitude());
    }

    public void setInfo(InstituteInfo info) {
        binding.setInfo(info);
        this.info = info;
    }
}