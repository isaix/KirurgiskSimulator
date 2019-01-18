package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
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
import dk.dtu.isaacirani.kirurgisksimulator.GroupRepository;
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioPickerAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Group;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockData;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockScenarioList;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;
import dk.dtu.isaacirani.kirurgisksimulator.models.Student;

public class InstructorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout l;
    RecyclerView recyclerView, scenarioPicker;
    private DrawerLayout drawer;
    Adapter adapter;
    ArrayList<Scenario> scenarioList = new ArrayList<>();
    ScenarioPickerAdapter spAdapter;
    TextView scenariosavaliable;
    String scenariosavailableString;

    View view;
    Snackbar snackbarnotconnected;
    Snackbar snackbarisconnected;

    public static TextView ratePreview, pressurePreview, volumePreview, nozzlePreview, airPreview, pressurePreview1, pressurePreview2, ratePreview1, ratePreview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        GroupRepository groupRepository = new GroupRepository();

        groupRepository.loadGroup(getIntent().getStringExtra("instructorID") ,group -> {createAdapter(group); return null;});




        airPreview = findViewById(R.id.airPreview);
        ratePreview = findViewById(R.id.ratePreview);
        pressurePreview = findViewById(R.id.pressurePreview);
        volumePreview = findViewById(R.id.volumePreview);
        nozzlePreview = findViewById(R.id.nozzlePreview);
        pressurePreview1 = findViewById(R.id.pressureBar1Preview);
        pressurePreview2 = findViewById(R.id.pressureBar2Preview);
        ratePreview1 = findViewById(R.id.rateBar1Preview);
        ratePreview2 = findViewById(R.id.rateBar2Preview);

        scenariosavaliable = findViewById(R.id.scenariosavaliable);
        scenariosavailableString = "⇦ Avaliable Scenarios ⇨";
        SpannableString spannableString = new SpannableString(scenariosavailableString);
        spannableString.setSpan(new RelativeSizeSpan(2f), 0, 1, 0);
        spannableString.setSpan(new RelativeSizeSpan(2f), scenariosavailableString.length()-1, scenariosavailableString.length()-0, 0);
        scenariosavaliable.setText(spannableString);




        l = findViewById(R.id.lin);

        //nyt BR
        registerReceiver();
        view = findViewById(android.R.id.content);
        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE);
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT);
        View snacknotconnectedview = snackbarnotconnected.getView();
        TextView textView = snacknotconnectedview.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);



        recyclerView = findViewById(R.id.recyclerView);
       // adapter = new Adapter(mockData.getStudents());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
           /* case R.id.settings:
                intent = new Intent(this, SettingsActivity.class);
                finish();
                startActivity(intent);
                break; */
            case R.id.about:
                intent = new Intent(this, SimulatorActivity.class);
                finish();
                startActivity(intent);
                break;
           /* case R.id.scenarios:
                intent = new Intent(this, InstructorActivity.class);
                finish();
                startActivity(intent);
                break; */


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadRec(){
        scenarioPicker = findViewById(R.id.scenarioPicker);
        scenarioPicker.setAdapter(spAdapter);
        scenarioPicker.setHasFixedSize(false);
        scenarioPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Log.e("tæst", scenarioList.size() + "");
    }



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


    void createAdapter(Group group){
        adapter = new Adapter(group.getStudents());
        Log.e("IDINTENT", getIntent().getStringExtra("instructorID"));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
