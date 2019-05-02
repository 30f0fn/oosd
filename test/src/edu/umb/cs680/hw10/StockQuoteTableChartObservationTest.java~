package edu.umb.cs680.hw10;

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


class DJIAQuoteObservableTest {

    private static DJIAQuoteObservable observable;

    @BeforeEach
    public static void setUp() {
        observable = new DJIAQuoteObservable();
    }

    @Test
    public void verifyQuote() {
        observable.setQuote(12f);
        assertEquals(12f, observable.getQuote());
    }

    @Test
    public void verifyAddObserver(){
        ThreeDeeObserver obsvr = new ThreeDeeObserver();
        observable.addObserver(obsvr);
        assertEquals(obsvr, observable.getObservers());
    }

}
