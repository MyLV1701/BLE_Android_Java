package com.example.ble.view.utils;

import android.view.View;
import android.view.Window;

public class ViewControllerUtils {
    public static void hideNavigationBar(Window window) {
        View view = window.getDecorView();
        int uiOption = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        view.setSystemUiVisibility(uiOption);
    }
}
