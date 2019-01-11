package dk.dtu.isaacirani.kirurgisksimulator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import dk.dtu.isaacirani.kirurgisksimulator.activities.SimulatorActivity

class StudentLoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var simulator: Intent
    lateinit var enterStudentLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        enterStudentLogin = findViewById(R.id.enterStudentLogin)
        enterStudentLogin.setOnClickListener(this)

        simulator = Intent(this, SimulatorActivity::class.java)

    }

    override fun onClick(v: View) {
        startActivity(simulator)
    }
}
