package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import dk.dtu.isaacirani.kirurgisksimulator.InstructorLoginActivity;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.StudentLoginActivity;
import dk.dtu.isaacirani.kirurgisksimulator.activities.SettingsActivity;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawer;
    Intent studentLogin, surgeonLogin;
    Button student, surgeon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

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
            case R.id.settings:
                intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.scenario_data:
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
            startActivity(new Intent(this, InstructorActivity.class).putExtra("instructorID", "-LWQO2j41v1ZhlUOx-xq"));
        }
    }


}
