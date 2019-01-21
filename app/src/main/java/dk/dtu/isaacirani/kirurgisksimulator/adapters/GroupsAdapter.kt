package dk.dtu.isaacirani.kirurgisksimulator.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import kotlinx.android.synthetic.main.activity_student_login.view.*
import kotlinx.android.synthetic.main.group_item.view.*

class GroupsAdapter( var groups : ArrayList<Group>, var context: Context) : RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of groups in the list
    override fun getItemCount(): Int {
        return groups.size
    }

    //     Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.group_item, parent, false))
    }

    // Binds each group in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.title.text = groups[position].instructor.name
        holder.view.sub_title.text = "${groups[position].students.size} Students connected"
    }
}

class ViewHolder (var view: View) : RecyclerView.ViewHolder(view){}