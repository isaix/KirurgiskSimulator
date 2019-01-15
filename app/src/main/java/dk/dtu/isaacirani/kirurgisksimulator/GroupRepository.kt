package dk.dtu.isaacirani.kirurgisksimulator

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
    var instructorRef = mDatabase.child("Group").child("Instructor")
    var studentsRef = mDatabase.child("Group").child("Students")


    fun loadGroup(callback: (Group) -> Unit) {
        mDatabase.child("Group").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val instructor: Instructor = dataSnapshot.child("Instructor").getValue(Instructor::class.java)!!
                var students: HashMap<String, Student> = hashMapOf()
                Log.e("what's in here?", dataSnapshot.key)

                Log.e("Instructor", instructor.name)
                for (itemSnapshot: DataSnapshot in dataSnapshot.child("Students").children) {
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


    fun createGroupWithStudents(instructor: Instructor, studentList: MutableList<Student>) {
        instructorRef.setValue(instructor)
        for (student in studentList) {
            studentsRef.child(studentsRef.push().key!!).setValue(student)
        }
    }

    fun createGroupWithoutStudents(instructor: Instructor) {
        instructorRef.setValue(instructor)
    }

    fun addStudentToGroup(student: Student) {
        studentsRef.child(studentsRef.push().key!!).setValue(student)
    }


}