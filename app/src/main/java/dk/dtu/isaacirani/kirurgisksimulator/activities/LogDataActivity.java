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
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.Adapter;
import dk.dtu.isaacirani.kirurgisksimulator.GroupRepository;
import dk.dtu.isaacirani.kirurgisksimulator.LogDataAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioPickerAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Group;
import dk.dtu.isaacirani.kirurgisksimulator.models.LogEntry;
import dk.dtu.isaacirani.kirurgisksimulator.models.LogList;

public class LogDataActivity extends AppCompatActivity  {
    LinearLayout linearLayoutLog;
    RecyclerView recyclerViewLogData;
    private DrawerLayout drawer;
    LogDataAdapter logDataAdapter;
    LogList logList = new LogList();

    //nyt til BR
    View view;
    public static TextView logName, logScenarioName, logDate,logTime, logFailures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


        logName = findViewById(R.id.logName);
        logScenarioName = findViewById(R.id.logScenarioName);
        logDate = findViewById(R.id.logDate);
        logTime = findViewById(R.id.logTime);
        logFailures = findViewById(R.id.logFailures);

        logDataAdapter = new LogDataAdapter(logList.getLogData());
        view = findViewById(android.R.id.content);

        linearLayoutLog = findViewById(R.id.linearLayoutLog);
        //nyt slut
        recyclerViewLogData = findViewById(R.id.recyclerViewLogData);
        recyclerViewLogData.setAdapter(logDataAdapter);
        recyclerViewLogData.setHasFixedSize(false);
        recyclerViewLogData.setLayoutManager(new LinearLayoutManager(this));


    }


}