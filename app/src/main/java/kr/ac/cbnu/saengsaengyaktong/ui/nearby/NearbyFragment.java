package kr.ac.cbnu.saengsaengyaktong.ui.nearby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kr.ac.cbnu.saengsaengyaktong.R;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.HospitalInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.HospitalInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.HospitalListResponse;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.InstituteInfo;
import kr.ac.cbnu.saengsaengyaktong.api.naver.NaverCloudService;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PharmacyInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PharmacyInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PharmacyListResponse;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PublicDataPortalService;
import kr.ac.cbnu.saengsaengyaktong.api.naver.ReverseGeocodeResponse;
import kr.ac.cbnu.saengsaengyaktong.api.naver.ReverseGeocodeServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentNearbyBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyFragment extends Fragment implements OnMapReadyCallback {
    private static final LatLng DEFAULT_CENTER = new LatLng(36.62957081433693, 127.45760331653162);
    private static final int DEFAULT_ZOOM = 12;

    private static final int MARKER_WIDTH = 75;
    private static final int MARKER_HEIGHT = 107;
    private static final int BOTTOM_SHEET_PEEK_HEIGHT = 450;

    private final ReverseGeocodeServiceWrapper reverseGeocodingService = NaverCloudService.getInstance().getReverseGeocodingService();
    private final HospitalInfoServiceWrapper hospitalInfoService = PublicDataPortalService.getInstance().getHospitalInfoService();
    private final PharmacyInfoServiceWrapper pharmacyInfoService = PublicDataPortalService.getInstance().getPharmacyInfoService();

    private FusedLocationSource locationSource;
    private FragmentNearbyBinding binding;
    private NaverMap map;

    @Nullable
    private InstituteInfoBottomSheetFragment bottomSheetFragment;

    @Nullable
    private LatLng lastCenter;
    @Nullable
    private String lastProvince, lastCity;
    @Nullable
    private Marker selectedMarker;

    private final Set<String> loadedHospitals = new HashSet<>();
    private final Set<String> loadedPharmacies = new HashSet<>();

    private final Map<Marker, HospitalInfo> hospitalMarkers = new HashMap<>();
    private final Map<Marker, PharmacyInfo> pharmacyMarkers = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        locationSource = new FusedLocationSource(this, 1000);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        binding = FragmentNearbyBinding.inflate(inflater, container, false);

        binding.chipHospital.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) loadHospitals(lastProvince, lastCity);
            else unloadHospitals();
        });

        binding.chipPharmacy.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) loadPharmacies(lastProvince, lastCity);
            else unloadPharmacies();
        });

        return binding.getRoot();
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap map) {
        this.map = map;

        final CameraPosition cameraPosition = new CameraPosition(DEFAULT_CENTER, DEFAULT_ZOOM);
        map.setCameraPosition(cameraPosition);
        map.setLocationSource(locationSource);

        final UiSettings settings = map.getUiSettings();
        settings.setCompassEnabled(true);
        settings.setLocationButtonEnabled(true);
        settings.setScaleBarEnabled(true);

        map.addOnCameraChangeListener((i, b) -> onCameraChange());
        map.setOnMapClickListener((p, position) -> onMapClick(position));
    }

    private void onCameraChange() {
        if (selectedMarker != null && !map.getContentBounds().contains(selectedMarker.getPosition())) {
            removeSelections();
        }

        final LatLng center = map.getCameraPosition().target;

        if (lastCenter != null && center.distanceTo(lastCenter) < 10) {
            lastCenter = center;
            return;
        }

        reverseGeocodingService.reverseGeocode(center.latitude, center.longitude).enqueue(new Callback<ReverseGeocodeResponse>() {
            @Override
            public void onResponse(Call<ReverseGeocodeResponse> call, Response<ReverseGeocodeResponse> response) {
                System.out.println(call.request().url());

                if (!response.isSuccessful()) return;

                final List<ReverseGeocodeResponse.Result> results = response.body().getResults();
                if (results.size() == 0) return;

                final ReverseGeocodeResponse.Result.Region region = results.get(0).getRegion();
                final String country = region.getArea0().getName();
                if (!country.equals("kr")) return;

                final String province = region.getArea1().getName();
                String city = region.getArea2().getName();
                if (city.contains(" ")) city = city.substring(0, city.indexOf(" "));

                if (province.equals(lastProvince) && city.equals(lastCity)) return;

                loadHospitals(province, city);
                loadPharmacies(province, city);

                lastProvince = province;
                lastCity = city;
            }

            @Override
            public void onFailure(Call<ReverseGeocodeResponse> call, Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });

        lastCenter = center;
    }

    private void onMapClick(LatLng position) {
    }

    private void loadHospitals(String province, String city) {
        hospitalInfoService.getHospitalList(province, city, 25).enqueue(new Callback<HospitalListResponse>() {
            @Override
            public void onResponse(Call<HospitalListResponse> call, Response<HospitalListResponse> response) {
                System.out.println(call.request().url());

                if (!response.isSuccessful()) return;

                final List<HospitalInfo> results = response.body().getBody().getItems();
                if (results == null) return;

                for (final HospitalInfo info : results) {
                    final String id = info.getId();
                    if (loadedHospitals.contains(id)) continue;

                    final LatLng coord = new LatLng(info.getLatitude(), info.getLongitude());
                    final OverlayImage icon = OverlayImage.fromResource(R.drawable.hospital_marker);
                    final String captionText = info.getName();

                    final Marker marker = createMarker(coord, icon, captionText);
                    marker.setOnClickListener(overlay -> {
                        System.out.println("hosp click: " + info.getName());
                        onHospitalMarkerClick((Marker) overlay);
                        return false;
                    });

                    marker.setMap(map);
                    loadedHospitals.add(id);
                    hospitalMarkers.put(marker, info);
                }
            }

            @Override
            public void onFailure(Call<HospitalListResponse> call, Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });
    }

    private void onInstituteSelect(InstituteInfo info) {
        if (bottomSheetFragment != null && bottomSheetFragment.getView() != null) {
            final BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetFragment.getView());
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bottomSheetBehavior.setPeekHeight(BOTTOM_SHEET_PEEK_HEIGHT);

            bottomSheetFragment.setInfo(info);
            return;
        }

        bottomSheetFragment = new InstituteInfoBottomSheetFragment();

        getParentFragmentManager()
                .beginTransaction()
                .add(R.id.layout_nearby, bottomSheetFragment)
                .runOnCommit(() -> {
                    final BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetFragment.getView());
                    bottomSheetBehavior.setPeekHeight(BOTTOM_SHEET_PEEK_HEIGHT);

                    bottomSheetFragment.setInfo(info);
                })
                .commit();
    }

    private void loadPharmacies(String province, String city) {
        pharmacyInfoService.getPharmacyList(province, city, 25).enqueue(new Callback<PharmacyListResponse>() {
            @Override
            public void onResponse(Call<PharmacyListResponse> call, Response<PharmacyListResponse> response) {
                System.out.println(call.request().url());

                if (!response.isSuccessful()) return;

                final List<PharmacyInfo> results = response.body().getBody().getItems();
                if (results == null) return;

                for (final PharmacyInfo info : results) {
                    final String id = info.getId();
                    if (loadedPharmacies.contains(id)) continue;

                    final LatLng coord = new LatLng(info.getLatitude(), info.getLongitude());
                    final OverlayImage icon = OverlayImage.fromResource(R.drawable.pharmacy_marker);
                    final String captionText = info.getName();

                    final Marker marker = createMarker(coord, icon, captionText);
                    marker.setOnClickListener(overlay -> {
                        System.out.println("pharm click: " + info.getName());
                        onPharmacyMarkerClick((Marker) overlay);
                        return false;
                    });

                    marker.setMap(map);
                    loadedPharmacies.add(id);
                    pharmacyMarkers.put(marker, info);
                }
            }

            @Override
            public void onFailure(Call<PharmacyListResponse> call, Throwable t) {
                System.out.println(call.request().url());
                System.out.println(t.getMessage());
            }
        });
    }

    private Marker createMarker(LatLng coord, OverlayImage icon, String captionText) {
        final Marker marker = new Marker(coord, icon);
        marker.setWidth(MARKER_WIDTH);
        marker.setHeight(MARKER_HEIGHT);
        marker.setCaptionText(captionText);
        marker.setHideCollidedMarkers(true);
        marker.setHideCollidedSymbols(true);

        return marker;
    }

    private void removeSelections() {
        if (bottomSheetFragment != null && bottomSheetFragment.getView() != null) {
            final BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetFragment.getView());
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }

        if (selectedMarker != null) {
            unhighlightMarker(selectedMarker);
            selectedMarker = null;
        }
    }

    private void highlightMarker(Marker marker) {
        if (selectedMarker != null) {
            unhighlightMarker(selectedMarker);
            selectedMarker = null;
        }

        marker.setWidth((int) Math.round(MARKER_WIDTH * 1.5));
        marker.setHeight((int) Math.round(MARKER_HEIGHT * 1.5));
    }

    private void unhighlightMarker(Marker marker) {
        marker.setWidth(MARKER_WIDTH);
        marker.setHeight(MARKER_HEIGHT);
    }

    private void unloadHospitals() {
        if (selectedMarker != null && hospitalMarkers.containsKey(selectedMarker)) {
            removeSelections();
        }

        for (final Marker marker : hospitalMarkers.keySet()) {
            marker.setMap(null);
        }

        loadedHospitals.clear();
        hospitalMarkers.clear();
    }

    private void unloadPharmacies() {
        if (selectedMarker != null && pharmacyMarkers.containsKey(selectedMarker)) {
            removeSelections();
        }

        for (final Marker marker : pharmacyMarkers.keySet()) {
            marker.setMap(null);
        }

        loadedPharmacies.clear();
        pharmacyMarkers.clear();
    }

    private void onHospitalMarkerClick(Marker marker) {
        highlightMarker(marker);

        final HospitalInfo info = hospitalMarkers.get(marker);
        onInstituteSelect(info);

        selectedMarker = marker;
    }

    private void onPharmacyMarkerClick(Marker marker) {
        highlightMarker(marker);

        final PharmacyInfo info = pharmacyMarkers.get(marker);
        onInstituteSelect(info);

        selectedMarker = marker;
    }
}
