package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView ID, Name, Pressure, Rate, Volume, Nozzle;
    public TableRow TableRow;

    public ViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.v("GØR NOGET","GØR NOGET" + (getAdapterPosition() + 1));
    }
}
