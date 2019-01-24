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
import kotlinx.android.synthetic.main.activity_student_login.*
import kotlinx.android.synthetic.main.activity_student_login.view.*
import kotlinx.android.synthetic.main.group_item.view.*
import kotlinx.android.synthetic.main.group_item_delete.view.*
import javax.security.auth.callback.Callback

class GroupAdapter( var groups : ArrayList<Group>, var context: Context, val callback: (String) -> Unit) : RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of groups in the list
    override fun getItemCount(): Int {
        return groups.size
    }

    //     Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.group_item_delete, parent, false))
    }

    // Binds each group in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.group_title_delete.text = groups[position].instructor.name
        holder.view.group_sub_title_delete.text = "${groups[position].students.size} Students connected"
        holder.view.group_delete_button.setOnClickListener {

            callback(groups[position].id)
        }
    }
}

