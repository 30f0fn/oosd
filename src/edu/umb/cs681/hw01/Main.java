package edu.umb.cs681.hw01;

import edu.umb.cs680.hw10.MyObserver;
import edu.umb.cs680.hw10.DJIAEvent;
import edu.umb.cs680.hw10.DJIAQuoteObserver;
import edu.umb.cs680.hw10.StockEvent;
import edu.umb.cs680.hw10.StockQuoteObserver;

import edu.umb.cs680.hw11.ObservableEnhanced;
import edu.umb.cs680.hw11.ObserverEnhanced;


public class Main {
    public static void main(String[] args) {

        //////////////////////////////////////////////////////////
        // cs 680 homework 10 - observer pattern implementation //
        //////////////////////////////////////////////////////////
        System.out.printf("HW01...\n");
        StockQuoteObservable sqObservable = new StockQuoteObservable();
        MyObserver threeDeeStockQuoteRenderer = (Object e) ->
            System.out.printf("\tWow a Three Dee rendering of stock %s at %.2f!\n",
                              ((StockEvent) e).getTicker(),
                              ((StockEvent) e).getQuote());
        sqObservable.addObserver((MyObserver) threeDeeStockQuoteRenderer);
        sqObservable.changeQuote("ENRN", 99f);

        DJIAQuoteObservable djiaObservable = new DJIAQuoteObservable();
        MyObserver threeDeeDJIARenderer = (Object e) -> {
            System.out.printf("\tWow a Three Dee rendering of DJIA at %.2f!\n",
                              ((DJIAEvent) e).getQuote());
        };
        djiaObservable.addObserver((MyObserver) threeDeeDJIARenderer);
        djiaObservable.setQuote(9999f);

        
        ///////////////////////////////////////////
        // cs 680 homework 11 - generic observer //
        ///////////////////////////////////////////
        StockQuoteObservableEnhanced sqObservableE =
            new StockQuoteObservableEnhanced();
        ObserverEnhanced<StockEvent> fourDeeStockQuoteRendererE =
            (StockEvent e) ->
            System.out.printf("\tWow a Four Dee rendering of stock %s at %.2f!\n",
                              e.getTicker(),
                              e.getQuote());
        sqObservableE.addObserver(fourDeeStockQuoteRendererE);
        sqObservableE.changeQuote("ENRN", 9f);

    }
    
}
