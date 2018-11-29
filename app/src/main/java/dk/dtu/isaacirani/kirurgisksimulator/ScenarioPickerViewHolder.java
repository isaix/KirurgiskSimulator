package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.activities.SurgeonActivity;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenarioList;

public class ScenarioPickerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    MockScenarioList scenatioList = new MockScenarioList();

    public TextView scenario_name;
    public android.widget.TableRow TableRow;

    public static int chosenScenario;

    public ScenarioPickerViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        chosenScenario = getAdapterPosition();

        SurgeonActivity.mockData.getStudents()[ViewHolder.chosenStudent].setScenario(scenatioList.getScenarios().get(getAdapterPosition()));

    }
}
