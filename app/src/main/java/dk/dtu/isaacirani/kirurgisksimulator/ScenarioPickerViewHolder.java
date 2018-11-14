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

    public TextView Pressure, Rate, Volume, Nozzle;
    public android.widget.TableRow TableRow;

    static Scenario updateScenatio;

    public static int chosenScenario;
    int chosenStudent;

    public ScenarioPickerViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        chosenScenario = getAdapterPosition();

        boolean nozzle;
        if(Nozzle.getText().equals("In")){
            nozzle = true;
        } else {
            nozzle = false;
        }

        int pressure = Integer.parseInt(Rate.getText().toString());
        int rate = Integer.parseInt(Pressure.getText().toString());
        Double volume = Double.parseDouble(Volume.getText().toString());

        SurgeonActivity.mockData.getStudents()[ViewHolder.chosenStudent].setVolume(volume);
        SurgeonActivity.mockData.getStudents()[ViewHolder.chosenStudent].setRate(rate);
        SurgeonActivity.mockData.getStudents()[ViewHolder.chosenStudent].setNozzle(nozzle);
        SurgeonActivity.mockData.getStudents()[ViewHolder.chosenStudent].setPressure(pressure);

    }
}
