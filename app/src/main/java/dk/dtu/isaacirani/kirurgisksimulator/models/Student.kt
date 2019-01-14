package dk.dtu.isaacirani.kirurgisksimulator.models

data class Student constructor(
        var name: String,
        var scenario: Scenario = Scenario()
)
