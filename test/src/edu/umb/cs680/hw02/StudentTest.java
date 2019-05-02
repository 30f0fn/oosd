package edu.umb.cs680.hw02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StudentTest{

    @Test
    public void testInternationalStudentType() {
        Student s = StudentFactory.createInternationalStudent("Ayalur", "Krishnan", 15000);
        StudentType expected = StudentType.INTERNATIONAL;
        StudentType actual = s.getStudentType();
        assertEquals(actual, expected);
    }

    @Test
    public void testInternationalStudentTuition() {
        float tuition = 15000;
        Student s = StudentFactory.createInternationalStudent("Ayalur", "Krishnan", tuition);
        float expected = tuition * 2;
        float actual = s.getTuition();
        assertEquals(actual, expected);
    }

    @Test
    public void testOutOfStateStudentTuition() {
        float tuition = 15000;
        Student s = StudentFactory.createOutOfStateStudent("Johnny", "Johnson", tuition);
        float expected = tuition * 1;
        float actual = s.getTuition();
        assertEquals(actual, expected);
    }

    @Test
    public void testInStateStudentTuition() {
        float tuition = 15000;
        Student s = StudentFactory.createInStateStudent("Ulysses", "Euphorbia", tuition);
        float expected = tuition * .5f;
        float actual = s.getTuition();
        assertEquals(actual, expected);
    }

    @Test
    public void testFirstName() {
        Student s = StudentFactory.createInStateStudent("Ulysses", "Euphorbia", 15000);
        String expected = "Ulysses";
        String actual = s.getFirstName();
        assertEquals(actual, expected);
    }

    @Test
    public void testLastName() {
        Student s = StudentFactory.createInStateStudent("Ulysses", "Euphorbia", 15000);
        String expected = "Euphorbia";
        String actual = s.getLastName();
        assertEquals(actual, expected);
    }


}



