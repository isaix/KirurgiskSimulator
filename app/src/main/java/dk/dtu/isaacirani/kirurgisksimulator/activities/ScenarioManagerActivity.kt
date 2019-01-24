package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.adapters.GroupsAdapter
import dk.dtu.isaacirani.kirurgisksimulator.adapters.ScenarioAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario
import dk.dtu.isaacirani.kirurgisksimulator.models.Student
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import dk.dtu.isaacirani.kirurgisksimulator.repositories.ScenarioRepository
import kotlinx.android.synthetic.main.activity_join_group.*

class ScenarioManagerActivity : AppCompatActivity() {
    private val scenarioRepository = ScenarioRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenario_manager)
        setSupportActionBar(findViewById(R.id.toolbar))



        scenarioRepository.loadScenarios { scenarios -> loadRec(scenarios)}

    }

    private fun deleteScenario(scenarioId: String){
        Log.e("LOCATION", "inside deleteScenario")
        scenarioRepository.deleteScenario(scenarioId)

    }

    private fun loadRec(scenarios: ArrayList<Scenario>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ScenarioAdapter(scenarios, this){ string -> deleteScenario(string)}
    }
}
