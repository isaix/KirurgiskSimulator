package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.net.ConnectivityManagerCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.R.layout.activity_student_login
import dk.dtu.isaacirani.kirurgisksimulator.adapters.GroupsAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import kotlinx.android.synthetic.main.activity_instructor_login.*
import kotlinx.android.synthetic.main.activity_student_login.*

class StudentLoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var view: View
    lateinit var snackbarisconnected: Snackbar
    lateinit var snackbarnotconnected: Snackbar
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
        setSupportActionBar(findViewById(R.id.toolbar))

        noSoftKeyBoard()
        enterStudentLogin.setOnClickListener(this)
        registerReceiver()

        view = findViewById(android.R.id.content)
        snackbarnotconnected = Snackbar.make(view, "Device is not connected to internet", Snackbar.LENGTH_INDEFINITE)
        snackbarisconnected = Snackbar.make(view, "Device is connected to internet", Snackbar.LENGTH_SHORT)
        view = snackbarnotconnected.view
        textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.RED)

        if(!checkInternetConnection()){
            snackbarnotconnected.show()
            enterStudentLogin.isEnabled = false
        }

    }

    override fun onClick(v: View) {
        Log.e("Clicked", "Button has been clicked")
        if (student_text_input.text.isEmpty()){
            Snackbar.make(student_login_activity, "You must fill out a name", Snackbar.LENGTH_LONG).show()
        } else {
            startActivity(Intent(this, JoinGroupActivity::class.java).putExtra("studentName", student_text_input.text.toString()))
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




    internal var networkChangeReceiver = InternalNetworkChangeReceiver()

    internal inner class InternalNetworkChangeReceiver : BroadcastReceiver() {


        override fun onReceive(c: Context, i: Intent) {
            Log.e("hej", i.getBooleanExtra("networkstatus", false).toString());
            if (!i.getBooleanExtra("networkstatus", false)) {
                snackbarnotconnected.show()
                enterStudentLogin.isEnabled = false
            } else {
                    snackbarnotconnected.dismiss()
                    snackbarisconnected.show()
                    enterStudentLogin.isEnabled = true
            }
        }
    }

    fun noSoftKeyBoard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var v = currentFocus
        if (v == null) {
            v = View(this)
        }
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun checkInternetConnection() : Boolean{
        val cm: ConnectivityManager =  getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected)

    }
}


