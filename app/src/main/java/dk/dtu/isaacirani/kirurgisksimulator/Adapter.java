package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    Student[] data;

    public Adapter(Student[] data){
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        vh.ID = view.findViewById(R.id.ID);
        vh.Name = view.findViewById(R.id.Name);
        vh.Rate = view.findViewById(R.id.Rate);
        vh.Pressure = view.findViewById(R.id.Pressure);
        vh.Volume = view.findViewById(R.id.Volume);
        vh.Nozzle = view.findViewById(R.id.Nozzle);
        return  vh;
    }

    //TODO: Opdater metode så den indsætter elementer i tablerow. Sæt overskrift på table på en måde.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Student student = data[i];
        viewHolder.ID.setText(String.valueOf(student.getID()));
        viewHolder.Name.setText(student.getName());
        viewHolder.Pressure.setText(String.valueOf(student.getPressure()));
        viewHolder.Rate.setText(String.valueOf(student.getRate()));
        viewHolder.Volume.setText(student.getVolume().toString().substring(0, 2));
        if(student.isNozzle()){
            viewHolder.Nozzle.setText("In");
        } else {
            viewHolder.Nozzle.setText("Not In");
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
