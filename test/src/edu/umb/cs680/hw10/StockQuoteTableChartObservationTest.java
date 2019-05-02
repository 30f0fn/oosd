package edu.umb.cs680.hw10;

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


class StockQuoteTableChartObservationTest {

    private static StockQuoteObservable observable;
    private static TableChartObserver observer;

    @BeforeEach
    public static void setUp() {
        observable = new StockQuoteObservable();
        observer = new TableChartObserver();
    }

    @Test
    public void verifyChangeQuote() {
        observable.addObserver(observer);
        observable.changeQuote("ENRN", 99f);
        StockEvent e = observer.getReceivedStockEvents().get(0);
        assertEquals("ENRN", e.getTicker());
        assertEquals(99f, e.getQuote());
}

    @Test
    public void verifyAddObserver(){
        ThreeDeeObserver obsvr = new ThreeDeeObserver();
        observable.addObserver(obsvr);
        assertEquals(obsvr, observable.getObservers());
    }

}
