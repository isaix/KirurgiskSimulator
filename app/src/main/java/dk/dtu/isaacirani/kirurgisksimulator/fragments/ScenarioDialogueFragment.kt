package dk.dtu.isaacirani.kirurgisksimulator.fragments


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.adapters.ScenarioPickerAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario
import dk.dtu.isaacirani.kirurgisksimulator.repositories.ScenarioRepository
import kotlinx.android.synthetic.main.fragment_scenario_dialogue.*
import kotlinx.android.synthetic.main.fragment_scenario_dialogue.view.*
import kotlinx.android.synthetic.main.group_item.*
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 *
 */
class ScenarioDialogueFragment : DialogFragment() {
    private var scenarioRepository = ScenarioRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.fragment_scenario_dialogue, container, false)
        scenarioRepository.loadScenarios { scenarios ->
            view.scenarioPicker.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            view.scenarioPicker.adapter = ScenarioPickerAdapter(scenarios)
        }

        this.dialog.setTitle("Scenarios")

        // Inflate the layout for this fragment
        return view
    }


    private fun loadRec(scenarios: ArrayList<Scenario>) {
        scenarioPicker.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        scenarioPicker.adapter = ScenarioPickerAdapter(scenarios)
    }

}
