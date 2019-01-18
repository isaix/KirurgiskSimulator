package dk.dtu.isaacirani.kirurgisksimulator.models

import java.util.*

data class LogEntry constructor(
    var name: String = "Student",
    var instructor: String = "Instructor",
    var scenarioName: String = "Name",
    var date: Date = Date(0L),
    var time: Int = 0,
    var failures: Int = 0

)