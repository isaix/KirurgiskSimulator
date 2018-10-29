package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ViewHolder(View itemView){
        super(itemView);
        textView = (TextView) itemView;
    }
}
