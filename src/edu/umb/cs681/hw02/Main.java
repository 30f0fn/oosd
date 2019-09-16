package edu.umb.cs681.hw02;


import java.util.stream.Stream;
import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.Optional;

import edu.umb.cs680.hw14.Car;


public class Main {

    public static <T> BinaryOperator<T> nullFriendlyComp(BinaryOperator<T> f) {
        return (T m, T n) -> m == null ? n : f.apply(m, n);
    }

    public static Optional<Float> getMinCarPrice(Stream<Car> carStream) {
        Float val = carStream.map((Car c) -> c.getPrice())
            .reduce(null, (nullFriendlyComp(Float::min)));
        return Optional.ofNullable(val);
    }

    public static Optional<Float> getMaxCarPrice(Stream<Car> carStream) {
        Float val = carStream.map((Car c) -> c.getPrice())
            .reduce(null, (nullFriendlyComp(Float::max)));
        return Optional.ofNullable(val);
    }

    public static Integer getCarCount(Stream<Car> carStream) {
        return carStream.map((Car c) -> 1)
            .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {

        System.out.printf("HW02...\n");
        Car[] cars = {new Car("Toyota", "RAV4", 0, 2020, 26770f),
                      new Car("Subaru", "Forester", 0, 2020, 25505f),
                      new Car("Porsche", "Macan", 0, 2020, 51150f),
                      new Car("Volvo", "XC-60", 0, 2020, 41145f),
                      new Car("Mercedes-AMG", "GLC43", 0, 2020, 60495f)};

        
        Integer carCount = getCarCount(Stream.of(cars));
        Float maxCarPrice = getMinCarPrice(Stream.of(cars)).get();
        Float minCarPrice = getMaxCarPrice(Stream.of(cars)).get();

        System.out.printf("\tThe price range of the %d cars you considered is from %.2f to %.2f.\n",
                          carCount, maxCarPrice, minCarPrice);
    }
    
}

