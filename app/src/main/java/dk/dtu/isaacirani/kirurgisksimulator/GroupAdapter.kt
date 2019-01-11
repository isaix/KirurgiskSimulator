package dk.dtu.isaacirani.kirurgisksimulator

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import com.google.firebase.database.*
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
import dk.dtu.isaacirani.kirurgisksimulator.models.Student


public class GroupAdapter {

    lateinit var GroupReference: DatabaseReference
    lateinit var group : Group


    var mDatabase = FirebaseDatabase.getInstance().reference
    //toDoItemsReference = mDatabase.child("todo_item")
    //toDoItemsReference.addValueEventListener(itemListener)

    val listener = mDatabase.child("Group").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            val instructor : Instructor = dataSnapshot.child("Instructor").getValue(Instructor::class.java)!!
            var students: MutableList<Student> = mutableListOf()

            Log.e("Instructor", instructor.name)
            for (itemSnapshot: DataSnapshot in dataSnapshot.child("Students").children) {
                var student = itemSnapshot.getValue(Student::class.java)!!
                Log.e("Student", student.name)
                students.add(student)
            }
            group = Group(instructor, students)
            Log.e("Group", group.instructor.name)
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w("ERROR", "Failed to read value.", error.toException())
        }
    })


    fun createGroup(instructor: Instructor, studentList: MutableList<Student>){
        mDatabase.child("Group").child("Instructor").setValue(instructor)
        mDatabase.child("Group").child("Students").setValue(studentList)
    }

    fun updateGroup(studentList: ArrayList<Student>){
    }

    fun loadGroup(){
        //return group
    }



}