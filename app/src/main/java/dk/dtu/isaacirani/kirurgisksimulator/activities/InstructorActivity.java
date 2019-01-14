package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
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

    //nyt til BR
    View view;
    Snackbar snackbarnotconnected;
    Snackbar snackbarisconnected;

    public static TextView ratePreview, pressurePreview, volumePreview, nozzlePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        /*
        ratePreview = findViewById(R.id.RatePreviewValue);
        pressurePreview = findViewById(R.id.PressurePreviewValue);
        volumePreview = findViewById(R.id.VolumePreviewValue);
        nozzlePreview = findViewById(R.id.Nozzle);
        */

        l = findViewById(R.id.lin);

        mockData = new MockData();
        mockScenarioList = new MockScenarioList();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(mockData.getStudents());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnClickListener(this);


        //nyt BR
        registerReceiver();

        view = findViewById(android.R.id.content);

        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE);
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT);

        View snacknotconnectedview = snackbarnotconnected.getView();
        TextView textView = snacknotconnectedview.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);

        //nyt slut

        FirebaseDatabase.getInstance().getReference().child("Scenarios").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scenarioList.clear();
                for (DataSnapshot scenario : dataSnapshot.getChildren()) {
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
           /* case R.id.scenarios:
                intent = new Intent(this, SurgeonActivity.class);
                finish();
                startActivity(intent);
                break;*/

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        Scenario scenario = spAdapter.getChosenScenario();
        int chosenStudent = adapter.getChosenStudent();
        if (scenario != null && chosenStudent > 0) {
            mockData.getStudents()[chosenStudent].setScenario(scenario);
            adapter.notifyItemChanged(chosenStudent);
        }
    }

    public void loadRec() {
        scenarioPicker = findViewById(R.id.scenarioPicker);
        scenarioPicker.setAdapter(spAdapter);
        scenarioPicker.setHasFixedSize(false);
        scenarioPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        scenarioPicker.setOnClickListener(this);
        Log.e("tæst", scenarioList.size() + "");
    }


    //nyt BR

    private void registerReceiver() {

        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(NetworkChangeReceiver.NETWORK_CHANGE_ACTION);
            registerReceiver(networkChangeReceiver, filter);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void onDestroy() {

        try {
            unregisterReceiver(networkChangeReceiver);

        } catch (Exception e) {
            e.printStackTrace();

        } super.onDestroy();
    }

    InternalNetworkChangeReceiver networkChangeReceiver = new InternalNetworkChangeReceiver();
    class InternalNetworkChangeReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context c, Intent i) {


            if (i.getBooleanExtra("networkstatus", false) == false) {
                snackbarnotconnected.show();

            } else {
                if (snackbarnotconnected.isShown()){
                    snackbarnotconnected.dismiss();
                    snackbarisconnected.show();

                }
            }
        }
    }
}


