package dk.dtu.isaacirani.kirurgisksimulator;

public class MockData {
    Student[] students;

    public MockData() {
        Student[] students = new Student[5];
        students[0] = new Student(1, 2, 3, "Mads", 2.1, true);
        students[1] = new Student(2, 1, 4, "Frederik", 1.5, false);
        students[2] = new Student(3, 2, 2, "Yoss", 1.7, true);
        students[3] = new Student(4, 2, 4, "Isaac", 1.4, true);
        students[4] = new Student(5, 1, 3, "Benjamin", 1.3, false);
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
