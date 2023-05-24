package kr.ac.cbnu.saengsaengyaktong.ai;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;

import org.tensorflow.lite.gpu.CompatibilityList;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.Rot90Op;
import org.tensorflow.lite.task.core.BaseOptions;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;

import java.io.IOException;

public class ObjectDetectorHelper {
    public static final String MODEL_NAME = "model0510.tflite";

    public static final int DELEGATE_CPU = 0;
    public static final int DELEGATE_GPU = 1;
    public static final int DELEGATE_NNAPI = 2;

    private float threshold = 0.5f;
    private int numThreads = 2;
    private int maxResults = 3;
    private int currentDelegate = 0;
    private Context context;
    private DetectorListener objectDetectorListener;
    private ObjectDetector objectDetector = null;

    public ObjectDetectorHelper(Context context, DetectorListener objectDetectorListener) {
        this.context = context;
        this.objectDetectorListener = objectDetectorListener;
        setupObjectDetector();
    }

    public void clearObjectDetector() {
        objectDetector = null;
    }

    public void setupObjectDetector() {
        ObjectDetector.ObjectDetectorOptions.Builder optionsBuilder = ObjectDetector.ObjectDetectorOptions.builder()
                .setScoreThreshold(threshold)
                .setMaxResults(maxResults);

        BaseOptions.Builder baseOptionsBuilder = BaseOptions.builder().setNumThreads(numThreads);

        switch (currentDelegate) {
            case DELEGATE_CPU:
                // Default
                break;
            case DELEGATE_GPU:
                if (new CompatibilityList().isDelegateSupportedOnThisDevice()) {
                    baseOptionsBuilder.useGpu();
                } else {
                    objectDetectorListener.onError("GPU is not supported on this device");
                }
                break;
            case DELEGATE_NNAPI:
                baseOptionsBuilder.useNnapi();
                break;
        }

        optionsBuilder.setBaseOptions(baseOptionsBuilder.build());

        try {
            objectDetector = ObjectDetector.createFromFileAndOptions(context, MODEL_NAME, optionsBuilder.build());
        } catch (IllegalStateException | IOException e) {
            objectDetectorListener.onError("Object detector failed to initialize. See error logs for details");
            Log.e("Test", "TFLite failed to load model with error: " + e.getMessage());
        }
    }

    public void detect(Bitmap image, int imageRotation) {
        if (objectDetector == null) {
            setupObjectDetector();
        }

        long inferenceTime = SystemClock.uptimeMillis();

        ImageProcessor imageProcessor = new ImageProcessor.Builder()
                .add(new Rot90Op(-imageRotation / 90))
                .build();

        TensorImage tensorImage = imageProcessor.process(TensorImage.fromBitmap(image));

        java.util.List<Detection> results = objectDetector.detect(tensorImage);
        inferenceTime = SystemClock.uptimeMillis() - inferenceTime;
        objectDetectorListener.onResults(results, inferenceTime, tensorImage.getHeight(), tensorImage.getWidth());
    }

    public interface DetectorListener {
        void onError(String error);

        void onResults(java.util.List<Detection> results, long inferenceTime, int imageHeight, int imageWidth);
    }
}
