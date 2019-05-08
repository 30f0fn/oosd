package edu.umb.cs680.hw14;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Stream;

class CarParetoCompBuilder {

    LinkedList<Comparator<Car>> simpleCompFcns;

    protected CarParetoCompBuilder() {
        simpleCompFcns = new LinkedList<Comparator<Car>>();
    }

    protected void addCompFcn(Comparator<Car> compFcn) {
        simpleCompFcns.add(compFcn);
    }

    private int firstHasWinner(Car c1, Car c2) {
        return simpleCompFcns.stream()
            .anyMatch(comp -> (comp.compare(c1,c2) < 0))
            ? 1 : 0;
    }

    public int compare(Car c1, Car c2) {
        return firstHasWinner(c2, c1) - firstHasWinner(c1, c2);
    }


}
