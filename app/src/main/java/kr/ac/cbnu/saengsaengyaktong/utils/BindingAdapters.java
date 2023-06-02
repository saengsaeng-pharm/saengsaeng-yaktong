package kr.ac.cbnu.saengsaengyaktong.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import javax.annotation.Nullable;

public class BindingAdapters {
    @BindingAdapter({"bind:imageUrl", "bind:imageCropPart", "bind:imagePlaceHolder"})
    public static void bindImage(ImageView imageView, String url, @Nullable HalfCropTransformation.CropPart cropPart, @Nullable Drawable imagePlaceHolder) {
        if (url != null) {
            final Uri uri = Uri.parse(url).buildUpon().scheme("https").build();
            RequestBuilder<Drawable> request = Glide.with(imageView).load(uri);

            if (cropPart != null) {
                request = request.transform(new HalfCropTransformation(cropPart));
            }

            if (imagePlaceHolder != null) {
                request = request.placeholder(imagePlaceHolder);
            }

            request.into(imageView);
        }
    }
}
