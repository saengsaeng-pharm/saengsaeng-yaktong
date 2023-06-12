package kr.ac.cbnu.saengsaengyaktong.api;

import androidx.annotation.Nullable;

public interface ProductInfo {
    ProductType getType();

    String getId();

    String getName();

    String getCompanyName();

    @Nullable
    String getImageUrl();
}
