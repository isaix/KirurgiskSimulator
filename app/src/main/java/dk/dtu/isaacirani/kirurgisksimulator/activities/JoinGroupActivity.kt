package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.adapters.GroupsAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Student
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import kotlinx.android.synthetic.main.activity_join_group.*
import kotlinx.android.synthetic.main.activity_student_login.*

class JoinGroupActivity : AppCompatActivity() {

    lateinit var display: DisplayMetrics
    internal var width: Int = 0
    internal var scale: Float = 0.toFloat()

    private val groupRepository = GroupsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_group)
        setSupportActionBar(findViewById(R.id.toolbar))

        val widthscreen: Float

        display = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(display)
        width = display.widthPixels
        scale = display.density
        widthscreen = width / scale

        if (widthscreen <= 600) {
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }


        groupRepository.loadGroups { groups -> loadRec(groups)}


        registerReceiver()


    }

    private fun joinGroup(groupId: String){
        groupRepository.addStudentToGroup(groupId, Student(name = intent.getStringExtra("studentName"))){studentId ->
            startActivity(Intent(this, SimulatorActivity::class.java)
                    .putExtra("studentId", studentId)
                    .putExtra("groupId", groupId))

        }

    }

    private fun loadRec(groups: ArrayList<Group>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GroupsAdapter(groups, this){ string -> joinGroup(string)}
    }


    private fun registerReceiver() {

        try {
            val filter = IntentFilter()
            filter.addAction(NetworkChangeReceiver.NETWORK_CHANGE_ACTION)
            registerReceiver(networkChangeReceiver, filter)

        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    override fun onDestroy() {

        try {
            unregisterReceiver(networkChangeReceiver)

        } catch (e: Exception) {
            e.printStackTrace()

        }

        super.onDestroy()
    }


    internal var networkChangeReceiver = InternalNetworkChangeReceiver()

    internal inner class InternalNetworkChangeReceiver : BroadcastReceiver() {


        override fun onReceive(c: Context, i: Intent) {
            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE)

            if (!i.getBooleanExtra("networkstatus", false)) {
                snackbar.show()

            } else {
                if (snackbar.isShown) {
                    snackbar.dismiss()
                    Snackbar.make(findViewById(android.R.id.content), "Device is connected to internet", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}


