package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.SimulatorPresenter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository;

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SimulatorPresenter.View {

    SimulatorPresenter presenter;

    private boolean isOn;
    private TextView volume;
    private TextView pressure;
    private TextView rate;
    private Switch switchbutton;
    MediaPlayer turnOn;
    GroupsRepository groupsRepository;
    MediaPlayer mediaPlayer;

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
        switchbutton.setEnabled(true);
        airBar = frame1.findViewById(R.id.airBar);

        floatingplus1 = frame2.findViewById(R.id.floatingplus1);
        floatingminus1 = frame2.findViewById(R.id.floatingminus1);
        pressure = (TextView) frame2.findViewById(R.id.pressure);
        pressureBar1 = frame2.findViewById(R.id.progressBar1);
        pressureBar2 = frame2.findViewById(R.id.progressBar2);

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


        floatingplus1.setElevation(0);
        floatingminus1.setElevation(0);
        floatingminus2.setElevation(0);
        floatingplus2.setElevation(0);

        floatingplus1.setEnabled(false);
        floatingminus1.setEnabled(false);
        floatingplus2.setEnabled(false);
        floatingminus2.setEnabled(false);



        /*
        pressure.setOnClickListener(this);
        rate.setOnClickListener(this);
        volume.setOnClickListener(this);
        switchbutton.setOnCheckedChangeListener(this);
        */
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


        //henter fra firebasen
        groupsRepository = new GroupsRepository();
        groupsRepository.loadStudentScenario(
                getIntent().getStringExtra("studentId"),
                getIntent().getStringExtra("groupId"),
                scenario -> {
                    if(scenario == null){
//                        this.finish();
                    }else {
                        changeDisplayValues(scenario);
                    }
                    return null;
                });
        groupsRepository.loadStudentScenario(getIntent().getStringExtra("studentId"), getIntent().getStringExtra("groupId"), scenario -> {changeDisplayValues(scenario); return null;});

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

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        turnOn = MediaPlayer.create(this, R.raw.turnon);
        turnOn.start();
        Scenario scenario = new Scenario();
        scenario.setAir(0);
        scenario.setPressure(0);
        scenario.setPressureBar1(00);
        scenario.setPressureBar2(00);
        scenario.setRate(00);
        scenario.setRateBar1(00);
        scenario.setRateBar2(00);
        scenario.setVolume(00.0);


        isOn = isChecked;

        if (isChecked) {
            changeDisplayValues(scenario);
            Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void changeDisplayValues(Scenario scenario) {
        mediaPlayer = MediaPlayer.create(this, R.raw.turnon);
        mediaPlayer.start();
        airBar.setProgress(scenario.getAir());

        pressureBar1.setProgress(scenario.getPressureBar1());
        pressureBar2.setProgress(scenario.getPressureBar2());
        rateBar1.setProgress(scenario.getRateBar1());
        rateBar2.setProgress(scenario.getRateBar2());
        String newVolume = String.valueOf(scenario.getVolume());
        volume.setText(newVolume);


        if(scenario.getPressure() < 10 ){
            pressure.setText("0" + scenario.getPressure());
        } else {
            pressure.setText(String.valueOf(scenario.getPressure()));
        }

        if(scenario.getRate() < 10 ){
            rate.setText("0" + scenario.getRate());
        } else {
            rate.setText(String.valueOf(scenario.getRate()));
        }

        if (newVolume.length() <= 3) {
            newVolume = "0" + newVolume;
        } else { newVolume = newVolume.substring(0,4);

        }  volume.setText(newVolume);


        if (scenario.getPressureBar1() <= 10) {
            pressureBar1.setMax(50);
        } else if (scenario.getPressureBar1() <= 30) {
            pressureBar1.setMax(30);
        } else if (scenario.getPressureBar1() < 50) {
            pressureBar1.setMax(50);
            pressureBar1.setProgress(45);
        }

        if (scenario.getPressureBar2() <= 10) {
            pressureBar2.setMax(50);
        } else if (scenario.getPressureBar2()<= 30) {
            pressureBar2.setMax(30);
        } else if (scenario.getPressureBar2() < 50) {
            pressureBar2.setMax(50);
            pressureBar2.setProgress(45);
        }

        if (scenario.getRateBar1() <= 10) {
            rateBar1.setMax(20);
        } else if (scenario.getRateBar1() <= 20){
            rateBar1.setMax(27);
        } else {
            rateBar1.setMax(30);
        }

        if (scenario.getRateBar2() <= 10) {
            rateBar2.setMax(20);
        } else if (scenario.getRateBar2() <= 20){
            rateBar2.setMax(27);
        } else {
            rateBar2.setMax(30);
        }
    }


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
                switchbutton.setEnabled(false);


            } else {
                if (snackbarnotconnected.isShown()){
                    snackbarnotconnected.dismiss();
                    snackbarisconnected.show();
                    switchbutton.setEnabled(true);

                }
            }
        }
    }
}


