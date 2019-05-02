package edu.umb.cs680.hw02;

public class Student {
    private String firstName;
    private String lastName;    
    private float tuition;
    private StudentType studentType;

    public Student(String firstName, String lastName, float tuition, StudentType studentType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentType = studentType;
        this.tuition = tuition;
    }

    public StudentType getStudentType() {
        return this.studentType;
    };

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public float getTuition() {
        return this.tuition * this.getStudentType().getTuitionFactor();
    }

    public static void main(String[] args) {
        Student stud = StudentFactory.createInternationalStudent("Kim", "Park", 40000);
        System.out.printf("Student: %s %s is a student.\n",
                          stud.getFirstName(),
                          stud.getLastName());
    }
}
