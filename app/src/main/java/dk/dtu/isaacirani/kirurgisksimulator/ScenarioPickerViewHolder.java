package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.activities.SurgeonActivity;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockData;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenatioList;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioPickerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    MockScenatioList scenatioList = new MockScenatioList();

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
