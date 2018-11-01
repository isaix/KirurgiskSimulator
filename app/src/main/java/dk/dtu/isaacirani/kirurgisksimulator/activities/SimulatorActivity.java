package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class SimulatorActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private boolean isOn;
    private int buttonvalue1 = 00;
    private int buttonvalue2 = 00;
    private ImageView barblank1;
    private ImageView barblank2;
    private ImageView barblank3;
    private ImageView barblank4;
    private ImageButton plusbutton1;
    private ImageButton minusbutton1;
    private ImageButton plusbutton2;
    private ImageButton minusbutton2;
    private TextView tevalue;
    private TextView tevalue00;
    private TextView tevalue000;
    private Switch switchbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        //refererer til alle IV, IB og TV
        barblank1 = (ImageView) findViewById(R.id.barblank1);
        barblank2 = (ImageView) findViewById(R.id.barblank2);
        barblank3 = (ImageView) findViewById(R.id.barblank3);
        barblank4 = (ImageView) findViewById(R.id.barblank4);

        plusbutton1 = (ImageButton) findViewById(R.id.plusbutton1);
        plusbutton2 = (ImageButton) findViewById(R.id.plusbutton2);
        minusbutton1 = (ImageButton) findViewById(R.id.minusbutton1);
        minusbutton2 = (ImageButton) findViewById(R.id.minusbutton2);

        tevalue00 = (TextView) findViewById(R.id.tevalue00);
        tevalue = (TextView) findViewById(R.id.tevalue);
        tevalue000 = (TextView) findViewById(R.id.tevalue000);
        switchbutton = (Switch) findViewById(R.id.switchbutton);

        //setter onclick på knapperne
        plusbutton1.setOnClickListener(this);
        plusbutton2.setOnClickListener(this);
        minusbutton1.setOnClickListener(this);
        minusbutton2.setOnClickListener(this);
        tevalue000.setOnClickListener(this);
        tevalue00.setOnClickListener(this);
        tevalue.setOnClickListener(this);
        switchbutton.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        if (isOn) {
            if (plusbutton1 == view && buttonvalue1 < 12) {
                buttonvalue1++;
                tevalue00.setText(String.valueOf(buttonvalue1));
                update1stBar();
                update2ndtBar();
            } else if (minusbutton1 == view && buttonvalue1 > 0) {
                buttonvalue1--;
                tevalue00.setText(String.valueOf(buttonvalue1));
                update1stBar();
                update2ndtBar();
            }

            if (plusbutton2 == view && buttonvalue2 < 12) {
                buttonvalue2++;
                tevalue.setText(String.valueOf(buttonvalue2));
                update3rdBar();
                update4thdBar();
            } else if (minusbutton2 == view && buttonvalue2 > 0) {
                buttonvalue2--;
                tevalue.setText(String.valueOf(buttonvalue2));
                update3rdBar();
                update4thdBar();
            }
        }
    }


        public void update1stBar () {
            switch (buttonvalue1 / 2) {
                case 1:
                    barblank1.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank1.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank1.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank1.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank1.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank1.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }

        public void update2ndtBar () {
            switch (buttonvalue1 / 2) {
                case 1:
                    barblank2.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank2.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank2.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank2.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank2.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank2.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }


        public void update3rdBar () {
            switch (buttonvalue2 / 2) {
                case 1:
                    barblank3.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank3.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank3.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank3.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank3.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank3.setImageResource(R.drawable.bar6);
                default:
                    break;
            }
        }

        public void update4thdBar () {
            switch (buttonvalue2 / 2) {
                case 1:
                    barblank4.setImageResource(R.drawable.bar1);
                    break;
                case 2:
                    barblank4.setImageResource(R.drawable.bar2);
                    break;
                case 3:
                    barblank4.setImageResource(R.drawable.bar3);
                    break;
                case 4:
                    barblank4.setImageResource(R.drawable.bar4);
                    break;
                case 5:
                    barblank4.setImageResource(R.drawable.bar5);
                    break;
                case 6:
                    barblank4.setImageResource(R.drawable.bar6);
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

    public void turnOnMachine(){
        update1stBar();
        update2ndtBar();
        update3rdBar();
        update4thdBar();
    }
}
