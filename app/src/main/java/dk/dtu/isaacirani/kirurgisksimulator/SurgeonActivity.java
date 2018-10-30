package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SurgeonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    //TODO: Lav bedre mock data. Lav Javaklasse med data tilsvarende simulatoren.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon);

        recyclerView = findViewById(R.id.recyclerView);
        Adapter adapter = new Adapter(new MockData().getStudents());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
