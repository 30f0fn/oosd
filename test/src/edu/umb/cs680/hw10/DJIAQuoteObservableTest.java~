package edu.umb.cs680.hw10;

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


class ThreeDeeObserverTest {

    private static DJIAQuoteObservable djiaQO;
    private static StockQuoteObservable stockQO;
    private static ThreeDeeObserver observer;

    @BeforeEach
    public static void setUp() {
        djiaQO = new DJIAQuoteObservable();
        stockQO = new StockQuoteObservable();
        observer = new ThreeDeeObserver();
    }

    @Test
    public void verifyUpdate() {
        StockEvent stockEvent = new StockEvent("enron", 23f);
        observer.update(stockEvent);
        assertEquals(stockEvent, observer.getReceivedStockEvents().get(0));
        DJIAEvent DJIAEvent = new DJIAEvent(23f);
        observer.update(DJIAEvent);
        assertEquals(DJIAEvent, observer.getReceivedDJIAEvents().get(0));
    }

}
