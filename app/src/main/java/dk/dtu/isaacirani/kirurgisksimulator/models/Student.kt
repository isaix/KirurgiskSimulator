package dk.dtu.isaacirani.kirurgisksimulator.models

data class Student constructor(
        var name: String = "Unknown",
        var scenario: Scenario = Scenario()
)
