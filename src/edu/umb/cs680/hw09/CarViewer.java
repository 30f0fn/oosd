package edu.umb.cs680.hw09;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.lang.System;

public class CarViewer {

    private static Comparator<Car> priceComp; 
    private static Comparator<Car> mileageComp; 
    private static Comparator<Car> yearComp; 
    private static CarParetoComparator paretoComp;
    private CarDominanceCountComparator domCountComp;

    private ArrayList<Car> cars;

    static {
        priceComp = new CarPriceComparator();
        mileageComp = new CarMileageComparator();
        yearComp = new CarYearComparator();
        paretoComp = new CarParetoComparator();
        paretoComp.addComparator(yearComp);        
        paretoComp.addComparator(mileageComp);
        paretoComp.addComparator(priceComp);
    }

    public CarViewer() {
        cars = new ArrayList<Car>();
        domCountComp = new CarDominanceCountComparator(this);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    protected CarDominanceCountComparator getDomCountComp() {
        return domCountComp;
    }

    public void addCar(Car car) {
        cars.add(car);
        domCountComp.addCar(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        domCountComp.removeCar(car);
    }

    public ArrayList<Car> byPrice() {
        Collections.sort(cars, priceComp);
        return cars;
    }

    public ArrayList<Car> byYear() {
        Collections.sort(cars, yearComp);
        return cars;
    }

    public ArrayList<Car> byMileage() {
        Collections.sort(cars, mileageComp);
        return cars;
    }

    public ArrayList<Car> byDominanceCount() {
        Collections.sort(cars, domCountComp);
        return cars;
    }

    protected int paretoCompare(Car car1, Car car2) {
        return paretoComp.compare(car1, car2);
    }

    public String toString() {
        return "CarViewer: the hw9 CarViewer instance";
    }

    public static void main(String[] args) {
        CarViewer viewer = new CarViewer();
        System.out.println(viewer);
    }

}
