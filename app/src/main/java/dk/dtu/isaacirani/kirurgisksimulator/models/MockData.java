package dk.dtu.isaacirani.kirurgisksimulator.models;

public class MockData {
    Student[] students;

    public MockData() {
        Student[] students = new Student[5];
        students[0] = new Student(1, "Mads", new MockScenarioList().getScenario(0));
        students[1] = new Student(2, "Yoss", new MockScenarioList().getScenario(1));
        students[2] = new Student(3, "Frederik", new MockScenarioList().getScenario(2));
        students[3] = new Student(4, "Benjamin", new MockScenarioList().getScenario(3));
        students[4] = new Student(5, "Isaac", new MockScenarioList().getScenario(4));
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
