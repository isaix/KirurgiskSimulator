package dk.dtu.isaacirani.kirurgisksimulator.fragments


import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_scenario_dialogue.*
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 *
 */
class ScenarioDialogueFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scenario_dialogue, container, false)
    }


    fun loadRec(scenarios: ArrayList<Scenario>) {
        scenarioPicker.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        scenarioPicker.adapter = ScenarioPickerAdapter(scenarios)
    }

}
