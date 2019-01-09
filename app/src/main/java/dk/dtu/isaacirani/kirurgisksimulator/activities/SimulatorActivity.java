package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private ImageView barblank1_1;
    private ImageView barblank1_2;
    private ImageView barblank2_1;
    private ImageView barblank2_2;
    private TextView totalvalue;
    private TextView value_1;
    private TextView value_2;
    private Switch switchbutton;
    private ImageButton minusbutton_1;
    private ImageButton plusbutton_1;
    private ImageButton minusbutton_2;
    private ImageButton plusbutton_2;
    //nyt
    private ProgressBar progressbar1;
    private ProgressBar progressicon;


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
        barblank1_1 = (ImageView) frame2.findViewById(R.id.barblank1);
        barblank2_1 = (ImageView) frame2.findViewById(R.id.barblank2);
        barblank1_2 = (ImageView) frame3.findViewById(R.id.barblank1);
        barblank2_2 = (ImageView) frame3.findViewById(R.id.barblank2);
        minusbutton_1 = (ImageButton) frame2.findViewById(R.id.minusbutton);
        plusbutton_1 = (ImageButton) frame2.findViewById(R.id.plusbutton);
        minusbutton_2 = (ImageButton) frame3.findViewById(R.id.minusbutton);
        plusbutton_2 = (ImageButton) frame3.findViewById(R.id.plusbutton);
        value_1 = (TextView) frame2.findViewById(R.id.value);
        value_2 = (TextView) frame3.findViewById(R.id.value);
        totalvalue = (TextView) frame4.findViewById(R.id.totalvalue);

        //setter onclick på knapperne
        plusbutton_1.setOnClickListener(this);
        minusbutton_1.setOnClickListener(this);
        plusbutton_2.setOnClickListener(this);
        minusbutton_2.setOnClickListener(this);
        value_1.setOnClickListener(this);
        value_2.setOnClickListener(this);
        totalvalue.setOnClickListener(this);
        switchbutton.setOnCheckedChangeListener(this);


        progressbar1 = frame2.findViewById(R.id.progressbar1);
        progressicon = frame1.findViewById(R.id.progressicon);

        progressicon.setMax(50);
        progressicon.setProgress(1);

        progressbar1.setMax(60);
        progressbar1.setProgress(1);

        //tror det er den her i skal bruge for den røde, hilsen Yoss
        //progressbar1.setBackgroundColor(R.drawable.progressdetails_red);

    }


    @Override
    public void onClick(View view) {
        if (isOn) {
            if (plusbutton_1 == view && buttonvalue_1 < 12) {
                buttonvalue_1++;
                value_1.setText(String.valueOf(buttonvalue_1));
                update1stBar();
                update2ndBar();
            } else if (minusbutton_1 == view && buttonvalue_1 > 0) {
                buttonvalue_1--;
                value_1.setText(String.valueOf(buttonvalue_1));
                update1stBar();
                update2ndBar();
            }

            if (plusbutton_2 == view && buttonvalue_2 < 12) {
                Log.e(buttonvalue_1+"", "yeees");
                buttonvalue_2++;
                value_2.setText(String.valueOf(buttonvalue_2));
                update3rdBar();
                update4thBar();
            } else if (minusbutton_2 == view && buttonvalue_2 > 0) {
                buttonvalue_2--;
                value_2.setText(String.valueOf(buttonvalue_2));
                update3rdBar();
                update4thBar();
            }
        }
    }

    @Override
        public void update1stBar() {
            switch (buttonvalue_1 / 2) {
                case 1:
                    barblank1_1.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank1_1.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank1_1.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank1_1.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank1_1.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank1_1.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }
    @Override
        public void update2ndBar() {
            switch (buttonvalue_1 / 2) {
                case 1:
                    barblank2_1.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank2_1.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank2_1.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank2_1.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank2_1.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank2_1.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }

    @Override
        public void update3rdBar() {
            switch (buttonvalue_2 / 2) {
                case 1:
                    barblank1_2.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank1_2.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank1_2.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank1_2.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank1_2.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank1_2.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }
    @Override
        public void update4thBar () {
            switch (buttonvalue_2 / 2) {
                case 1:
                    barblank2_2.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank2_2.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank2_2.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank2_2.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank2_2.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank2_2.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isOn = isChecked;
        if (isChecked) {
            Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_LONG).show();
            turnOnMachine();
        } else {
            Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void turnOnMachine(){
        update1stBar();
        update2ndBar();
        update3rdBar();
        update4thBar();
    }

    @Override
    public void changeDisplayValues(Scenario scenario) {
        value_1.setText(String.valueOf(scenario.getRate()));
        totalvalue.setText(String.valueOf(scenario.getVolume()));
        value_2.setText(String.valueOf(scenario.getPressure()));
        update1stBar();
        update2ndBar();
        update3rdBar();
        update4thBar();

    }
}
