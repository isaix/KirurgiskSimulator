package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.SimulatorPresenter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;
import dk.dtu.isaacirani.kirurgisksimulator.repositories.ScenarioRepository;

public class ScenarioCreatorActivity extends AppCompatActivity implements View.OnClickListener, SimulatorPresenter.View {

    SimulatorPresenter presenter;
    ScenarioRepository scenarioAdapter = new ScenarioRepository();
    Scenario scenario = new Scenario();
    private int inflationRate = 0;
    private int pressureValue = 0;
    private String name = "";
    private int air = 0;
    private int pressureBar1Value = 0;
    private int pressureBar2Value = 0;
    private int rateBar1Value = 0;
    private int rateBar2Value = 0;
    private double volumeValue = 0.0;
    private Boolean nozzle = false;
    private boolean isOn;
    private TextView volume;
    private TextView pressure;
    private TextView rate;
    private Switch switchbutton;
    private Button save;
    private ImageView animationBottle;
    //nyt
    private ProgressBar pressureBar1, pressureBar2, rateBar1, rateBar2, airBar;
    private FloatingActionButton floatingplus1, floatingminus1, floatingplus2, floatingminus2;

    AnimationDrawable bottleanimation;
    //nyt BR
    View view;
    private int i = 0;
    private int j = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator_container);
        presenter = new SimulatorPresenter(this);

        //laver View @namepåframe = (ImageView) find...(R.id.@navnetpåframe)
        View frame1 = findViewById(R.id.frame1);
        View frame2 = findViewById(R.id.frame2);
        View frame3 = findViewById(R.id.frame3);
        View frame4 = findViewById(R.id.frame4);

        Button saveButton = new Button(this);
        saveButton.setText("SAVE SCENARIO");
        this.addContentView(saveButton, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));



        //refererer til alle IV, IB og TV
        switchbutton = (Switch) frame1.findViewById(R.id.switchbutton);
        airBar = frame1.findViewById(R.id.airBar);

        floatingplus1 = frame2.findViewById(R.id.floatingplus1);
        floatingminus1 = frame2.findViewById(R.id.floatingminus1);
        pressure = (TextView) frame2.findViewById(R.id.rate);
        pressureBar1 = frame2.findViewById(R.id.rateBar1);
        pressureBar2 = frame2.findViewById(R.id.rateBar2);

        floatingplus2 = frame3.findViewById(R.id.floatingminus2);
        floatingminus2 = frame3.findViewById(R.id.floatingminus2);
        rate = (TextView) frame3.findViewById(R.id.rate);
        rateBar1 = frame3.findViewById(R.id.rateBar1);
        rateBar2 = frame3.findViewById(R.id.rateBar2);
        ImageView bottleanimated = frame1.findViewById(R.id.bottleanimated);
        bottleanimated.setBackgroundResource(R.drawable.bottleanimation);
        bottleanimation = (AnimationDrawable) bottleanimated.getBackground();

        volume = (TextView) frame4.findViewById(R.id.totalvalue);

        //setter onclick på knapperne
        floatingplus1.setOnClickListener(this);
        floatingminus1.setOnClickListener(this);
        floatingminus2.setOnClickListener(this);
        floatingplus2.setOnClickListener(this);

        pressure.setOnClickListener(this);
        pressureBar1.setOnClickListener(this);
        pressureBar2.setOnClickListener(this);
        rate.setOnClickListener(this);
        rateBar1.setOnClickListener(this);
        rateBar2.setOnClickListener(this);
        volume.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        airBar.setOnClickListener(this);
        //tror det er den her i skal bruge for den røde, hilsen Yoss
        //progressbar1.setBackgroundColor(R.drawable.progressdetails_red);

        view = findViewById(android.R.id.content);
        bottleanimation.start();

    }


    private void animateFloatingButton(final FloatingActionButton floatingActionButton) {
        floatingActionButton.animate().scaleX(0.9f).scaleY(0.9f).setDuration(10).withEndAction(() -> floatingActionButton.animate().scaleX(1f).scaleY(1f));
    }

    @Override
    public void onClick(View view) {
        if (view == pressure) {
            Log.e("UNDER", "PRESSURE");
        }
        if (view == floatingplus1) {
            animateFloatingButton(floatingplus1);
            i++;
            scenario.setPressure(i);
            pressure.setText(String.valueOf(i));
        }
        if (view == floatingminus1) {
            animateFloatingButton(floatingminus1);
            i--;
            scenario.setPressure(i);
            pressure.setText(String.valueOf(i));
        }
        if (view == floatingplus2) {
            animateFloatingButton(floatingplus2);
            j++;
            scenario.setRate(j);
            rate.setText(String.valueOf(j));
        }
        if (view == floatingminus2) {
            animateFloatingButton(floatingminus2);
            j--;
            scenario.setRate(j);
            rate.setText(String.valueOf(j));
        }
        if (view.getId() == rate.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Inflation Rate");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Integer.parseInt(input.getText().toString()) < 0 || Integer.parseInt(input.getText().toString()) > 31) {
                    Toast.makeText(getApplicationContext(), "Inflation Rate not between 0 and 30", Toast.LENGTH_LONG).show();
                } else {
                    inflationRate = Integer.parseInt(input.getText().toString());
                    if(inflationRate<10){
                        input.setText("0" + input.getText());
                    }
                    scenario.setRate(inflationRate);
                    j = inflationRate;
                    rate.setText(input.getText());
                }
            });
            alertDialogBuilder.show();
        }
        if (view.getId() == pressure.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Pressure");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Integer.parseInt(input.getText().toString()) <= 0 || Integer.parseInt(input.getText().toString()) > 51) {
                    Toast.makeText(getApplicationContext(), "Pressure not between 0 and 50", Toast.LENGTH_LONG).show();
                } else {
                    pressureValue = Integer.parseInt(input.getText().toString());
                    if (Integer.parseInt(input.getText().toString()) < 10) {
                        input.setText("0" + input.getText());
                    }
                    i = pressureValue;
                    scenario.setPressure(pressureValue);
                    pressure.setText(input.getText());
                }
            });
            alertDialogBuilder.show();

        }

        if (view.getId() == pressureBar1.getId()) {
            String hej = String.valueOf(pressureBar1.getId());
            Log.e("PressureBarValue", hej);
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Pressure Bar Value 1");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Integer.parseInt(input.getText().toString()) <= 0 || Integer.parseInt(input.getText().toString()) > 51) {
                    Toast.makeText(getApplicationContext(), "Pressure not between 0 and 50", Toast.LENGTH_LONG).show();
                } else {
                    pressureBar1Value = Integer.parseInt(input.getText().toString());
                    scenario.setPressureBar1(pressureBar1Value);
                    pressureBar1.setProgress(pressureBar1Value);
                }
            });
            alertDialogBuilder.show();

        }
        if (view.getId() == pressureBar2.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Pressure Bar Value 2");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Integer.parseInt(input.getText().toString()) <= 0 || Integer.parseInt(input.getText().toString()) > 51) {
                    Toast.makeText(getApplicationContext(), "Pressure not between 0 and 50", Toast.LENGTH_LONG).show();
                } else {
                    pressureBar2Value = Integer.parseInt(input.getText().toString());
                    scenario.setPressureBar2(pressureBar2Value);
                    pressureBar2.setProgress(pressureBar2Value);
                }
            });
            alertDialogBuilder.show();
        }
        if (view.getId() == rateBar1.getId()) {
            String hej = String.valueOf(rateBar1.getId());
            Log.e("rateBarValue", hej);
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Inflation Rate Bar 1");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Integer.parseInt(input.getText().toString()) <= 0 || Integer.parseInt(input.getText().toString()) > 31) {
                    Toast.makeText(getApplicationContext(), "Pressure not between 0 and 30", Toast.LENGTH_LONG).show();
                } else {
                    rateBar1Value = Integer.parseInt(input.getText().toString());
                    scenario.setRateBar1(rateBar1Value);
                    rateBar1.setProgress(rateBar1Value);
                }
            });
            alertDialogBuilder.show();
        }
        if (view.getId() == rateBar2.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Inflation Rate Bar 2");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Integer.parseInt(input.getText().toString()) <= 0 || Integer.parseInt(input.getText().toString()) > 31) {
                    Toast.makeText(getApplicationContext(), "Pressure not between 0 and 30", Toast.LENGTH_LONG).show();
                } else {
                    rateBar2Value = Integer.parseInt(input.getText().toString());
                    scenario.setRateBar2(rateBar2Value);
                    rateBar2.setProgress(rateBar2Value);
                }
            });
            alertDialogBuilder.show();
        }
        if (view.getId() == volume.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Total Volume");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("1");
                }
                if (Double.parseDouble(input.getText().toString()) <= 0.0 || Double.parseDouble(input.getText().toString()) > 100.0) {
                    Toast.makeText(getApplicationContext(), "Volume not between 0 and 100", Toast.LENGTH_LONG).show();
                } else {
                    volumeValue = Double.parseDouble(input.getText().toString());
                    scenario.setVolume(volumeValue);
                    volume.setText(input.getText());
                }
            });
            alertDialogBuilder.show();
        }
        if (view.getId() == save.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Type In Scenario Name");
            final EditText input = new EditText(this);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("DefaultScenarioName");
                } else {
                    name = input.getText().toString();
                    scenario.setName(name);
                    scenarioAdapter.createScenario(scenario);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });
            alertDialogBuilder.show();
        }
        if (view.getId() == airBar.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Type in AirBar level");
            final EditText input = new EditText(this);
            alertDialogBuilder.setView(input);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setButton("Confirm", (dialog, which) -> {
                if (input.getText() == null) {
                    input.setText("0");
                }
                if(Integer.parseInt(input.getText().toString())>100 || Integer.parseInt(input.getText().toString())<0){
                    Toast.makeText(getApplicationContext(),"Air not between 0 and 100", Toast.LENGTH_LONG).show();
                }
                else {
                    air = Integer.parseInt(input.getText().toString());
                    scenario.setAir(air);
                    airBar.setProgress(air);
                }
            });
            alertDialogBuilder.show();
        }
    }

        @Override
        public void changeDisplayValues (Scenario scenario){

        }

}



