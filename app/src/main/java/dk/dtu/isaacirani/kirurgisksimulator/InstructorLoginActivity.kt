package dk.dtu.isaacirani.kirurgisksimulator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import dk.dtu.isaacirani.kirurgisksimulator.activities.InstructorActivity

class InstructorLoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var surgeonEnterLogin: Button
    lateinit var surgeon: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_login)

        surgeonEnterLogin = findViewById(R.id.enterSurgeonLogin)
        surgeonEnterLogin.setOnClickListener(this)

        surgeon = Intent(this, InstructorActivity::class.java)
    }

    override fun onClick(v: View) {
        startActivity(surgeon)
    }
}
