package edu.umb.cs680.hw14;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.lang.System;

/**
a domination-count sorting algorithm can't have a worst case which doesn't require computing O(n^2) instances of the paretoDomination relationship.  Since a realistic use case may have 1000 or 10000 items, it's a good idea to precompute the domination counts.  (A worst case would be when all items are incomparable, as for example with (1, -1, 0), (2, -2, 0), (3, -3, 0)...).
**/

class CarDominanceCountTable {

    private CarViewer viewer; 

    private HashMap<Car, HashSet<Car>> carDomTable;
    private Comparator<Car> dominanceCountComp;

    protected CarDominanceCountTable(CarViewer viewer) {
        this.viewer = viewer;
        carDomTable = new HashMap<Car, HashSet<Car>>();
    }

    protected void addCar(Car car) {
        HashSet<Car> dominates = new HashSet<Car>();
        carDomTable.put(car, dominates);
        for (Car car2 : viewer.getCars()) {
            int cmp = viewer.paretoCompare(car2, car);
            if (cmp < 0) {
                carDomTable.get(car2).add(car);
            } else if (cmp > 0) {
                carDomTable.get(car).add(car2);
            }
        }
    }

    protected void removeCar(Car car) {
        carDomTable.remove(car);
        for (Car car2 : viewer.getCars()) {
            carDomTable.get(car2).remove(car);
        }
    }
    
    protected int getDomCount(Car car) {
        return carDomTable.get(car).size();
    }


}
