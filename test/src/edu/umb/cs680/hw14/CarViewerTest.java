package edu.umb.cs680.hw14;

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
            // System.out.printf("expected has year %d\n",
            //                   carExpected.getYear(),
            //                   viewer.getDomCount(carExpected)
            //                   );
            // System.out.printf("actual has year %d\n",
            //                   car.getYear(),
            //                   viewer.getDomCount(car));
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
            Car car = carsActual.get(i);
            Car carExpected = carsExpected.get(i);
            // System.out.printf("expected has year %d and price %f; ",
                              // carExpected.getYear(),
                              // carExpected.getPrice()
                              // );
            // System.out.printf("actual has year %d and price %f\n",
                              // car.getYear(),
                              // car.getPrice()
                              // );
            assertEquals(carExpected, car);
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
        // System.out.println("ripoff, ripoffhelper\n");
        // System.out.println(viewer.paretoCompare(ripoff, ripoffHelper));
        assertTrue(viewer.paretoCompare(ripoff, ripoffHelper) < 0);
        // System.out.println("ripoff, antique\n");
        assertTrue(viewer.paretoCompare(ripoff, antique) < 0);
        // System.out.println("ripoff, okdeal\n");
        assertTrue(viewer.paretoCompare(ripoff, okDeal) == 0);
        assertTrue(viewer.paretoCompare(okDeal, ripoff) == 0);
        assertTrue(viewer.paretoCompare(okDeal, antique) < 0);
        assertTrue(viewer.paretoCompare(newCar, okDeal) == 0);
        assertTrue(viewer.paretoCompare(newCar, antique) < 0);
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

        // for (int i = 0; i < carsExpected.size(); i++) {
        //     Car car = carsActual.get(i);
        //     Car carExpected = carsExpected.get(i);
        //     System.out.printf("expected has year %d and dom count %d\n",
        //                       carExpected.getYear(),
        //                       viewer.getDomCount(carExpected)
        //                       );
        //     System.out.printf("actual has year %d and dom count %d\n",
        //                       car.getYear(),
        //                       viewer.getDomCount(car));
        //     // assertEquals(carExpected, car);
        // }
        
        
    }


}

