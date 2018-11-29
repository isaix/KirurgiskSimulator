package dk.dtu.isaacirani.kirurgisksimulator.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenarioList;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;



public class ScenarioCreatorActivity extends AppCompatActivity implements View.OnClickListener {

    ScenarioAdapter scenarioAdapter = new ScenarioAdapter();
    TextView pressure;
    TextView inflation_rate;
    TextView total_air;
    TextView name;
    CheckBox inflation_hose;
    MockScenarioList mockScenarioList = new MockScenarioList();
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenariocreate_activity);

        pressure = findViewById(R.id.pressure);
        inflation_rate = findViewById(R.id.Inflation_rate);
        total_air = findViewById(R.id.Total_air);
        inflation_hose = findViewById(R.id.Inflation_hose);
        name = findViewById(R.id.NameScenario);

        save = findViewById(R.id.Save);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String pressureString = pressure.getText().toString();
        int finalPressure = Integer.parseInt(pressureString);

        String inflationString = inflation_rate.getText().toString();
        int finalInflation = Integer.parseInt(inflationString);

        String totalString = total_air.getText().toString();
        double finalTotal = Double.parseDouble(totalString);

        String finalName = name.getText().toString();

        if(inflation_hose.isChecked()){
            Scenario scenario = new Scenario(finalName,finalPressure,finalInflation,finalTotal,true);
            scenarioAdapter.createScenario(scenario);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Scenario scenario = new Scenario(finalName,finalPressure,finalInflation,finalTotal,false);
            scenarioAdapter.createScenario(scenario);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
