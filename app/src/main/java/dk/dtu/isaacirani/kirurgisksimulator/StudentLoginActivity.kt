package dk.dtu.isaacirani.kirurgisksimulator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.hardware.input.InputManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.TextView

import dk.dtu.isaacirani.kirurgisksimulator.activities.SimulatorActivity
import kotlinx.android.synthetic.main.activity_student_login.*

class StudentLoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var simulator: Intent
    lateinit var enterStudentLogin: Button

    lateinit var view: View
    lateinit var snackbarisconnected: Snackbar
    lateinit var snackbarnotconnected: Snackbar
    lateinit var textView: TextView
    lateinit var textInputLayout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        enterStudentLogin = findViewById(R.id.enterStudentLogin)
        enterStudentLogin.setOnClickListener(this)

        simulator = Intent(this, SimulatorActivity::class.java)

        registerReceiver()

        view = findViewById(android.R.id.content)
        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE)
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT)
        view = snackbarnotconnected.view
        textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.RED)

    }

    override fun onClick(v: View) {
        startActivity(simulator)
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


