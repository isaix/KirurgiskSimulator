package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.models.LogList;

public class LogDataViewHolder extends RecyclerView.ViewHolder {
    LogList logList = new LogList();

    public TextView logData_name;
    public TextView logData_scenarioName;
    public TextView logData_date;
    public TextView logData_time;
    public TextView logData_failures;
    public android.widget.TableRow logTableRow;

    public LogDataViewHolder(View itemView) {
        super(itemView);
    }
}
