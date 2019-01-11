package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.dtu.isaacirani.kirurgisksimulator.models.Student;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    Student[] data;
    int chosenStudent = -1;

    public Adapter(Student[] data){
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        vh.TableRow = view.findViewById(R.id.TableRow);
        vh.ID = view.findViewById(R.id.ID);
        vh.Name = view.findViewById(R.id.Name);
        vh.Rate = view.findViewById(R.id.Rate);
        vh.Pressure = view.findViewById(R.id.Pressure);
        vh.Volume = view.findViewById(R.id.Volume);
        vh.Nozzle = view.findViewById(R.id.Nozzle);

        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data[i].setScenario(ScenarioPickerAdapter.chosenScenario);
                Log.e("Tæst", ""+i);
                notifyItemChanged(i);
            }
        });
        Student student = data[i];
        viewHolder.TableRow.setId(i);
        viewHolder.ID.setText(String.valueOf(student.getId()));
        viewHolder.Name.setText(student.getName());
        viewHolder.Pressure.setText(String.valueOf(student.getScenario().getPressure()));
        viewHolder.Rate.setText(String.valueOf(student.getScenario().getRate()));
        viewHolder.Volume.setText(Double.toString(student.getScenario().getVolume()).substring(0, 3));
        if(student.getScenario().isNozzle()){
            viewHolder.Nozzle.setText("In");
        } else {
            viewHolder.Nozzle.setText("Not In");
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public int getChosenStudent(){
        return chosenStudent;
    }
}
