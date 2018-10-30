package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    //TODO: Test om det her virker. Det gør det nok ikke, så få det til at virke.
    public TextView ID, Name, Pressure, Rate, Volume, Nozzle;

    public ViewHolder(View itemView){
        super(itemView);
    }

}
