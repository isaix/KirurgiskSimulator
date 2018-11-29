package dk.dtu.isaacirani.kirurgisksimulator.models;

import java.util.ArrayList;

public class MockScenarioList {

    ArrayList<Scenario> scenarios;

    public MockScenarioList() {
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios.add(new Scenario(4, 3, 2.0, false));
        scenarios.add(new Scenario(5, 2, 3.0, true));
        scenarios.add(new Scenario(2, 3, 4.0, true));
        scenarios.add(new Scenario(4, 2, 3, true));
        scenarios.add(new Scenario(1, 4, 2.5, false));
        this.scenarios = scenarios;

    }

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public Scenario getScenario(int index){ return scenarios.get(index); }
}
