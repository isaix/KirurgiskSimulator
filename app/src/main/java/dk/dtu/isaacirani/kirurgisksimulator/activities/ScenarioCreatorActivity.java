package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentBreadCrumbs;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Calendar;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.SimulatorPresenter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioCreatorActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SimulatorPresenter.View {

    SimulatorPresenter presenter;
    private int pressure;
    private int inflationRate;
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
    public EditText pressureValue;
    Scenario scenario = new Scenario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().hide();
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
        pressureValue = findViewById(R.id.valueOfPressure);

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
                Log.e(buttonvalue_1 + "", "yeees");
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
            if (view.getId() == value_2.getId()) {
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setTitle("Set Inflation Rate");
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertDialogBuilder.setButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inflationRate = Integer.parseInt(input.getText().toString());
                        scenario.setRate(inflationRate);
                        buttonvalue_2 = inflationRate;
                        value_1.setText(String.valueOf(inflationRate));
                        update1stBar();
                        update2ndBar();
                    }
                });
                alertDialogBuilder.show();
            }
            if (view.getId() == value_1.getId()) {
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
                alertDialogBuilder.setTitle("Set Pressure");
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertDialogBuilder.setView(input);
                alertDialogBuilder.setButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText()==null){
                            input.setText("0");
                        }
                        pressure = Integer.parseInt(input.getText().toString());
                        scenario.setPressure(pressure);
                        buttonvalue_1 = pressure;
                        value_1.setText(String.valueOf(pressure));
                        update1stBar();
                        update2ndBar();
                    }
                });
                alertDialogBuilder.show();
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
    public void update4thBar() {
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
    public void turnOnMachine() {
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


    private void showPopup(final Activity context, int xml) {
        int width = 400;
        int height = 300;
        LinearLayout viewGroup = context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = layoutInflater.inflate(xml, viewGroup);
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(viewLayout);
        popup.setWidth(width);
        popup.setHeight(height);
        popup.setFocusable(true);
        popup.showAtLocation(viewLayout, Gravity.CENTER, 0, 0);
        Button confirmPressure = viewLayout.findViewById(R.id.confirmPressure);
        confirmPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==confirmPressure.getId()){
                    scenario.setPressure(Integer.parseInt(pressureValue.getText().toString()));
                    popup.dismiss();
                }

            }
        });
    }
}
