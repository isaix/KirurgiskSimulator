package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.adapters.GroupAdapter
import dk.dtu.isaacirani.kirurgisksimulator.adapters.GroupsAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Student
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import kotlinx.android.synthetic.main.activity_join_group.*

class GroupManagerActivity : AppCompatActivity() {

    private val groupRepository = GroupsRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_manager)
        setSupportActionBar(findViewById(R.id.toolbar))


        groupRepository.loadGroups { groups -> loadRec(groups)}

    }

    private fun deleteGroup(groupId: String){
        groupRepository.deleteGroup(groupId)

    }

    private fun loadRec(groups: ArrayList<Group>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GroupAdapter(groups, this){ string -> deleteGroup(string)}
    }
}
