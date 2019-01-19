package dk.dtu.isaacirani.kirurgisksimulator.models

data class Group constructor(
        var instructor: Instructor = Instructor("Unknown"),
        var students: HashMap<String, Student> = hashMapOf()
)