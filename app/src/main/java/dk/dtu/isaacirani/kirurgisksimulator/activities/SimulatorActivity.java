package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.SimulatorPresenter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SimulatorPresenter.View {

    SimulatorPresenter presenter;

    private boolean isOn;
    private TextView volume;
    private TextView pressure;
    private TextView rate;
    private Switch switchbutton;

    //nyt
    private ProgressBar pressureBar1, pressureBar2, rateBar1, rateBar2, airBar;
    private FloatingActionButton floatingplus1, floatingminus1, floatingplus2, floatingminus2;

    //nyt BR
    View view;
    Snackbar snackbarnotconnected;
    Snackbar snackbarisconnected;


    //nyt test animation
    AnimationDrawable bottleanimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator_container);

        int currentOrientation = this.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else{
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        presenter = new SimulatorPresenter(this);

        //laver View @namepåframe = (ImageView) find...(R.id.@navnetpåframe)
        View frame1 = findViewById(R.id.frame1);
        View frame2 = findViewById(R.id.frame2);
        View frame3 = findViewById(R.id.frame3);
        View frame4 = findViewById(R.id.frame4);


        //refererer til alle IV, IB og TV
        switchbutton = (Switch) frame1.findViewById(R.id.switchbutton);
        airBar = frame1.findViewById(R.id.airBar);

        floatingplus1 = frame2.findViewById(R.id.floatingplus1);
        floatingminus1 = frame2.findViewById(R.id.floatingminus1);
        pressure = (TextView) frame2.findViewById(R.id.pressure);
        pressureBar1 = frame2.findViewById(R.id.pressurebar);
        pressureBar2 = frame2.findViewById(R.id.pressurebar2);

        floatingplus2 = frame3.findViewById(R.id.floatingplus2);
        floatingminus2 = frame3.findViewById(R.id.floatingminus2);
        rate = (TextView) frame3.findViewById(R.id.rate);
        rateBar1 = frame3.findViewById(R.id.rateBar1);
        rateBar2 = frame3.findViewById(R.id.rateBar2);

        volume = (TextView) frame4.findViewById(R.id.totalvalue);


        //setter onclick på knapperne
        floatingplus1.setOnClickListener(this);
        floatingminus1.setOnClickListener(this);
        floatingminus2.setOnClickListener(this);
        floatingplus2.setOnClickListener(this);

        pressure.setOnClickListener(this);
        rate.setOnClickListener(this);
        volume.setOnClickListener(this);
        switchbutton.setOnCheckedChangeListener(this);
        //tror det er den her i skal bruge for den røde, hilsen Yoss
        //progressbar1.setBackgroundColor(R.drawable.progressdetails_red);

        //nyt BR
        registerReceiver();

        view = findViewById(android.R.id.content);

        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE);
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT);

        View snacknotconnectedview = snackbarnotconnected.getView();
        TextView textView = snacknotconnectedview.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);


        //nyt animationstest
        ImageView bottleanimated = frame1.findViewById(R.id.bottleanimated);
        bottleanimated.setBackgroundResource(R.drawable.bottleanimation);
        bottleanimation = (AnimationDrawable) bottleanimated.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        bottleanimation.start();
    }

    private void animateFloatingButton(final FloatingActionButton floatingActionButton) {
        floatingActionButton.animate().scaleX(0.9f).scaleY(0.9f).setDuration(10).withEndAction(new Runnable() {

            @Override
            public void run() {
                floatingActionButton.animate().scaleX(1f).scaleY(1f);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == pressure) {
            Log.e("UNDER", "PRESSURE");

        }
        if (view == floatingplus1) {
            animateFloatingButton(floatingplus1);
        }
        if (view == floatingminus1) {
            animateFloatingButton(floatingminus1);
        }
        if (view == floatingplus2) {
            animateFloatingButton(floatingplus2);
        }
        if (view == floatingminus2) {
            animateFloatingButton(floatingminus2);
        }
    }

    public void turnOnMachine() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Scenario scenario = new Scenario();
        scenario.setAir(50);
        scenario.setPressure(25);
        scenario.setPressureBar1(10);
        scenario.setPressureBar2(20);
        scenario.setRate(10);
        scenario.setRateBar1(25);
        scenario.setRateBar2(60);
        scenario.setVolume(20.6);


        isOn = isChecked;
        if (isChecked) {
            changeDisplayValues(scenario);
            Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_LONG).show();
            turnOnMachine();
        } else {
            Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void changeDisplayValues(Scenario scenario) {
        airBar.setProgress(scenario.getAir());

        pressureBar1.setProgress(scenario.getPressureBar1());
        pressureBar2.setProgress(scenario.getPressureBar2());
        pressure.setText(String.valueOf(scenario.getPressure()));
        Log.e("PRESSURE", String.valueOf(scenario.getPressure()));

        rateBar1.setProgress(scenario.getRateBar1());
        rateBar2.setProgress(scenario.getRateBar2());
        rate.setText(String.valueOf(scenario.getRate()));
        String newVolume = String.valueOf(scenario.getVolume());
        if (newVolume.length() > 4) {
            newVolume = newVolume.substring(0, 3);
        }

        volume.setText(newVolume);
    }


    //nyt BR

    private void registerReceiver() {

        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(NetworkChangeReceiver.NETWORK_CHANGE_ACTION);
            registerReceiver(networkChangeReceiver, filter);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void onDestroy() {

        try {
            unregisterReceiver(networkChangeReceiver);

        } catch (Exception e) {
            e.printStackTrace();

        } super.onDestroy();
    }

    InternalNetworkChangeReceiver networkChangeReceiver = new InternalNetworkChangeReceiver();
    class InternalNetworkChangeReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context c, Intent i) {


            if (i.getBooleanExtra("networkstatus", false) == false) {
                snackbarnotconnected.show();

            } else {
                if (snackbarnotconnected.isShown()){
                    snackbarnotconnected.dismiss();
                    snackbarisconnected.show();

                }
            }
        }
    }
}


