package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.pm.ActivityInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.adapters.LogDataAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.repositories.LogRepository;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.models.LogEntry;
import dk.dtu.isaacirani.kirurgisksimulator.models.LogList;

public class LogDataActivity extends AppCompatActivity  {
    LinearLayout linearLayoutLog;
    RecyclerView recyclerViewLogData;
    private DrawerLayout drawer;
    LogDataAdapter logDataAdapter;
    LogList logList = new LogList();
    ArrayList<LogEntry> logs = new ArrayList<LogEntry>();
    LogRepository logRepo;
    DisplayMetrics display;

    //nyt til BR
    View view;
    public static TextView logName, logScenarioName, logDate,logTime, logFailures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        setSupportActionBar(findViewById(R.id.toolbar));

        display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        logRepo = new LogRepository();
        logRepo.loadLogs(logs -> {loadLogs(logs); return null;});


        logName = findViewById(R.id.logName);
        logScenarioName = findViewById(R.id.logScenarioName);
        logDate = findViewById(R.id.logDate);
        logTime = findViewById(R.id.logTime);
        logFailures = findViewById(R.id.logFailures);


        view = findViewById(android.R.id.content);

        linearLayoutLog = findViewById(R.id.linearLayoutLog);
        //nyt slut
        recyclerViewLogData = findViewById(R.id.recyclerViewLogData);

    }

    public void loadLogs(ArrayList<LogEntry> logs){
        logDataAdapter = new LogDataAdapter(logs);
        recyclerViewLogData.setAdapter(logDataAdapter);
        recyclerViewLogData.setHasFixedSize(false);
        recyclerViewLogData.setLayoutManager(new LinearLayoutManager(this));
    }


}