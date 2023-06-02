package kr.ac.cbnu.saengsaengyaktong.ui.recognition;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.tensorflow.lite.task.vision.detector.Detection;

import kr.ac.cbnu.saengsaengyaktong.databinding.FragmentCameraBinding;

public class CameraFragment extends Fragment implements ObjectDetectorHelper.DetectorListener {
    private final String TAG = "ObjectDetection";

    private FragmentCameraBinding _fragmentCameraBinding = null;

    private FragmentCameraBinding getFragmentCameraBinding() {
        return _fragmentCameraBinding;
    }

    private ObjectDetectorHelper objectDetectorHelper;
    private Bitmap bitmapBuffer;
    private Preview preview;
    private ImageAnalysis imageAnalyzer;
    private Camera camera;
    private ProcessCameraProvider cameraProvider;

    private ExecutorService cameraExecutor;

    @Override
    public void onResume() {
        super.onResume();

        if (!PermissionsFragment.hasPermissions(requireContext())) {
//            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.permissions_fragment);
        }
    }

    @Override
    public void onDestroyView() {
        _fragmentCameraBinding = null;
        super.onDestroyView();

        cameraExecutor.shutdown();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _fragmentCameraBinding = FragmentCameraBinding.inflate(inflater, container, false);
        return getFragmentCameraBinding().getRoot();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        objectDetectorHelper = new ObjectDetectorHelper(requireContext(), this);
        cameraExecutor = Executors.newSingleThreadExecutor();

        getFragmentCameraBinding().viewFinder.post(() -> setUpCamera());
    }

    private void updateControlsUi() {
        objectDetectorHelper.clearObjectDetector();
        getFragmentCameraBinding().overlay.clear();
    }

    private void setUpCamera() {
        ProcessCameraProvider.getInstance(requireContext()).addListener(() -> {
            try {
                cameraProvider = ProcessCameraProvider.getInstance(requireContext()).get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            bindCameraUseCases();
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    @SuppressLint("UnsafeOptInUsageError")
    private void bindCameraUseCases() {
        if (cameraProvider == null)
            throw new IllegalStateException("Camera initialization failed.");

        CameraSelector cameraSelector =
                new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

        preview = new Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setTargetRotation(getFragmentCameraBinding().viewFinder.getDisplay().getRotation())
                .build();

        imageAnalyzer = new ImageAnalysis.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setTargetRotation(getFragmentCameraBinding().viewFinder.getDisplay().getRotation())
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build();
        imageAnalyzer.setAnalyzer(cameraExecutor, image -> {
            if (bitmapBuffer == null) {
                bitmapBuffer = Bitmap.createBitmap(
                        image.getWidth(),
                        image.getHeight(),
                        Bitmap.Config.ARGB_8888
                );
            }

            detectObjects(image);
        });

        cameraProvider.unbindAll();

        try {
            camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer);

            preview.setSurfaceProvider(getFragmentCameraBinding().viewFinder.getSurfaceProvider());
        } catch (Exception exc) {
            Log.e(TAG, "Use case binding failed", exc);
        }
    }

    private void detectObjects(ImageProxy image) {
        bitmapBuffer.copyPixelsFromBuffer(image.getPlanes()[0].getBuffer());

        int imageRotation = image.getImageInfo().getRotationDegrees();
        objectDetectorHelper.detect(bitmapBuffer, imageRotation);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (imageAnalyzer != null)
            imageAnalyzer.setTargetRotation(getFragmentCameraBinding().viewFinder.getDisplay().getRotation());
    }

    @Override
    public void onResults(List<Detection> results, long inferenceTime, int imageHeight, int imageWidth) {
        getActivity().runOnUiThread(() -> {
            getFragmentCameraBinding().overlay.setResults(
                    results,
                    imageHeight,
                    imageWidth
            );

            getFragmentCameraBinding().overlay.invalidate();
        });
    }

    @Override
    public void onError(String error) {
        getActivity().runOnUiThread(() ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());
    }
}