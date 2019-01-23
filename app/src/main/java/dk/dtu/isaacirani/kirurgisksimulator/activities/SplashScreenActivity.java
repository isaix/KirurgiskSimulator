package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    Handler handler;
    DisplayMetrics display;
    int width;
    float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        float widthscreen;
        display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        width = display.widthPixels;
        scale = display.density;
        widthscreen = width / scale;

        if (widthscreen <= 600){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        if (savedInstanceState == null) {
            handler = new Handler();
            handler.postDelayed(this, 2000);
        }
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
