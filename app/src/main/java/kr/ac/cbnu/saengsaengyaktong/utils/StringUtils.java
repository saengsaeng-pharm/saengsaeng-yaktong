package kr.ac.cbnu.saengsaengyaktong.utils;

import android.text.Html;

import androidx.core.text.HtmlCompat;

public class StringUtils {
    public static String removeHtml(String value) {
        if (value == null) return null;
        return Html.fromHtml(value, HtmlCompat.FROM_HTML_MODE_LEGACY).toString();
    }
}
