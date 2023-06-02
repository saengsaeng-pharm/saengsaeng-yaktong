package kr.ac.cbnu.saengsaengyaktong.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class HalfCropTransformation extends BitmapTransformation {
    public enum CropPart {
        LEFT,
        RIGHT
    }

    private final CropPart part;

    public HalfCropTransformation(CropPart part) {
        this.part = part;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();

        int targetWidth = width / 2;
        int startX = part == CropPart.LEFT ? 0 : width / 2;
        int targetHeight = height;
        int startY = 0;

        final Bitmap.Config config = toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888;
        final Bitmap bitmap = pool.get(targetWidth, targetHeight, config);

        final Canvas canvas = new Canvas(bitmap);
        final Rect srcRect = new Rect(startX, startY, startX + targetWidth, startY + targetHeight);
        final RectF destRect = new RectF(0, 0, targetWidth, targetHeight);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);

        canvas.drawBitmap(toTransform, srcRect, destRect, paint);
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(getClass().getName().getBytes(CHARSET));
    }
}