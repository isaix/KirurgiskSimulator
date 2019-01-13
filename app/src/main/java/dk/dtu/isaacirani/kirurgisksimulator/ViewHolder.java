package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView ID, Name, Pressure, Rate, Volume, Nozzle, PressureBar1, PressureBar2, RateBar1, RateBar2, Air;
    public Button checkButton;
    public TableRow TableRow;

    public static int chosenStudent;

    public ViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        chosenStudent = getAdapterPosition();
        Log.e("Chosen student", String.valueOf(chosenStudent));
    }
}
