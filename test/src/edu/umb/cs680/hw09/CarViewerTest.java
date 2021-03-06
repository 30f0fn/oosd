package edu.umb.cs680.hw09;

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


class CarViewerTest {

    private static Car okDeal;
    private static Car ripoff;
    private static Car ripoffHelper;    
    private static Car jalopy;    
    private static Car antique;
    private static Car newCar;

    private static CarViewer viewer;

    @BeforeAll
    public static void setUp() {
        okDeal = new Car("Toyota", "RAV4", 50, 2018, 14999); // domCount 1
        ripoff = new Car("Toyota", "RAV4", 48, 2012, 49998); // 2
        ripoffHelper = new Car("Toyota", "RAV4", 49, 2011, 49999); // 1
        jalopy = new Car("Toyota", "RAV4", 400000, 2010, 200); // 1
        antique = new Car("Toyota", "RAV4", 4000000, 1919, 200000);  // 0
        newCar = new Car("Toyota", "RAV4", 0, 2019, 19999); //3

        viewer = new CarViewer();

        viewer.addCar(newCar);
        viewer.addCar(okDeal);
        viewer.addCar(ripoffHelper);
        viewer.addCar(ripoff);
        viewer.addCar(jalopy);
        viewer.addCar(antique);        
}


    @Test
    public void verifyYear() {
        ArrayList<Car> carsExpected = new ArrayList<Car>();
        carsExpected.add(newCar);
        carsExpected.add(okDeal);
        carsExpected.add(ripoff);
        carsExpected.add(ripoffHelper);
        carsExpected.add(jalopy);
        carsExpected.add(antique);

        ArrayList<Car> carsActual = viewer.byYear();
        for (int i = 0; i < carsExpected.size(); i++) {
            Car car = carsActual.get(i);
            Car carExpected = carsExpected.get(i);
            assertEquals(carExpected, car);
        }
    }

    @Test
    public void verifyPrice() {
        ArrayList<Car> carsExpected = new ArrayList<Car>();
        carsExpected.add(jalopy);
        carsExpected.add(okDeal);
        carsExpected.add(newCar);
        carsExpected.add(ripoff);
        carsExpected.add(ripoffHelper);
        carsExpected.add(antique);

        ArrayList<Car> carsActual = viewer.byPrice();
        for (int i = 0; i < carsExpected.size(); i++) {
            assertEquals(carsExpected.get(i), carsActual.get(i));
        }
    }

    @Test
    public void verifyMileage() {
        ArrayList<Car> carsExpected = new ArrayList<Car>();
        carsExpected.add(newCar);
        carsExpected.add(ripoff);
        carsExpected.add(ripoffHelper);
        carsExpected.add(okDeal);
        carsExpected.add(jalopy);
        carsExpected.add(antique);

        ArrayList<Car> carsActual = viewer.byMileage();
        for (int i = 0; i < carsExpected.size(); i++) {
            assertEquals(carsExpected.get(i), carsActual.get(i));
        }
    }

    @Test void verifyParetoCompare() {
        assertEquals(viewer.paretoCompare(ripoff, ripoffHelper), -1);
        assertEquals(viewer.paretoCompare(ripoff, antique), -1);
        assertEquals(viewer.paretoCompare(ripoff, okDeal), 0);
        assertEquals(viewer.paretoCompare(okDeal, ripoff), 0);
        assertEquals(viewer.paretoCompare(okDeal, antique), -1);
        assertEquals(viewer.paretoCompare(newCar, okDeal), 0);
        assertEquals(viewer.paretoCompare(newCar, antique), -1);
}

    @Test
    public void verifyDomCountMeasure() {
        assertEquals(1, viewer.getDomCountComp().getDomCount(okDeal));
        assertEquals(2, viewer.getDomCountComp().getDomCount(ripoff));
        assertEquals(1, viewer.getDomCountComp().getDomCount(ripoffHelper));
        assertEquals(0, viewer.getDomCountComp().getDomCount(antique));
    }

    @Test
    public void verifyDomCountSort() {
        viewer.byYear(); // all cars have different years
        ArrayList<Car> carsActual = viewer.byDominanceCount();

        ArrayList<Car> carsExpected = new ArrayList<Car>();
        carsExpected.add(newCar);
        carsExpected.add(ripoff);
        carsExpected.add(okDeal);
        carsExpected.add(ripoffHelper);
        carsExpected.add(jalopy);
        carsExpected.add(antique);

        for (int i = 0; i < carsExpected.size(); i++) {
            Car car = carsActual.get(i);
            Car carExpected = carsExpected.get(i);
            // System.out.printf("expected has year %d", carExpected.getYear());
            // System.out.printf("actual has year %d and dom count %d\n",
                              // car.getYear(),
                              // viewer.getDomCountComp().getDomCount(car));
            assertEquals(carExpected, car);
        }
        
        
    }


}

