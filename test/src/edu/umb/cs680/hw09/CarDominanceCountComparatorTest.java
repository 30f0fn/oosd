// // todo NOT IN USE

// package edu.umb.cs680.hw09;

// import java.util.Comparator;
// import java.util.Collections;
// import java.util.ArrayList;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeAll;


// class CarDominanceCountComparatorTest {

//     private static Car okDeal;
//     private static Car ripoff;
//     private static Car biggerRipoff;    
//     private static Car oldster;    
//     private static Car evenOldster;
//     private static Car newCar;
//     private static CarViewer viewer;
//     private static CarDominanceCountComparator domComp;

//     @BeforeAll
//     public static void setUp() {
//         okDeal = new Car("Toyota", "RAV4", 50, 2018, 14999); // 1
//         ripoff = new Car("Toyota", "RAV4", 49, 2012, 49998); // 2
//         biggerRipoff = new Car("Toyota", "RAV4", 51, 2011, 49999); // 1
//         oldster = new Car("Toyota", "RAV4", 400000, 2010, 200); // 1
//         evenOldster = new Car("Toyota", "RAV4", 4000000, 1919, 200000);  // 0
//         newCar = new Car("Toyota", "RAV4", 0, 2019, 19999); //1

//         viewer = new CarViewer();
//         domComp = new CarDominanceCountComparator(viewer);

//         viewer.addCar(newCar);
//         viewer.addCar(okDeal);
//         viewer.addCar(biggerRipoff);
//         viewer.addCar(ripoff);
//         viewer.addCar(oldster);
//         viewer.addCar(evenOldster);        

//     }


//     @Test
//     public void verifyAddCar() {
//         System.out.printf("ripoff: domCount = %d", domComp.getDomCount(ripoff));
//         int c = domComp.compare(ripoff, biggerRipoff);
//         assertEquals(1, c);
//     }

// }

