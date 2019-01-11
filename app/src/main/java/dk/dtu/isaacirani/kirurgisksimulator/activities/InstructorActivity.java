package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.Adapter;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioPickerAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.ViewHolder;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockData;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenarioList;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class InstructorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    LinearLayout l;
    RecyclerView recyclerView, scenarioPicker;
    private DrawerLayout drawer;
    Adapter adapter;
    public static MockData mockData;
    MockScenarioList mockScenarioList;
    ArrayList<Scenario> scenarioList = new ArrayList<>();
    ScenarioPickerAdapter spAdapter;

    public static TextView ratePreview, pressurePreview, volumePreview, nozzlePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon);

        ratePreview = findViewById(R.id.RatePreviewValue);
        pressurePreview = findViewById(R.id.PressurePreviewValue);
        volumePreview = findViewById(R.id.VolumePreviewValue);
        nozzlePreview = findViewById(R.id.Nozzle);

        l = findViewById(R.id.lin);

        mockData = new MockData();
        mockScenarioList = new MockScenarioList();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(mockData.getStudents());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnClickListener(this);
        FirebaseDatabase.getInstance().getReference().child("Scenarios").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scenarioList.clear();
                for (DataSnapshot scenario : dataSnapshot.getChildren()){
                    scenarioList.add(scenario.getValue(Scenario.class));

                }
                spAdapter = new ScenarioPickerAdapter(scenarioList);
                loadRec();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        l.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.settings:
                intent = new Intent(this, SettingsActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.about:
                intent = new Intent(this, SimulatorActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.scenarios:
                intent = new Intent(this, InstructorActivity.class);
                finish();
                startActivity(intent);
                break;
            
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        Scenario scenario = spAdapter.getChosenScenario();
        int chosenStudent = adapter.getChosenStudent();
        if(scenario != null && chosenStudent > 0) {
            mockData.getStudents()[chosenStudent].setScenario(scenario);
            adapter.notifyItemChanged(chosenStudent);
        }
    }

    public void loadRec(){
        scenarioPicker = findViewById(R.id.scenarioPicker);
        scenarioPicker.setAdapter(spAdapter);
        scenarioPicker.setHasFixedSize(false);
        scenarioPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        scenarioPicker.setOnClickListener(this);
        Log.e("tæst", scenarioList.size()+"");
    }
}
