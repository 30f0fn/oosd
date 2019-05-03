package edu.umb.cs680.hw14;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.lang.System;
import java.util.function.BiFunction;

public class CarViewer {

    private static CarParetoCompBuilder paretoCompBuilder;
    private static Comparator<Car> paretoCompFcn;
    private CarDominanceCountTable domCountTable;

    private ArrayList<Car> cars;

    static {
        CarParetoCompBuilder paretoCompBuilder = new CarParetoCompBuilder();
        paretoCompBuilder.addCompFcn(Comparator.comparingDouble(car -> car.getPrice()));        
        paretoCompBuilder.addCompFcn(Comparator.comparing(car -> car.getMileage()));
        paretoCompBuilder.addCompFcn(Comparator.comparing(car -> -car.getYear()));
        paretoCompFcn = paretoCompBuilder::compare;
    }

    public CarViewer() {
        cars = new ArrayList<Car>();
        domCountTable = new CarDominanceCountTable(this);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
        domCountTable.addCar(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        domCountTable.removeCar(car);
    }

    public ArrayList<Car> byPrice() {
        Collections.sort(cars, Comparator.comparingDouble(car -> car.getPrice()));
        return cars;
    }

    public ArrayList<Car> byYear() {
        Collections.sort(cars, Comparator.comparing(car -> -car.getYear()));
        return cars;
    }

    public ArrayList<Car> byMileage() {
        Collections.sort(cars, Comparator.comparing(car -> car.getMileage()));
        return cars;
    }

    protected int paretoCompare(Car car1, Car car2) {
        return paretoCompFcn.compare(car1, car2);
    }

    protected int getDomCount(Car car) {
        return domCountTable.getDomCount(car);
    }

    public ArrayList<Car> byDominanceCount() {
        Collections.sort(cars,
                         (car1, car2) ->
                         domCountTable.getDomCount(car2) -
                         domCountTable.getDomCount(car1));
        return cars;
    }

    public String toString() {
        return "CarViewer: the hw14 CarViewer instance";
    }

    public static void main(String[] args) {
        CarViewer viewer = new CarViewer();
        System.out.println(viewer);
    }

}
