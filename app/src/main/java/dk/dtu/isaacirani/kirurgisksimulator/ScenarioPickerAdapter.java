package dk.dtu.isaacirani.kirurgisksimulator;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.activities.SurgeonActivity;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioPickerAdapter extends RecyclerView.Adapter<ScenarioPickerViewHolder> {
    ArrayList<Scenario> scenarios;
    static Scenario chosenScenario;

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
                SurgeonActivity.ratePreview.setText(String.valueOf(scenarios.get(i).getRate()));
                SurgeonActivity.pressurePreview.setText(String.valueOf(scenarios.get(i).getPressure()));
                SurgeonActivity.volumePreview.setText(String.valueOf(scenarios.get(i).getVolume()));
                SurgeonActivity.nozzlePreview.setText(String.valueOf(scenarios.get(i).isNozzle()));
            }
        });
        viewHolder.scenario_name.setId(i);
        if(i % 2 == 0){
            viewHolder.scenario_name.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
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
