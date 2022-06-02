package com.planatechnologies.androidchallenge.Helpers;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

public class Helper {

    /**
     * It hides the status bar.
     *
     * @param activity The activity that you want to hide the system UI on.
     */
    public static void hideSystemUI(Activity activity) {
        activity.getWindow()
                .clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        activity.getWindow()
                .getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * Hide the soft keyboard from the screen
     *
     * @param activity The activity that is currently open.
     */
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view != null) {
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(
                    Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
        }
    }

}
