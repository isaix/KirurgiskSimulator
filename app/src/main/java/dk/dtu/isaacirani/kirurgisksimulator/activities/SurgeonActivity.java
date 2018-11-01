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

import dk.dtu.isaacirani.kirurgisksimulator.Adapter;
import dk.dtu.isaacirani.kirurgisksimulator.R;
import dk.dtu.isaacirani.kirurgisksimulator.models.MockData;

public class SurgeonActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon);

        recyclerView = findViewById(R.id.recyclerView);
        Adapter adapter = new Adapter(new MockData().getStudents());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                intent = new Intent(this, SurgeonActivity.class);
                finish();
                startActivity(intent);
                break;
            
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}