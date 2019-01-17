package dk.dtu.isaacirani.kirurgisksimulator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import dk.dtu.isaacirani.kirurgisksimulator.activities.InstructorActivity
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor


class InstructorLoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var surgeonEnterLogin: Button
    lateinit var surgeon: Intent


    lateinit var view: View
    lateinit var snackbarisconnected: Snackbar
    lateinit var snackbarnotconnected: Snackbar
    lateinit var textView: TextView

    var group:GroupRepository = GroupRepository()
    var instructor: Instructor = Instructor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_login)

        surgeonEnterLogin = findViewById(R.id.enterSurgeonLogin)
        surgeonEnterLogin.setOnClickListener(this)



        surgeon = Intent(this, InstructorActivity::class.java)



        registerReceiver()

        view = findViewById(android.R.id.content)
        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE)
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT)
        view = snackbarnotconnected.view
        textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.RED)

        //instructor.id_i = group.createGroupWithoutStudents(instructor)
        instructor.name = "tobias"
        //Log.e("IDINTENT", instructor.id_i)
        //surgeon.putExtra("instructorID", instructor.id_i)
        //surgeon.putExtra("instructorID", "-LWQO2j41v1ZhIUOx-xq")
        surgeon.putExtra("instructorID", "-LWQO2j41v1ZhlUOx-xq")


    }

    override fun onClick(v: View) {
        startActivity(surgeon)
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


            if (i.getBooleanExtra("networkstatus", false) == false) {
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


