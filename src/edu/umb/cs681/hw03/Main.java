package edu.umb.cs681.hw03;

import java.util.stream.Stream;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import edu.umb.cs680.hw14.Car;

import edu.umb.cs680.hw11.ObservableEnhanced;
import edu.umb.cs680.hw11.ObserverEnhanced;


public class Main {


    private static String carStreamToString(Stream<Car> carStream) {
        String lineStarter = "\n\t\t";
        return lineStarter + carStream.map((Car c) -> c.getMake() + " " + c.getModel())
            .collect(Collectors.joining(";"+lineStarter));
    }


    public static void main(String[] args) {

        System.out.printf("HW03...\n");

        CarViewerWithStream viewer = new CarViewerWithStream();

        viewer.addCar(new Car("Toyota", "RAV4", 31000, 2016, 18998));
        viewer.addCar(new Car("Subaru", "Forester", 90000, 2012, 13998));
        viewer.addCar(new Car("Porsche", "Macan", 46000, 2015, 37998));
        viewer.addCar(new Car("Volvo", "XC-60", 41000, 2014, 22998));
        viewer.addCar(new Car("Mercedes-AMG", "GLC43", 21000, 2018, 51998));
        
        Stream<Car> byPrice = viewer.streamCarsByPrice();
        Stream<Car> byYear = viewer.streamCarsByYear();        
        Stream<Car> byMileage = viewer.streamCarsByMileage();        
        Stream<Car> byDominanceCount = viewer.streamCarsByDominanceCount();        

            
        System.out.printf("\tYour cars by price:%s\n",
                          carStreamToString(byPrice));
        System.out.printf("\tYour cars by year:%s\n",
                          carStreamToString(byYear));
        System.out.printf("\tYour cars by mileage:%s\n",
                          carStreamToString(byMileage));
        System.out.printf("\tYour cars by dom count:%s\n",
                          carStreamToString(byDominanceCount));


    }
    
}


