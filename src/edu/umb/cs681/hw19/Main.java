package edu.umb.cs681.hw19;

import edu.umb.cs681.hw17.ThreadSafeStockQuoteObservable;
import edu.umb.cs680.hw10.ThreeDeeObserver;
import edu.umb.cs680.hw10.StockEvent;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("HW 19...");
        ThreadSafeStockQuoteObservable SQObservable =
            new ThreadSafeStockQuoteObservable();
        ThreeDeeObserver threeDeeObserver = new ThreeDeeObserver();
        SQObservable.addObserver(threeDeeObserver);
        SQObservable.changeQuote("ENRN", 99f);
        StockEvent e = threeDeeObserver.getLastReceivedStockEvent();
        System.out.printf("\tMain.main: threeDeeObserver heard that %s is now %f\n",
                          e.getTicker(),
                          e.getQuote());
    }
}
