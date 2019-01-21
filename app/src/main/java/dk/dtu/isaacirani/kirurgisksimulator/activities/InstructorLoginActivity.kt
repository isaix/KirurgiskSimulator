package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import kotlinx.android.synthetic.main.activity_instructor_login.*


class InstructorLoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var view: View
    lateinit var snackbarisconnected: Snackbar
    lateinit var snackbarnotconnected: Snackbar
    lateinit var textView: TextView

    var group: GroupsRepository = GroupsRepository()
    var instructor: Instructor = Instructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_login)
        setSupportActionBar(findViewById(R.id.toolbar))

        enterSurgeonLogin.setOnClickListener(this)

        registerReceiver()

        view = findViewById(android.R.id.content)
        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE)
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT)
        view = snackbarnotconnected.view
        textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.RED)

        instructor.name = "tobias"
    }

    override fun onClick(v: View) {
        if (plain_text_input.text.isEmpty()){
            Snackbar.make(activity_instructor_login, "You must fill out a name", Snackbar.LENGTH_LONG).show()
        } else {
            startActivity(Intent(this, InstructorActivity::class.java).putExtra("instructorName", plain_text_input.text.toString()))
        }
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

    private var networkChangeReceiver = InternalNetworkChangeReceiver()

    internal inner class InternalNetworkChangeReceiver : BroadcastReceiver() {
        override fun onReceive(c: Context, i: Intent) {
            if (!i.getBooleanExtra("networkstatus", false)) {
                snackbarnotconnected.show()
            } else {
                if (snackbarnotconnected.isShown) {
                    snackbarnotconnected.dismiss()
                    snackbarisconnected.show()
                }
            }
        }
    }
}


