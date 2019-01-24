package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.MediaController;

import com.crashlytics.android.Crashlytics;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawer;
    Intent studentLogin, surgeonLogin;
    Button student, surgeon;
    MediaPlayer mediaPlayer;
    DisplayMetrics display;
    int width;
    float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        studentLogin = new Intent(this, StudentLoginActivity.class);
        surgeonLogin = new Intent(this, InstructorLoginActivity.class);

        student = findViewById(R.id.student);
        surgeon = findViewById(R.id.Intructor);

        student.setOnClickListener(this);
        surgeon.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing Application")
                    .setMessage("Are you sure you want to close the app?")
                    .setPositiveButton("Yes", (dialog, which) -> finish())
                    .setNegativeButton("No", null)
                    .show();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
           /* case R.id.scenarios:
                intent = new Intent(this, InstructorActivity.class);
                startActivity(intent);
                break;
                */
            case R.id.scenario_creator:
                intent = new Intent(this,ScenarioCreatorActivity.class);
                startActivity(intent);
                break;
                case R.id.scenario_manager:
                intent = new Intent(this,ScenarioManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.saved_data:
                intent = new Intent(this,LogDataActivity.class);
                startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v == student){
            startActivity(new Intent(this, StudentLoginActivity.class));
        } else if(v == surgeon){
            startActivity(new Intent(this, InstructorLoginActivity.class));
        }
    }
}
