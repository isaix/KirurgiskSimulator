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


    var mDatabase = FirebaseDatabase.getInstance().reference
    //toDoItemsReference = mDatabase.child("todo_item")
    //toDoItemsReference.addValueEventListener(itemListener)


    fun createGroup(instructor: Instructor, studentList: MutableList<Student>){
        var group = Group(instructor, studentList)
        val key = mDatabase.child("kirurgisksimulator-127e1").push().key

        mDatabase.child("kirurgisksimulator-127e1").child(key!!).setValue(group)


        //mDatabase.child("groups").child("group").setValue()
    }

    fun updateGroup(studentList: ArrayList<Student>){

    }

    fun loadGroup()/*: Group*/ {
        val itemListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (itemSnapshot: DataSnapshot in dataSnapshot.children) {

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("LoadGroup", "loadItem:onCancelled", databaseError.toException())
            }
        }
    }
}