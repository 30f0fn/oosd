package edu.umb.cs681.hw02;

import java.util.stream.Stream;
import java.util.function.BinaryOperator;
import java.util.Optional;

import edu.umb.cs680.hw14.Car;


public class StreamWithExtra<T>  {

    // private static <T> BinaryOperator<T> nullFriendlyComp(BinaryOperator<T> f) {
    //     return (T m, T n) -> m == null ? n : f.apply(m, n);
    // }

    // public static <T> Optional<Float>
    //     getBest(BinaryOperator<T> betterOf) {
    //     Float val = this.reduce(null, (nullFriendlyComp(betterOf)));
    //     return Optional.ofNullable(val);
    // }

    // public static int countByHand() {
    //     return this.map((Car c) -> 1)
    //         .reduce((int m, int n) -> m + n);
    // }
}
