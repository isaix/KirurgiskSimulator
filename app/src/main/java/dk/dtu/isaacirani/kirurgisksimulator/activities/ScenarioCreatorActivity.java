package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.SimulatorPresenter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioCreatorActivity extends AppCompatActivity implements View.OnClickListener, SimulatorPresenter.View {

    SimulatorPresenter presenter;

    Scenario scenario = new Scenario();
    private int inflationRate;
    private int pressureValue;
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


        //refererer til alle IV, IB og TV
        switchbutton = (Switch) frame1.findViewById(R.id.switchbutton);
        airBar = frame1.findViewById(R.id.airBar);

        floatingplus1 = frame2.findViewById(R.id.floatingplus1);
        floatingminus1 = frame2.findViewById(R.id.floatingminus1);
        pressure = (TextView) frame2.findViewById(R.id.pressure);
        pressureBar1 = frame2.findViewById(R.id.progressbar1);
        pressureBar2 = frame2.findViewById(R.id.progressbar2);

        floatingplus2 = frame3.findViewById(R.id.floatingplus2);
        floatingminus2 = frame3.findViewById(R.id.floatingminus2);
        rate = (TextView) frame3.findViewById(R.id.rate);
        rateBar1 = frame3.findViewById(R.id.progressbar1);
        rateBar2 = frame3.findViewById(R.id.progressbar2);

        volume = (TextView) frame4.findViewById(R.id.totalvalue);


        //setter onclick på knapperne
        floatingplus1.setOnClickListener(this);
        floatingminus1.setOnClickListener(this);
        floatingminus2.setOnClickListener(this);
        floatingplus2.setOnClickListener(this);

        pressure.setOnClickListener(this);
        rate.setOnClickListener(this);
        volume.setOnClickListener(this);
        //tror det er den her i skal bruge for den røde, hilsen Yoss
        //progressbar1.setBackgroundColor(R.drawable.progressdetails_red);

        view = findViewById(android.R.id.content);

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
        if (view.getId() == rate.getId()) {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
            alertDialogBuilder.setTitle("Set Inflation Rate");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alertDialogBuilder.setView(input);
            alertDialogBuilder.setButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    inflationRate = Integer.parseInt(input.getText().toString());
                    scenario.setRate(inflationRate);
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
            alertDialogBuilder.setButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText() == null) {
                        input.setText("0");
                    }
                    if (Integer.parseInt(input.getText().toString()) < 0 || Integer.parseInt(input.getText().toString()) > 99) {
                        Toast.makeText(getApplicationContext(), "Pressure not between 0 and 99", Toast.LENGTH_LONG).show();
                    }
                    pressureValue = Integer.parseInt(input.getText().toString());
                    scenario.setPressure(pressureValue);
                }
            });
            alertDialogBuilder.show();

        }



    }

    @Override
    public void changeDisplayValues(Scenario scenario) {

    }

    @Override
    public void turnOnMachine() {

    }
}


