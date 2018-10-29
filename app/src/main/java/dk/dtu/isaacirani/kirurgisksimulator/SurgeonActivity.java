package dk.dtu.isaacirani.kirurgisksimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SurgeonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] data = {"TEMP", "DATA", "TEST", "DEMO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon);

        recyclerView = findViewById(R.id.recyclerView);
        Adapter adapter = new Adapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
