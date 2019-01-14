package dk.dtu.isaacirani.kirurgisksimulator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.models.Student;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<Student> students;
    int chosenStudent = -1;
    int backGround;


    public Adapter(ArrayList<Student> students){
        this.students = students;
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
        vh.RateBar1 = view.findViewById(R.id.Ratebar1);
        vh.RateBar2 = view.findViewById(R.id.Ratebar2);
        vh.Pressure = view.findViewById(R.id.Pressure);
        vh.PressureBar1 = view.findViewById(R.id.Pressurebar1);
        vh.PressureBar2 = view.findViewById(R.id.Pressurebar2);
        vh.Volume = view.findViewById(R.id.Volume);
        vh.Nozzle = view.findViewById(R.id.Nozzle);
        vh.Air = view.findViewById(R.id.Air);
        vh.checkButton = view.findViewById(R.id.checkButton);

        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.TableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ScenarioPickerAdapter.chosenScenario != null) {
                    students.get(i).setScenario(ScenarioPickerAdapter.chosenScenario);
                    notifyItemChanged(i);
                    chosenStudent = i;
                    backGround = R.drawable.redrecyclerviewtext;
                }
            }
        });
        viewHolder.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(i);
                backGround = R.drawable.recyclerviewtext;
                chosenStudent = i;
            }
        });

        if(chosenStudent == i){
            viewHolder.ID.setBackgroundResource(backGround);
            viewHolder.Name.setBackgroundResource(backGround);
            viewHolder.Pressure.setBackgroundResource(backGround);
            viewHolder.PressureBar1.setBackgroundResource(backGround);
            viewHolder.PressureBar2.setBackgroundResource(backGround);
            viewHolder.Rate.setBackgroundResource(backGround);
            viewHolder.RateBar1.setBackgroundResource(backGround);
            viewHolder.RateBar2.setBackgroundResource(backGround);
            viewHolder.Air.setBackgroundResource(backGround);
            viewHolder.Volume.setBackgroundResource(backGround);
            viewHolder.Nozzle.setBackgroundResource(backGround);
        }

        Student student = students.get(i);
        viewHolder.TableRow.setId(i);
        viewHolder.ID.setText(String.valueOf(i+1));
        viewHolder.Name.setText(student.getName());
        viewHolder.Pressure.setText(String.valueOf(student.getScenario().getPressure()));
        viewHolder.PressureBar1.setText(String.valueOf(student.getScenario().getPressureBar1()));
        viewHolder.PressureBar2.setText(String.valueOf(student.getScenario().getPressureBar2()));
        viewHolder.Rate.setText(String.valueOf(student.getScenario().getRate()));
        viewHolder.RateBar1.setText(String.valueOf(student.getScenario().getRateBar1()));
        viewHolder.RateBar2.setText(String.valueOf(student.getScenario().getRateBar2()));
        viewHolder.Air.setText(String.valueOf(student.getScenario().getAir()));
        viewHolder.Volume.setText(Double.toString(student.getScenario().getVolume()).substring(0, 3));
        if(student.getScenario().isNozzle()){
            viewHolder.Nozzle.setText("In");
        } else {
            viewHolder.Nozzle.setText("Not In");
        }



    }

    @Override
    public int getItemCount() {
        return students.size();
    }

}
