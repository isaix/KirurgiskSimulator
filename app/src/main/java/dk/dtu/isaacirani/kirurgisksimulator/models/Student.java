package dk.dtu.isaacirani.kirurgisksimulator.models;

public class Student {

    public int ID;
    public String name;
    public Scenario scenario;

    public Student(int ID, String name, Scenario scenario) {
        this.ID = ID;
        this.name = name;
        this.scenario = scenario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
