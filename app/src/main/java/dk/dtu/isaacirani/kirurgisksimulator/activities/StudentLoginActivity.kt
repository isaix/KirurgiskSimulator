package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import dk.dtu.isaacirani.kirurgisksimulator.NetworkChangeReceiver
import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.R.layout.activity_student_login
import dk.dtu.isaacirani.kirurgisksimulator.adapters.GroupsAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import kotlinx.android.synthetic.main.activity_instructor_login.*
import kotlinx.android.synthetic.main.activity_student_login.*

class StudentLoginActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
        setSupportActionBar(findViewById(R.id.toolbar))


        noSoftKeyBoard()
        enterStudentLogin.setOnClickListener(this)


        registerReceiver()

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

    fun noSoftKeyBoard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var v = currentFocus
        if (v == null) {
            v = View(this)
        }
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }
}


