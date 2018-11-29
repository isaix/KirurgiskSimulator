package dk.dtu.isaacirani.kirurgisksimulator.models;

public class Student {

    public int id_s;
    public String name;
    public Scenario scenario;

    public Student(int id_s, String name, Scenario scenario) {
        this.id_s = id_s;
        this.name = name;
        this.scenario = scenario;
    }

    public int getId() {
        return id_s;
    }

    public void setId(int id_s) {
        this.id_s = id_s;
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
