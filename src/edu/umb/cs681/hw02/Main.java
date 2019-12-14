package edu.umb.cs681.hw02;


import java.util.stream.Stream;
import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.Optional;

import edu.umb.cs680.hw14.Car;


public class Main {

    public static Car[] cars = {
        new Car("Toyota", "RAV4", 0, 2020, 26770f),
        new Car("Subaru", "Forester", 0, 2020, 25505f),
        new Car("Porsche", "Macan", 0, 2020, 51150f),
        new Car("Volvo", "XC-60", 0, 2020, 41145f),
        new Car("Mercedes-AMG", "GLC43", 0, 2020, 60495f)
    };


    private static <T> BinaryOperator<T>
        nullFriendlyComp(BinaryOperator<T> f) {
        return (T m, T n) -> m == null ? n : f.apply(m, n);
    }

    private static Optional<Float>
        getXmostCarPrice(Stream<Car> carStream, BinaryOperator<Float> moreX) {
        Float val = carStream.map((Car c) -> c.getPrice())
            .reduce(null, (nullFriendlyComp(moreX)));
        return Optional.ofNullable(val);
    }

    public static Integer getCarCount(Stream<Car> carStream) {
        return carStream.map((Car c) -> 1)
            .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {

        System.out.printf("HW02...\n");
        
        Integer carCount = getCarCount(Stream.of(cars));
        Float maxCarPrice = getXmostCarPrice(Stream.of(cars), Float::max).get();
        Float minCarPrice = getXmostCarPrice(Stream.of(cars), Float::min).get();

        System.out.printf("\tThe price range of the %d cars you considered is from %.2f to %.2f.\n",
                          carCount, maxCarPrice, minCarPrice);
    }
    
}

