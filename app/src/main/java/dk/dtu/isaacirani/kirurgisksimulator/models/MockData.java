package dk.dtu.isaacirani.kirurgisksimulator.models;

public class MockData {
    Student[] students;

    public MockData() {
//        Student[] students = new Student[5];
//        students[0] = new Student("Mads", new MockScenarioList().getScenario(0));
//        students[1] = new Student( "Yoss", new MockScenarioList().getScenario(1));
//        students[2] = new Student( "Frederik", new MockScenarioList().getScenario(2));
//        students[3] = new Student( "Benjamin", new MockScenarioList().getScenario(3));
//        students[4] = new Student( "Isaac", new MockScenarioList().getScenario(4));
//        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
