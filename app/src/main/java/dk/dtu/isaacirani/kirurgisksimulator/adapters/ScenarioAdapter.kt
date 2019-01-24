package dk.dtu.isaacirani.kirurgisksimulator.adapters

import android.content.ComponentCallbacks
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.R.id.student_text_input
import dk.dtu.isaacirani.kirurgisksimulator.activities.JoinGroupActivity
import dk.dtu.isaacirani.kirurgisksimulator.activities.SimulatorActivity
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario
import kotlinx.android.synthetic.main.activity_student_login.*
import kotlinx.android.synthetic.main.activity_student_login.view.*
import kotlinx.android.synthetic.main.group_item.view.*
import kotlinx.android.synthetic.main.scenario_item.view.*
import javax.security.auth.callback.Callback

class ScenarioAdapter(var scenarios : ArrayList<Scenario>, var context: Context, val callback: (String) -> Unit) : RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of groups in the list
    override fun getItemCount(): Int {
        return scenarios.size
    }

    //     Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.scenario_item, parent, false))
    }

    // Binds each group in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.scenario_title.text = scenarios[position].name
        //holder.view.sub_title.text = "${groups[position].students.size} Students connected"
        holder.view.delete_button.setOnClickListener {

            callback(scenarios[position].id)
        }
    }
}

