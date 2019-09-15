package edu.umb.cs681.hw03;

import java.util.stream.Stream;
import java.util.Comparator;

import edu.umb.cs680.hw14.CarViewer;
import edu.umb.cs680.hw14.Car;

public class CarViewerWithStream extends CarViewer {

    private Stream<Car> streamCars() {
        return getCars().stream();
    }
    
    public Stream<Car> streamCarsByPrice() {
        return streamCars().sorted(Comparator.comparingDouble(car -> car.getPrice()));
    }

    public Stream<Car> streamCarsByYear() {
        return streamCars().sorted(Comparator.comparing(car -> -car.getYear()));
    }

    public Stream<Car> streamCarsByMileage() {
        return streamCars().sorted(Comparator.comparing(car -> car.getMileage()));
    }

    public Stream<Car> streamCarsByDominanceCount() {
        return streamCars().sorted((car1, car2) ->
                                  getDomCount(car2) -
                                  getDomCount(car1));
    }
}
