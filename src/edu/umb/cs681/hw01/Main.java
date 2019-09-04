package edu.umb.cs681.hw01;

import edu.umb.cs680.hw10.MyObservable;
import edu.umb.cs680.hw10.MyObserver;
import edu.umb.cs680.hw10.DJIAEvent;
import edu.umb.cs680.hw10.DJIAQuoteObserver;
import edu.umb.cs680.hw10.StockEvent;
import edu.umb.cs680.hw10.StockQuoteObserver;


public class Main {
    public static void main(String[] args) {
        StockQuoteObservable sqObservable = new StockQuoteObservable();
        MyObserver threeDeeStockQuoteRenderer = (Object e) ->
            System.out.printf("Wow a Three Dee rendering of stock %s as %f!\n",
                              ((StockEvent) e).getTicker(),
                              ((StockEvent) e).getQuote());
        sqObservable.addObserver((MyObserver) threeDeeStockQuoteRenderer);
        sqObservable.changeQuote("ENRN", 99f);

        DJIAQuoteObservable djiaObservable = new DJIAQuoteObservable();
        MyObserver threeDeeDJIARenderer = (Object e) -> {
            System.out.printf("Wow a Three Dee rendering of DJIA as %f!\n",
                              ((DJIAEvent) e).getQuote());
        };
        djiaObservable.addObserver((MyObserver) threeDeeDJIARenderer);
        djiaObservable.setQuote(9999f);
    }
    
}
