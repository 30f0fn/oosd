package edu.umb.cs680.hw14;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Stream;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.BiFunction;

class CarParetoCompBuilder {

    LinkedList<Comparator<Car>> simpleCompFcns;

    protected CarParetoCompBuilder() {
        simpleCompFcns = new LinkedList<Comparator<Car>>();
    }

    protected void addCompFcn(Comparator<Car> compFcn) {
        simpleCompFcns.add(compFcn);
    }


    protected Comparator<Car> buildFcn() {
        // idea: shift stream of values of comp(c1, c2) from -1, 0, 1 to get stream s1 with values in 0, 1, 2
        // also likewise shift comp(c1, c2) from  -1, 0, 1 to get stream s2 of values in 2, 1, 0
        // now put p1 := product of all s1-values, p2 := product of all s2-values
        // then p1 == 1 iff all s1-values are 1 iff all s2-values are 1 iff p2 == 1;
        //  if p1 > 1, then s1 has a 0-value so p2 == 0, and likewise if p2 > 1, then p1 == 0.
        // it follows that if p1 - p2 > 0, then p1 > 1 and p2 == 0, so c1 has a winning comp but no losers,
        // similarly if p1 - p2 < 0, then p1 has a losing comp but no winners;
        // finally if p1 - p2 == 0, then either p1 == p2 == 0, or p1 == p2 == 1,
        //     so they are either incomparable or tied
        // therefore, (c1, c2) -> p1 - p2 is a pareto-dominance comparator.
        return (c1, c2) ->
            simpleCompFcns.stream()
            .map(comp -> 1 + comp.compare(c1, c2))
            .reduce(1, (x, y) -> x * y)
            -
            simpleCompFcns.stream()
            .map(comp -> 1 - comp.compare(c1, c2))
            .reduce(1, (x, y) -> x * y);
    }

}
