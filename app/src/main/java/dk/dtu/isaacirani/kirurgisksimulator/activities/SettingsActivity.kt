package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_settings.*
import android.support.v4.view.GravityCompat
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.R.id.drawer
import android.widget.Toast
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.support.design.widget.NavigationView
import android.util.Log
import android.view.View
import dk.dtu.isaacirani.kirurgisksimulator.GroupAdapter
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import dk.dtu.isaacirani.kirurgisksimulator.models.MockData
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario

class SettingsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var nv: NavigationView
    var groupAdapter = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var scenarioAdapter = ScenarioAdapter()


        //groupAdapter.createGroup(Instructor(12, "bølle Bob"), MockData().students.toMutableList())
        scenarioAdapter.createScenario(Scenario("name",1, 1, 2.0, true))
        Log.e("scenarios", scenarioAdapter.scenarioList.toString())

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }


    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when (item.itemId) {
            R.id.settings -> {
                intent = Intent(this, SettingsActivity::class.java)
                finish()
                startActivity(intent)
            }
            R.id.about -> {
                intent = Intent(this, SimulatorActivity::class.java)
                finish()
                startActivity(intent)
            }
            R.id.scenarios -> {
                intent = Intent(this, SurgeonActivity::class.java)
                finish()
                startActivity(intent)
            }

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }






}
