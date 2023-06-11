package kr.ac.cbnu.saengsaengyaktong.ui.drugs;

import androidx.annotation.Nullable;

public interface SearchSuggestion {
    String getId();

    String getName();

    String getCompanyName();

    @Nullable
    String getImageUrl();
}
