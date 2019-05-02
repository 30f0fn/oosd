package edu.umb.cs680.hw09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;


class CarTest{

    private static Car car;

    @BeforeAll
    public static void setUp() {
        car = new Car("Toyota", "RAV4", 0, 2018, 19999);
    }


    private String[] carToStringArrayForEquality(Car car) {
        String[] carArray = {car.getMake(), car.getModel(),
                             Integer.toString(car.getYear())};
        return carArray;
    }

    @Test
    public void verifyCarEqualityWithMakeModelYear() {
        String[] expected = {"Toyota", "RAV4", "2018"};
        // actual = car;
        // Car actual = new Car("Toyota", "RAV4", 0, 2018, 19999);
        assertArrayEquals(expected, carToStringArrayForEquality(car));
    }

    @Test
    public void verifyMakeGetter() {
        String expectedMake = "Toyota";
        Car car = new Car(expectedMake, "RAV4", 0, 2018, 19999);
        String actualMake = car.getMake();
        assertEquals(actualMake, expectedMake);
    }

}

