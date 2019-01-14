package dk.dtu.isaacirani.kirurgisksimulator.models

data class Student constructor(
        var id_s: Int = 0,
        var name: String,
        var scenario: Scenario = Scenario()
)
