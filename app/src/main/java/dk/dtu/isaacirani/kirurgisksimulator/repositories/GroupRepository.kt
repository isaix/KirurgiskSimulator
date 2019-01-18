package dk.dtu.isaacirani.kirurgisksimulator.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import dk.dtu.isaacirani.kirurgisksimulator.models.Student


public class GroupRepository {

    var mDatabase = FirebaseDatabase.getInstance().reference
    val groupsRef = mDatabase.child("Groups")


    fun loadGroups(callback: (HashMap<String, Group>) -> Unit) {
        groupsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var groups: HashMap<String, Group> = HashMap()

                for(itemSnapshot: DataSnapshot in dataSnapshot.children){
                    Log.e("STATUS", "Reading Group")
                    val instructor = itemSnapshot.child("Instructor").getValue(Instructor::class.java)!!
                    var students: HashMap<String, Student> = hashMapOf()

                    for(subItemSnapshot: DataSnapshot in itemSnapshot.child("Students").children){
                        Log.e("STATUS", "Reading Students")
                        students[subItemSnapshot.key!!] = subItemSnapshot.getValue(Student::class.java)!!
                    }
                    groups[itemSnapshot.key!!] = itemSnapshot.getValue(Group::class.java)!!
                }

                callback(groups)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun loadGroup(groupId: String, callback: (Group) -> Unit) {
        mDatabase.child("Groups").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val instructor: Instructor = dataSnapshot.child(groupId).child("instructor").getValue(Instructor::class.java)!!
                var students: HashMap<String, Student> = hashMapOf()
                Log.e("what's in here?", dataSnapshot.key)

                Log.e("Instructor", instructor.name)
                for (itemSnapshot: DataSnapshot in dataSnapshot.child(groupId).child("Students").children) {
                    Log.e("what's in here part 2?", itemSnapshot.key)
                    students[itemSnapshot.key!!] = itemSnapshot.getValue(Student::class.java)!!
                }
                val activeGroup = Group(instructor, students)
                Log.e("Group", activeGroup.instructor.name)

                callback(activeGroup)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }


    fun createGroupWithStudents(group: Group) : String {
        val id = groupsRef.push().key!!
        groupsRef.child(id).setValue(group)
        return id
    }

    fun createGroupWithoutStudents(instructor: Instructor): String {
        val id = groupsRef.push().key!!
        groupsRef.child(id).child("Instructor").setValue(instructor)
        return id
    }

    fun addStudentToGroup(groupId: String, student: Student): String {
        val id = groupsRef.child("Students").push().key!!
        groupsRef.child(groupId).child("Students").child(id).setValue(student)
        return id
    }

    fun loadStudent(studentId: String, callback: (Student) -> Unit) {
        mDatabase.child("Group").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                //callback(Student)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException())
            }
        })
    }


}