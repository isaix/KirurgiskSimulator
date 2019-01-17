package dk.dtu.isaacirani.kirurgisksimulator.models

data class Group constructor(
        var instructor: Instructor = Instructor(0, "Unknown"),
        var students: HashMap<String, Student> = hashMapOf()
)