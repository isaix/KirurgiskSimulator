package dk.dtu.isaacirani.kirurgisksimulator.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.dtu.isaacirani.kirurgisksimulator.models.LogEntry
import java.util.*

public class LogRepository {

    var mDatabase = FirebaseDatabase.getInstance().reference;
    var logRef = mDatabase.child("Logs");

    fun loadLogs(callback: (ArrayList<LogEntry>) -> Unit) {
        logRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var logs: ArrayList<LogEntry> = ArrayList();

                for (itemSnapShot: DataSnapshot in dataSnapshot.children) {
                    var entry: LogEntry = itemSnapShot.getValue(LogEntry::class.java)!!
                    logs.add(entry)
                }

                Collections.reverse(logs)
                callback(logs)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun addLog(entry: LogEntry) : String{
        val id = logRef.push().key!!
        logRef.child(id).setValue(entry)
        Log.e("Name:", entry.name)
        return id
    }
}