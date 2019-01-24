package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.adapters.StudentAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.adapters.ScenarioPickerAdapter;
import dk.dtu.isaacirani.kirurgisksimulator.models.Group;
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor;
import dk.dtu.isaacirani.kirurgisksimulator.models.LogEntry;
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository;
import dk.dtu.isaacirani.kirurgisksimulator.repositories.LogRepository;
import dk.dtu.isaacirani.kirurgisksimulator.repositories.ScenarioRepository;

public class InstructorActivity extends AppCompatActivity {
    LinearLayout l;
    RecyclerView recyclerView, scenarioPicker;
    StudentAdapter adapter;
    ArrayList<Scenario> scenarioList = new ArrayList<>();
    ScenarioPickerAdapter spAdapter;
    TextView scenariosavaliable;
    String scenariosavailableString;
    public static String groupID;
    TextView noStudents;
    MediaPlayer mediaPlayer;

    SparseArray<LogEntry> logEntries;
    SparseArray<Long> startTimes;
    SparseArray<Long> finishTimes;


    View view;
    Snackbar snackbarnotconnected;
    Snackbar snackbarisconnected;
    ProgressBar loadingIcon;
    DisplayMetrics display;


    public static TextView ratePreview, pressurePreview, volumePreview, nozzlePreview, airPreview, pressurePreview1, pressurePreview2, ratePreview1, ratePreview2;
    GroupsRepository groupRepository = new GroupsRepository();
    ScenarioRepository scenarioRepository = new ScenarioRepository();
    LogRepository logRepository = new LogRepository();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        if(savedInstanceState == null){
            // everything else that doesn't update UI
            display = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(display);

            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            recyclerView = findViewById(R.id.recyclerView);
            setSupportActionBar(findViewById(R.id.toolbar));
            mediaPlayer = MediaPlayer.create(this, R.raw.turnon);
            mediaPlayer.start();

            int currentOrientation = this.getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_PORTRAIT){
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            logEntries = new SparseArray<>();
            startTimes = new SparseArray<>();
            finishTimes = new SparseArray<>();

            Log.e("Instructor Name", getIntent().getStringExtra("instructorName"));

            groupRepository.createGroupWithoutStudents(new Instructor(getIntent().getStringExtra("instructorName")), (String groupId) -> {
                groupID = groupId;
                Log.e("ID", groupId);
                groupRepository.loadGroup(groupId, group -> {
                    if (!(group == null)){
                        Log.e("ID2", group.getId());
                        Log.e("Students", group.getStudents().size() + "");
                        createAdapter(group);
                    }

                    return null;
                });
                return null;
            });

            scenarioRepository.loadScenarios(scenarios -> {
                if (!(scenarios == null)){
                    loadRec(scenarios);
                }
                return null;
            });


            airPreview = findViewById(R.id.airPreview);
            ratePreview = findViewById(R.id.ratePreview);
            pressurePreview = findViewById(R.id.pressurePreview);
            volumePreview = findViewById(R.id.volumePreview);
            nozzlePreview = findViewById(R.id.nozzlePreview);
            pressurePreview1 = findViewById(R.id.pressureBar1Preview);
            pressurePreview2 = findViewById(R.id.pressureBar2Preview);
            ratePreview1 = findViewById(R.id.rateBar1Preview);
            ratePreview2 = findViewById(R.id.rateBar2Preview);

            noStudents = findViewById(R.id.nostudents);


            loadingIcon = findViewById(R.id.instructorLoadingIcon);

            scenariosavaliable = findViewById(R.id.scenariosavaliable);
            scenariosavailableString = "  Avaliable Scenarios  ";
            SpannableString spannableString = new SpannableString(scenariosavailableString);
            spannableString.setSpan(new RelativeSizeSpan(2f), 0, 1, 0);
            spannableString.setSpan(new RelativeSizeSpan(2f), scenariosavailableString.length() - 1, scenariosavailableString.length() - 0, 0);
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

        }



    }


    public void loadRec(ArrayList<Scenario> scenarios) {
        scenarioPicker = findViewById(R.id.scenarioPicker);
        spAdapter = new ScenarioPickerAdapter(scenarios);
        scenarioPicker.setAdapter(spAdapter);
        scenarioPicker.setHasFixedSize(false);
        scenarioPicker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loadingIcon.setVisibility(View.GONE);
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
    if(!(groupID == null)) {
        groupRepository.deleteGroup(groupID);
    }

        try {
            unregisterReceiver(networkChangeReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    void createAdapter(Group group) {

        adapter = new StudentAdapter(group.getStudents(), this);
        Log.e("rip", adapter.getItemCount() + "");
        if(adapter.getItemCount() > 0){
            noStudents.setVisibility(View.INVISIBLE);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void registerStartTime(LogEntry newEntry, int i, Long startTime){
        if(logEntries.get(i) != null){
            logEntries.get(i).setTime(-1);
            logRepository.addLog(logEntries.get(i));
        }
        logEntries.put(i, newEntry);
        startTimes.put(i, startTime);
    }

    public void registerFinishTime(int i, Long finishTime){
        if(logEntries.get(i) != null){
            finishTimes.put(i, finishTime);
            logEntries.get(i).setTime((int) (finishTime / startTimes.get(i))/1000);
            logRepository.addLog(logEntries.get(i));
        }
        logEntries.put(i, null);
    }

    public void incrementFailures(int i){
        if(logEntries.get(i) != null){
            logEntries.get(i).setFailures(logEntries.get(i).getFailures() + 1);
        }
    }

    InternalNetworkChangeReceiver networkChangeReceiver = new InternalNetworkChangeReceiver();

    class InternalNetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context c, Intent i) {


            if (i.getBooleanExtra("networkstatus", false) == false) {
                snackbarnotconnected.show();

            } else {
                if (snackbarnotconnected.isShown()) {
                    snackbarnotconnected.dismiss();
                    snackbarisconnected.show();
                }
            }
        }
    }


}
