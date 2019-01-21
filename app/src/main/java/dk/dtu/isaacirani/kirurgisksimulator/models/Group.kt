package dk.dtu.isaacirani.kirurgisksimulator.models

data class Group constructor(
        var id: String = "Unknown",
        var instructor: Instructor = Instructor("Unknown"),
        var students: ArrayList<Student> = arrayListOf()
)