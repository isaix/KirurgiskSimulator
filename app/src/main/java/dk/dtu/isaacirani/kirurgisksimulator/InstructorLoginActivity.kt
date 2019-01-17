package dk.dtu.isaacirani.kirurgisksimulator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import dk.dtu.isaacirani.kirurgisksimulator.activities.InstructorActivity
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor

class InstructorLoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var surgeonEnterLogin: Button
    lateinit var surgeon: Intent
    var group:GroupRepository = GroupRepository()
    var instructor: Instructor = Instructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_login)

        surgeonEnterLogin = findViewById(R.id.enterSurgeonLogin)
        surgeonEnterLogin.setOnClickListener(this)



        surgeon = Intent(this, InstructorActivity::class.java)

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
}
