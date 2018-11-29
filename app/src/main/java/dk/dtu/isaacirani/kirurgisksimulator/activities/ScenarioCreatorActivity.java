package dk.dtu.isaacirani.kirurgisksimulator.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenarioList;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioCreatorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView pressure;
    TextView inflation_rate;
    TextView total_air;
    CheckBox inflation_hose;
    MockScenarioList mockScenarioList = new MockScenarioList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenariocreate_activity);

        pressure = findViewById(R.id.pressure);
        inflation_rate = findViewById(R.id.Inflation_rate);
        total_air = findViewById(R.id.Total_air);
        inflation_hose = findViewById(R.id.Inflation_hose);

    }

    @Override
    public void onClick(View v) {
        String pressureString = pressure.getText().toString();
        int finalPressure = Integer.parseInt(pressureString);

        String inflationString = inflation_rate.getText().toString();
        int finalInflation = Integer.parseInt(inflationString);

        String totalString = total_air.getText().toString();
        double fintalTotal = Double.parseDouble(totalString);

        if(inflation_hose.isChecked()){
            Scenario scenario = new Scenario("TEST22",finalPressure,finalInflation,fintalTotal,true);
            mockScenarioList.getScenarios().add(scenario);
        }
        else{
            Scenario scenario = new Scenario("TEST2",finalPressure,finalInflation,fintalTotal,false);
            mockScenarioList.getScenarios().add(scenario);
        }

    }
}
