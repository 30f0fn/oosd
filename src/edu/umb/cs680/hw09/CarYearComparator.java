package edu.umb.cs680.hw09;

import java.util.Comparator;


public class CarYearComparator implements Comparator<Car> {

    public int compare(Car c1,  Car c2) {
        Comparator<Integer> yearComp = Comparator.<Integer>naturalOrder().reversed();
        return yearComp.compare(c1.getYear(), c2.getYear());
    }
}
