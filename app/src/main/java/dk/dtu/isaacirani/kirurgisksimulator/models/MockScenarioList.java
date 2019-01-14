package dk.dtu.isaacirani.kirurgisksimulator.models;

import java.util.ArrayList;

public class MockScenarioList {

    ArrayList<Scenario> scenarios;

    public MockScenarioList() {
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios.add(new Scenario("test", 20, 20, 50, 20, 20, 20, 20, 30.0, true));
        scenarios.add(new Scenario("test", 20, 20, 50, 20, 20, 20, 20, 30.0, true));
        scenarios.add(new Scenario("test", 20, 20, 50, 20, 20, 20, 20, 30.0, true));
        scenarios.add(new Scenario("test", 20, 20, 50, 20, 20, 20, 20, 30.0, true));
        scenarios.add(new Scenario("test", 20, 20, 50, 20, 20, 20, 20, 30.0, true));


        this.scenarios = scenarios;

    }

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public Scenario getScenario(int index){ return scenarios.get(index); }
}
