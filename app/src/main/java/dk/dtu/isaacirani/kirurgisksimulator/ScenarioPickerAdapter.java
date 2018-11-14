package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioPickerAdapter extends RecyclerView.Adapter<ScenarioPickerViewHolder> {
    ArrayList<Scenario> scenarios;

    public ScenarioPickerAdapter(ArrayList<Scenario> scenarios){
        this.scenarios = scenarios;
    }

    @NonNull
    @Override
    public ScenarioPickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_scenario, viewGroup, false);
        ScenarioPickerViewHolder vh = new ScenarioPickerViewHolder(view);
        vh.TableRow = view.findViewById(R.id.scenario_tablerow);
        vh.Rate = view.findViewById(R.id.scenario_rate);
        vh.Pressure = view.findViewById(R.id.scenario_pressure);
        vh.Volume = view.findViewById(R.id.scenario_volume);
        vh.Nozzle = view.findViewById(R.id.scenario_nozzle);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ScenarioPickerViewHolder viewHolder, int i) {
        viewHolder.TableRow.setId(i);
        Scenario scenario = scenarios.get(i);
        viewHolder.Rate.setText(String.valueOf(scenario.getRate()));
        viewHolder.Pressure.setText(String.valueOf(scenario.getPressure()));
        viewHolder.Volume.setText(String.valueOf(scenario.getVolume()));
        if(scenario.isNozzle()){
            viewHolder.Nozzle.setText("In");
        } else {
            viewHolder.Nozzle.setText("Not In");
        }
    }

    @Override
    public int getItemCount() {
        return scenarios.size();
    }
}
