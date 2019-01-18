package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_settings.*
import android.support.v4.view.GravityCompat
import dk.dtu.isaacirani.kirurgisksimulator.R
import android.view.MenuItem
import android.support.design.widget.NavigationView
import android.util.Log
import dk.dtu.isaacirani.kirurgisksimulator.GroupRepository
import dk.dtu.isaacirani.kirurgisksimulator.ScenarioRepository
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario
import dk.dtu.isaacirani.kirurgisksimulator.models.Student
import kotlinx.android.synthetic.main.activity_simulator_container.view.*

class SettingsActivity : AppCompatActivity(){
    private lateinit var nv: NavigationView
    var groupRepository = GroupRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)




        var scenarioAdapter = ScenarioRepository()


//        scenarioAdapter.createScenario(Scenario("Edmund",4, 5, 2, 1))
//        scenarioAdapter.createScenario(Scenario("Lucy ",6, 8, 4, 1))
//        scenarioAdapter.createScenario(Scenario("Peter",1, 4, 7, 1))
//        scenarioAdapter.createScenario(Scenario("Susan",2, 7, 6, 1))
//        scenarioAdapter.createScenario(Scenario("Mr.Robot",4, 6, 6, 1))
//        scenarioAdapter.createScenario(Scenario("Stefan",4, 5, 2, 1))
//        scenarioAdapter.createScenario(Scenario("Damon ",6, 8, 4, 1))
//        scenarioAdapter.createScenario(Scenario("Alaric",1, 4, 7, 1))
//        scenarioAdapter.createScenario(Scenario("John",2, 7, 6, 1))
//        scenarioAdapter.createScenario(Scenario("Mr.Kitty",4, 6, 6, 1))
//        groupAdapter.addStudentToGroup(Student("Edmund", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Lucy", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Peter", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Susan", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Dean", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Sam", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Bobby", Scenario()))
//        groupAdapter.addStudentToGroup(Student("Castiel", Scenario()))
//        groupRepository.createGroupWithStudents(Group())
//        groupRepository.addStudentToGroup("-LWQO2j41v1ZhlUOx-xq", Student("Edmund", Scenario()))
//        groupRepository.addStudentToGroup("-LWQO2j41v1ZhlUOx-xq", Student("Sam", Scenario()))
//        groupRepository.addStudentToGroup("-LWQO2j41v1ZhlUOx-xq", Student("Bob", Scenario()))
//        groupRepository.addStudentToGroup("-LWQO2j41v1ZhlUOx-xq", Student("Elise", Scenario()))
//        groupRepository.addStudentToGroup("-LWQO2j41v1ZhlUOx-xq", Student("Mahmoud", Scenario()))
//        groupRepository.addStudentToGroup("-LWQO2j41v1ZhlUOx-xq", Student("Mikkel", Scenario()))
//        groupRepository.createGroupWithoutStudents(Instructor())



        //groupAdapter.createGroup(Instructor(12, "bølle Bob"), MockData().students.toMutableList())
        //groupRepository.loadGroups{ group -> testFun(group)}
//        groupAdapter.addStudentToGroup(Student(0,"Brian", Scenario()))
        groupRepository.loadGroup("-LWQO2j41v1ZhlUOx-xq"){ group: Group -> testFun(group)}

        setSupportActionBar(toolbar)

//
//        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer.addDrawerListener(toggle)
//        toggle.syncState()
//
//
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        navigationView.setNavigationItemSelectedListener(this)

    }


    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun testFun(group: Group){
        Log.e("SUCCESS", "this time in a callback")
       // group.students.map { student: Student ->  Log.e("TestFun", "denne gang i callback") }
    }


//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val intent: Intent
//        when (item.itemId) {
//            R.id.about -> {
//                intent = Intent(this, SimulatorActivity::class.java)
//                finish()
//                startActivity(intent)
//            }
//
//        }
//
//        drawer.closeDrawer(GravityCompat.START)
//        return true
//    }






}
