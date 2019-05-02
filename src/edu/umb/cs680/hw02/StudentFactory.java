package edu.umb.cs680.hw02;

public class StudentFactory {

    private StudentFactory() {} // hide the constructor

    public static Student createInternationalStudent(String firstName, String lastName,
                                                     float tuition) {
        Student student = new Student(firstName, lastName, tuition, StudentType.INTERNATIONAL);
        return student;
    }

    public static Student createInStateStudent(String firstName, String lastName,
                                               float tuition) {
        Student student = new Student(firstName, lastName, tuition, StudentType.INSTATE);
        return student;
    }

    public static Student createOutOfStateStudent(String firstName, String lastName,
                                                  float tuition) {
        Student student = new Student(firstName, lastName, tuition, StudentType.OUTOFSTATE);
        return student;
    }
}
