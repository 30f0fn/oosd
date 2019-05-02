package edu.umb.cs680.hw09;

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;



class CarMileageComparatorTest{

    private static Car okDeal;
    private static Car ripoff;
    private static Car ripoff2;    
    private static Car oldster;    
    private static Car evenOldster;
    private static Car newCar;
    private static Comparator<Car> comp;

    @BeforeAll
    public static void setUp() {
        okDeal = new Car("Toyota", "RAV4", 50, 2018, 14999);
        ripoff = new Car("Toyota", "RAV4", 49, 2018, 49998);
        ripoff2 = new Car("Toyota", "RAV4", 50, 2018, 49999);
        oldster = new Car("Toyota", "RAV4", 400000, 2010, 200);
        evenOldster = new Car("Toyota", "RAV4", 4000000, 1919, 200000);
        newCar = new Car("Toyota", "RAV4", 0, 2019, 19999);

        comp = new CarMileageComparator();

}


    @Test
    public void verifyPriceComparator() {
        ArrayList<Car> carsExpected = new ArrayList<Car>();
        ArrayList<Car> carsActual = new ArrayList<Car>();        
        carsExpected.add(newCar);
        carsExpected.add(ripoff);
        carsExpected.add(okDeal);
        carsExpected.add(ripoff2);
        carsExpected.add(oldster);
        carsExpected.add(evenOldster);

        carsActual.add(okDeal);
        carsActual.add(ripoff);
        carsActual.add(ripoff2);
        carsActual.add(oldster);
        carsActual.add(evenOldster);
        carsActual.add(newCar);
        Collections.sort(carsActual, comp);
        for (int i = 0; i < carsExpected.size(); i++) {
            assertEquals(carsExpected.get(i), carsActual.get(i));
        }
    }

}

