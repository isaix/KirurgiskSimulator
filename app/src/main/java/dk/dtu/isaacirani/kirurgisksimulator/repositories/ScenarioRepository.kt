package dk.dtu.isaacirani.kirurgisksimulator.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario

public class ScenarioRepository {

    var mDatabase = FirebaseDatabase.getInstance().reference
    var scenarioRef = mDatabase.child("Scenarios")

    fun loadScenarios(callBack: (ArrayList<Scenario>) -> Unit) {
        scenarioRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var scenarios: ArrayList<Scenario> = ArrayList();

                for(itemSnapShot: DataSnapshot in dataSnapshot.children) {
                    var entry: Scenario = itemSnapShot.getValue(Scenario::class.java)!!
                    scenarios.add(entry)
                }

                callBack(scenarios)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun createScenario(entry: Scenario) : String{
        val id = scenarioRef.push().key!!
        scenarioRef.child(id).setValue(entry)
        return id;
    }

}