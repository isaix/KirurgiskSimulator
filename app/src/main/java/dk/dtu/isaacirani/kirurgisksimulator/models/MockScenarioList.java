package dk.dtu.isaacirani.kirurgisksimulator.models;

import java.util.ArrayList;

public class MockScenarioList {

    ArrayList<Scenario> scenarios;

    public MockScenarioList() {
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios.add(new Scenario("Mads", 3, 2, 3.0,false));
        scenarios.add(new Scenario("Frederik", 2, 3, 3.0,true));
        scenarios.add(new Scenario("Isaac", 3, 4, 4.0,true));
        scenarios.add(new Scenario("Yoss", 2, 3, 4.6,true));
        scenarios.add(new Scenario("Benjamin", 4, 2, 4,false));
        this.scenarios = scenarios;

    }

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public Scenario getScenario(int index){ return scenarios.get(index); }
}
