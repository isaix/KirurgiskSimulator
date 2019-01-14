package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.activities.InstructorActivity;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenarioList;

public class ScenarioPickerViewHolder extends RecyclerView.ViewHolder {

    MockScenarioList scenatioList = new MockScenarioList();

    public TextView scenario_name;
    public android.widget.TableRow TableRow;

    public ScenarioPickerViewHolder(View itemView){
        super(itemView);
    }

}
