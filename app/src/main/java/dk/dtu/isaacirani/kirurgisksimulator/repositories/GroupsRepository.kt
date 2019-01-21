package dk.dtu.isaacirani.kirurgisksimulator.repositories

import android.util.Log
import com.google.firebase.database.*
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario
import dk.dtu.isaacirani.kirurgisksimulator.models.Student


public class GroupsRepository {

    var mDatabase = FirebaseDatabase.getInstance().reference
    val groupsRef = mDatabase.child("Groups")


    fun loadGroups(callback: (ArrayList<Group>) -> Unit) {
        groupsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var groups: ArrayList<Group> = arrayListOf()

                for(itemSnapshot: DataSnapshot in dataSnapshot.children){
                    groups.add(itemSnapshot.getValue(Group::class.java)!!)
                }

                callback(groups)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun loadGroup(groupId: String, callback: (Group?) -> Unit) {
        mDatabase.child("Groups").child(groupId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot != null) {
                    callback(dataSnapshot.getValue(Group::class.java))
                }

//                } else {
//                    callback(null)
//                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun deleteGroup(groupId: String){
        groupsRef.child(groupId).removeValue()
    }


//    fun createGroupWithStudents(group: Group) : String {
//        val id = groupsRef.push().key!!
//        groupsRef.child(id).setValue(group)
//        return id
//    }

    fun createGroupWithoutStudents(instructor: Instructor, callback: (String) -> Unit){
        val id = groupsRef.push().key!!
        val group = Group(id, instructor)
        groupsRef.child(id).setValue(group)
                .addOnSuccessListener { callback(id) }


    }


    fun addStudentToGroup(groupId: String, student: Student, callback: (String) -> Unit) {
        val id = groupsRef.child("Students").push().key!!
        student.id = id
        groupsRef.child(groupId).child("Students").child(id).setValue(student)
                .addOnSuccessListener { callback(id) }
    }

    fun loadStudentScenario(studentId: String, groupId: String, callback: (Scenario) -> Unit){
        Log.e("GroupID", groupId)
        Log.e("StudentID", studentId)
        groupsRef.child(groupId).child("Students").child(studentId).child("scenario").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot != null) {
                    callback(dataSnapshot.getValue(Scenario::class.java)!!)
                }

//                } else {
//                    callback(null)
//                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }


}