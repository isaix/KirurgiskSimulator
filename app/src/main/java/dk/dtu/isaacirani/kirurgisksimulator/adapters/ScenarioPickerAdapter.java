package dk.dtu.isaacirani.kirurgisksimulator.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioPickerViewHolder;
import dk.dtu.isaacirani.kirurgisksimulator.activities.InstructorActivity;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioPickerAdapter extends RecyclerView.Adapter<ScenarioPickerViewHolder> {
    ArrayList<Scenario> scenarios;
    public static Scenario chosenScenario;

    public ScenarioPickerAdapter(ArrayList<Scenario> scenarios){
        this.scenarios = scenarios;
    }

    @NonNull
    @Override
    public ScenarioPickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_scenario, viewGroup, false);
        ScenarioPickerViewHolder vh = new ScenarioPickerViewHolder(view);
        vh.scenario_name = view.findViewById(R.id.scenario_name);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ScenarioPickerViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenScenario = scenarios.get(i);
                InstructorActivity.ratePreview.setText(String.valueOf(scenarios.get(i).getRate()));
                InstructorActivity.pressurePreview.setText(String.valueOf(scenarios.get(i).getPressure()));
                InstructorActivity.volumePreview.setText(String.valueOf(scenarios.get(i).getVolume()));
                InstructorActivity.pressurePreview1.setText(String.valueOf(scenarios.get(i).getPressureBar1()));
                InstructorActivity.pressurePreview2.setText(String.valueOf(scenarios.get(i).getPressureBar2()));
                InstructorActivity.ratePreview1.setText(String.valueOf(scenarios.get(i).getRateBar1()));
                InstructorActivity.ratePreview2.setText(String.valueOf(scenarios.get(i).getRateBar2()));
                InstructorActivity.airPreview.setText(String.valueOf(scenarios.get(i).getAir()));

                if(scenarios.get(i).isNozzle()) {
                    InstructorActivity.nozzlePreview.setText("In");
                } else {
                    InstructorActivity.nozzlePreview.setText("Not in");
                }
            }
        });
        viewHolder.scenario_name.setId(i);
        Scenario scenario = scenarios.get(i);
        viewHolder.scenario_name.setText(scenario.getName());

    }

    @Override
    public int getItemCount() {
        return scenarios.size();
    }

    public Scenario getChosenScenario(){
    return chosenScenario;
    }
}
