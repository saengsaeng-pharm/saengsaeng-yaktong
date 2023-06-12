package kr.ac.cbnu.saengsaengyaktong.ui.detection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfo;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugInfoServiceWrapper;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.DrugListResponse;
import kr.ac.cbnu.saengsaengyaktong.api.public_data_portal.PublicDataPortalService;
import kr.ac.cbnu.saengsaengyaktong.databinding.ActivityDrugDetectionBinding;
import kr.ac.cbnu.saengsaengyaktong.ui.drugs.DrugInfoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrugDetectionActivity extends AppCompatActivity {
    private static class ResultOverlay {
        private final RectF boundingBox;
        private final String label;
        private final float probability;

        public ResultOverlay(RectF boundingBox, String label, float probability) {
            this.boundingBox = boundingBox;
            this.label = label;
            this.probability = probability;
        }

        public RectF getBoundingBox() {
            return boundingBox;
        }

        public String getLabel() {
            return label;
        }

        public float getProbability() {
            return probability;
        }

        @NonNull
        @Override
        public String toString() {
            return String.format("%s (%.1f%%)", label, probability);
        }
    }

    private final DrugInfoServiceWrapper drugInfoService = PublicDataPortalService.getInstance().getDrugInfoService();

    private static final String TAG = "DrugDetectionActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final float MAX_FONT_SIZE = 96F;

    private String currentPhotoPath;

    private ActivityDrugDetectionBinding binding;
    private DrugDetectionResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrugDetectionBinding.inflate(getLayoutInflater());

        adapter = new DrugDetectionResultsAdapter();
        adapter.setOnClickListener(this::onItemClick);

        binding.recyclerDetectionResults.setAdapter(adapter);

        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarDrugDetection);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("알약 인식");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        currentPhotoPath = getIntent().getStringExtra("photo_path");

        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setViewAndDetect(getCapturedImage());

                binding.getRoot().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void onItemClick(DrugDetectionResult item) {
        final Intent intent = new Intent(this, DrugInfoActivity.class);
        intent.putExtra("drugInfo", item.getInfo());

        startActivity(intent);
    }

    private void runObjectDetection(Bitmap bitmap) {
        TensorImage image = TensorImage.fromBitmap(bitmap);

        ObjectDetector.ObjectDetectorOptions options =
                ObjectDetector.ObjectDetectorOptions.builder()
                        .setMaxResults(5)
                        .setScoreThreshold(0.3f)
                        .build();

        try {
            ObjectDetector detector = ObjectDetector.createFromFileAndOptions(
                    this,
                    "model.tflite",
                    options
            );

            binding.textViewDrugNotFound.setVisibility(View.GONE);

            List<Detection> results = detector.detect(image);
            List<ResultOverlay> resultsToDraw = new ArrayList<>();

            Log.i(TAG, "results: " + results);
            binding.textViewDrugNotFound.setVisibility(results.isEmpty() ? View.VISIBLE : View.GONE);

            for (Detection result : results) {
                Category firstCategory = result.getCategories().get(0);
                ResultOverlay overlay = new ResultOverlay(result.getBoundingBox(), firstCategory.getLabel(), firstCategory.getScore() * 100);

                Log.i(TAG, "overlays: " + overlay.toString());
                resultsToDraw.add(overlay);

                for (Category category : result.getCategories()) {
                    drugInfoService.getDrugList(category.getLabel()).enqueue(new Callback<DrugListResponse>() {
                        @Override
                        public void onResponse(Call<DrugListResponse> call, Response<DrugListResponse> response) {
                            final DrugListResponse.Body body = response.body().getBody();
                            if (body.getTotalCount() == 0) return;

                            final DrugInfo item = body.getItems().get(0);
                            final DrugDetectionResult result = new DrugDetectionResult(item, category.getScore() * 100);

                            adapter.addItem(result);
                        }

                        @Override
                        public void onFailure(Call<DrugListResponse> call, Throwable t) {
                        }
                    });
                }
            }

            final Bitmap imgWithResult = drawDetectionResult(bitmap, resultsToDraw);
            runOnUiThread(() -> binding.imagePhoto.setImageBitmap(imgWithResult));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setViewAndDetect(Bitmap bitmap) {
        binding.imagePhoto.setImageBitmap(bitmap);
//        binding.tvPlaceholder.setVisibility(View.INVISIBLE);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> runObjectDetection(bitmap));
    }

    private Bitmap getCapturedImage() {
        int targetW = binding.imagePhoto.getWidth();
        int targetH = binding.imagePhoto.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inMutable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        try {
            ExifInterface exif = new ExifInterface(currentPhotoPath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(bitmap, 90f);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(bitmap, 180f);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(bitmap, 270f);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return bitmap;
    }

    private Bitmap rotateImage(Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap drawDetectionResult(Bitmap bitmap, List<ResultOverlay> detectionResults) {
        Bitmap outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(outputBitmap);
        Paint pen = new Paint();
        pen.setTextAlign(Paint.Align.LEFT);
        float MAX_FONT_SIZE = 80.0f;

        for (ResultOverlay result : detectionResults) {
            pen.setColor(Color.RED);
            pen.setStrokeWidth(8f);
            pen.setStyle(Paint.Style.STROKE);
            RectF box = result.getBoundingBox();
            canvas.drawRect(box, pen);

            Rect tagSize = new Rect(0, 0, 0, 0);

            pen.setStyle(Paint.Style.FILL_AND_STROKE);
            pen.setColor(Color.YELLOW);
            pen.setStrokeWidth(2f);

            pen.setTextSize(MAX_FONT_SIZE);
            pen.getTextBounds(result.toString(), 0, result.toString().length(), tagSize);
            float fontSize = pen.getTextSize() * box.width() / tagSize.width();

            if (fontSize < pen.getTextSize()) pen.setTextSize(fontSize);

            float margin = (box.width() - tagSize.width()) / 2.0f;
            if (margin < 0f) margin = 0f;
            canvas.drawText(result.toString(), box.left + margin, box.top + tagSize.height(), pen);
        }

        return outputBitmap;
    }
}
