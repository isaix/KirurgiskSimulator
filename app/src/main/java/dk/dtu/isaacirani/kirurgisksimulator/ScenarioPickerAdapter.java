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
        vh.scenario_name = view.findViewById(R.id.scenario_name);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ScenarioPickerViewHolder viewHolder, int i) {
        viewHolder.TableRow.setId(i);
        Scenario scenario = scenarios.get(i);
        viewHolder.scenario_name.setText(scenario.getName());
    }

    @Override
    public int getItemCount() {
        return scenarios.size();
    }
}
