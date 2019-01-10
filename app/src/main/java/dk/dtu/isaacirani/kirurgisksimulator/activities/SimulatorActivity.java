package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.SimulatorPresenter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SimulatorPresenter.View {

    SimulatorPresenter presenter;

    private boolean isOn;
    private int buttonvalue_1 = 00;
    private int buttonvalue_2 = 00;
    private TextView volume;
    private TextView pressure;
    private TextView rate;
    private Switch switchbutton;
    private ImageButton minusbutton_1;
    private ImageButton plusbutton_1;
    private ImageButton minusbutton_2;
    private ImageButton plusbutton_2;
    //nyt
    private ProgressBar pressureBar1, pressureBar2, rateBar1, rateBar2, airBar;


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
        minusbutton_1 = (ImageButton) frame2.findViewById(R.id.minusbutton);
        plusbutton_1 = (ImageButton) frame2.findViewById(R.id.plusbutton);
        minusbutton_2 = (ImageButton) frame3.findViewById(R.id.minusbutton);
        plusbutton_2 = (ImageButton) frame3.findViewById(R.id.plusbutton);
        pressure = (TextView) frame2.findViewById(R.id.value);
        rate = (TextView) frame3.findViewById(R.id.value);
        volume = (TextView) frame4.findViewById(R.id.totalvalue);
        airBar = frame1.findViewById(R.id.airBar);
        pressureBar1 = frame2.findViewById(R.id.progressbar1);
        pressureBar2 = frame2.findViewById(R.id.progressbar2);
        rateBar1 = frame3.findViewById(R.id.progressbar);
        rateBar2 = frame3.findViewById(R.id.progressbar2);

        //setter onclick på knapperne
        plusbutton_1.setOnClickListener(this);
        minusbutton_1.setOnClickListener(this);
        plusbutton_2.setOnClickListener(this);
        minusbutton_2.setOnClickListener(this);
        pressure.setOnClickListener(this);
        rate.setOnClickListener(this);
        volume.setOnClickListener(this);
        switchbutton.setOnCheckedChangeListener(this);
        //tror det er den her i skal bruge for den røde, hilsen Yoss
        //progressbar1.setBackgroundColor(R.drawable.progressdetails_red);

    }


    @Override
    public void onClick(View view) {
        if (isOn) {
            if (plusbutton_1 == view && buttonvalue_1 < 12) {
                buttonvalue_1++;
                pressure.setText(String.valueOf(buttonvalue_1));

            } else if (minusbutton_1 == view && buttonvalue_1 > 0) {
                buttonvalue_1--;
                pressure.setText(String.valueOf(buttonvalue_1));
            }

            if (plusbutton_2 == view && buttonvalue_2 < 12) {
                Log.e(buttonvalue_1+"", "yeees");
                buttonvalue_2++;
                rate.setText(String.valueOf(buttonvalue_2));

            } else if (minusbutton_2 == view && buttonvalue_2 > 0) {
                buttonvalue_2--;
                rate.setText(String.valueOf(buttonvalue_2));

            }
        }
    }

    public void turnOnMachine(){

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


        isOn = isChecked;
        if (isChecked) {
            changeDisplayValues(scenario);
            /*
            Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_LONG).show();
            turnOnMachine();*/
        } else {
            //Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void changeDisplayValues(Scenario scenario) {
        airBar.setProgress(scenario.getAir());

        pressureBar1.setProgress(scenario.getPressureBar1());
        pressureBar2.setProgress(scenario.getPressureBar2());
        pressure.setText(String.valueOf(scenario.getPressure()));

        rateBar1.setProgress(scenario.getRateBar1());
        rateBar2.setProgress(scenario.getRateBar2());
        rate.setText(String.valueOf(scenario.getRate()));
        String newVolume = String.valueOf(scenario.getVolume());
        if(newVolume.length() > 4){
            newVolume = newVolume.substring(0 , 3);
        }

        volume.setText(newVolume);

        pressure.setText(String.valueOf(scenario.getRate()));
        volume.setText(String.valueOf(scenario.getVolume()));
        rate.setText(String.valueOf(scenario.getPressure()));


    }
}
