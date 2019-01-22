package dk.dtu.isaacirani.kirurgisksimulator.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList
import java.util.Date
import java.util.HashMap

import javax.security.auth.callback.Callback

import dk.dtu.isaacirani.kirurgisksimulator.R
import dk.dtu.isaacirani.kirurgisksimulator.ViewHolder
import dk.dtu.isaacirani.kirurgisksimulator.activities.InstructorActivity
import dk.dtu.isaacirani.kirurgisksimulator.adapters.ScenarioPickerAdapter
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import dk.dtu.isaacirani.kirurgisksimulator.models.LogEntry
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario
import dk.dtu.isaacirani.kirurgisksimulator.models.Student
import dk.dtu.isaacirani.kirurgisksimulator.repositories.GroupsRepository
import dk.dtu.isaacirani.kirurgisksimulator.repositories.LogRepository

class StudentAdapter(internal var students: ArrayList<Student>) : RecyclerView.Adapter<ViewHolder>() {
    internal var logs = LogRepository()
    internal var groupsRepository = GroupsRepository()
    internal var date: Date? = null
    internal var chosenStudent = -1
    internal var backGroundNoBorder: Int = 0
    internal var backGroundLeftBorder: Int = 0
    internal var backGroundRightBorder: Int = 0
    var defaultScenario = Scenario()



    internal var visibility = View.INVISIBLE


    init {
        logEntries = SparseArray()
        startTimes = SparseArray()
        finishTimes = SparseArray()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_data, viewGroup, false)
        val vh = ViewHolder(view)
        vh.TableRow = view.findViewById(R.id.TableRow)
        vh.ID = view.findViewById(R.id.ID)
        vh.Name = view.findViewById(R.id.Name)
        vh.Rate = view.findViewById(R.id.Rate)
        vh.RateBar1 = view.findViewById(R.id.Ratebar1)
        vh.RateBar2 = view.findViewById(R.id.Ratebar2)
        vh.Pressure = view.findViewById(R.id.Pressure)
        vh.PressureBar1 = view.findViewById(R.id.Pressurebar1)
        vh.PressureBar2 = view.findViewById(R.id.Pressurebar2)
        vh.Volume = view.findViewById(R.id.Volume)
        vh.Nozzle = view.findViewById(R.id.Nozzle)
        vh.Air = view.findViewById(R.id.Air)
        vh.checkButton = view.findViewById(R.id.checkButton)
        vh.crossButton = view.findViewById(R.id.crossButton)



        defaultScenario = Scenario("Standard", 0, 0, 0, 0, 0, 0, 0, 0.0, false)
        return vh
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.TableRow.setOnClickListener {
            if (ScenarioPickerAdapter.chosenScenario != null) {
                students[i].scenario = ScenarioPickerAdapter.chosenScenario
                groupsRepository.updateStudent(students[i].id, InstructorActivity.groupID, ScenarioPickerAdapter.chosenScenario)
                notifyItemChanged(i)
                chosenStudent = i


                if (logEntries.get(i) != null) {
                    logEntries.get(i).time = -1
                    logs.addLog(logEntries.get(i))
                }
                logEntries.put(i, LogEntry())

                logEntries.get(i).name = students[i].name
                logEntries.get(i).scenarioName = students[i].scenario.name
                logEntries.get(i).failures = 0
                logEntries.get(i).date = Date(System.currentTimeMillis())
                startTimes.put(i, System.currentTimeMillis())

            }
        }
        viewHolder.checkButton.setOnClickListener {
            chosenStudent = i
            if (logEntries.get(i) != null) {
                finishTimes.put(i, System.currentTimeMillis())
                logEntries.get(i).time = ((finishTimes.get(i) - startTimes.get(i)) / 1000).toInt()
                logs.addLog(logEntries.get(i))
            }
            logEntries.put(i, null)
            students[i].scenario = defaultScenario
            notifyItemChanged(i)


        }

        viewHolder.crossButton.setOnClickListener {
            Log.e(students[i].name, logEntries.get(i).failures.toString() + "")
            if (logEntries.get(i) != null) {
                logEntries.get(i).failures = logEntries.get(i).failures + 1

            }
        }


        if(students.get(i).scenario.equals(defaultScenario)){
            backGroundNoBorder = R.drawable.recyclerview_details_noborder
            backGroundLeftBorder = R.drawable.recyclerview_details_borderleft
            backGroundRightBorder = R.drawable.recyclerview_details_borderright
            visibility = View.INVISIBLE;
        } else {
            backGroundNoBorder = R.drawable.recyclerview_details_noborder_red
            backGroundLeftBorder = R.drawable.recyclerview_details_borderleft_red
            backGroundRightBorder = R.drawable.recyclerview_details_borderright_red
            visibility = View.VISIBLE
        }
        viewHolder.ID.setBackgroundResource(backGroundLeftBorder)
        viewHolder.Name.setBackgroundResource(backGroundRightBorder)
        viewHolder.Pressure.setBackgroundResource(backGroundNoBorder)
        viewHolder.PressureBar1.setBackgroundResource(backGroundNoBorder)
        viewHolder.PressureBar2.setBackgroundResource(backGroundNoBorder)
        viewHolder.Rate.setBackgroundResource(backGroundNoBorder)
        viewHolder.RateBar1.setBackgroundResource(backGroundNoBorder)
        viewHolder.RateBar2.setBackgroundResource(backGroundNoBorder)
        viewHolder.Air.setBackgroundResource(backGroundLeftBorder)
        viewHolder.Volume.setBackgroundResource(backGroundNoBorder)
        viewHolder.Nozzle.setBackgroundResource(backGroundRightBorder)
        viewHolder.checkButton.visibility = visibility;
        viewHolder.crossButton.visibility = visibility;

        val (_, name, scenario) = students[i]
        viewHolder.TableRow.id = i
        viewHolder.ID.text = (i + 1).toString()
        viewHolder.Name.text = name
        viewHolder.Pressure.text = scenario.pressure.toString()
        viewHolder.PressureBar1.text = scenario.pressureBar1.toString()
        viewHolder.PressureBar2.text = scenario.pressureBar2.toString()
        viewHolder.Rate.text = scenario.rate.toString()
        viewHolder.RateBar1.text = scenario.rateBar1.toString()
        viewHolder.RateBar2.text = scenario.rateBar2.toString()
        viewHolder.Air.text = scenario.air.toString()
        viewHolder.Volume.text = java.lang.Double.toString(scenario.volume).substring(0, 3)
        if (scenario.isNozzle) {
            viewHolder.Nozzle.text = "In"
        } else {
            viewHolder.Nozzle.text = "Not In"
        }
        viewHolder.crossButton.visibility = visibility
        viewHolder.checkButton.visibility = visibility

    }

    override fun getItemCount(): Int {
        return students.size
    }

}
