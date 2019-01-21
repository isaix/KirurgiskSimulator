package dk.dtu.isaacirani.kirurgisksimulator.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import android.support.v4.view.GravityCompat
import dk.dtu.isaacirani.kirurgisksimulator.R
import android.support.design.widget.NavigationView
import android.util.Log
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import dk.dtu.isaacirani.kirurgisksimulator.repositories.ScenarioRepository
import dk.dtu.isaacirani.kirurgisksimulator.models.Group

class SettingsActivity : AppCompatActivity(){
    private lateinit var nv: NavigationView
    var groupRepository = GroupsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)


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



//        groupRepository.loadGroup("-LWQO2j41v1ZhlUOx-xq"){ group: Group -> testFun(group)}

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
    }
}
