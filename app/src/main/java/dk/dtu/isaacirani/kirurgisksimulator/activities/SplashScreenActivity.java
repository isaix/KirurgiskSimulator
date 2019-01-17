package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class SplashScreenActivity extends AppCompatActivity implements Runnable{

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(savedInstanceState == null){
            handler = new Handler();
            handler.postDelayed(this, 10000);
        }
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
