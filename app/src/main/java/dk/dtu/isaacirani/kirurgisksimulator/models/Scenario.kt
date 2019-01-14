package dk.dtu.isaacirani.kirurgisksimulator.models

data class Scenario constructor(
        var name: String = "Standard",
        var pressure: Int = 0,
        var rate: Int = 0,
        var air: Int = 0,
        var pressureBar1: Int = 0,
        var pressureBar2: Int = 0,
        var rateBar1: Int = 0,
        var rateBar2: Int = 0,
        var volume: Double = 0.0,
        var isNozzle: Boolean = false
)
