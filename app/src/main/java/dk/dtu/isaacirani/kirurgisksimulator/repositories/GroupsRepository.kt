package dk.dtu.isaacirani.kirurgisksimulator.repositories

import android.util.Log
import com.google.firebase.database.*
import dk.dtu.isaacirani.kirurgisksimulator.models.Group
import dk.dtu.isaacirani.kirurgisksimulator.models.Instructor
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
                if (dataSnapshot == null) {
                    callback(dataSnapshot.getValue(Group::class.java))
                }

//                if (dataSnapshot == null){
//                    val instructor: Instructor = dataSnapshot.child("Instructor").getValue(Instructor::class.java)!!
//                    var students: HashMap<String, Student> = hashMapOf()
//                    Log.e("what's in here?", dataSnapshot.key)
//
//                    Log.e("Instructor", instructor.name)
//                    for (itemSnapshot: DataSnapshot in dataSnapshot.child("Students").children) {
//                        Log.e("what's in here part 2?", itemSnapshot.key)
//                        students[itemSnapshot.key!!] = itemSnapshot.getValue(Student::class.java)!!
//                    }
//                    val activeGroup = Group(instructor, students)
//                    Log.e("Group", activeGroup.instructor.name)
//
//                    callback(activeGroup)
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


//    fun loadStudent(studentId: String, callback: (Student) -> Unit) {
//        mDatabase.child("Group").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//
//                //callback(Student)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("ERROR", "Failed to read value.", error.toException())
//            }
//        })
//    }


}