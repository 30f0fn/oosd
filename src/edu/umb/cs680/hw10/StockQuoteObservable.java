package edu.umb.cs680.hw10;

import java.util.HashMap;

public class StockQuoteObservable extends MyObservable {

    private HashMap<String, Float> tickerToQuote;

    public StockQuoteObservable() {
        tickerToQuote = new HashMap<String, Float>();
    }


    public void changeQuote(String ticker, Float quote) {
        tickerToQuote.put(ticker, quote);
        setChanged();
        notifyObservers(new StockEvent(ticker, quote));
    }

    public Float getQuote(String ticker) {
        return tickerToQuote.get(ticker);
    }

    public static void main(String[] args) {
        StockQuoteObservable SQObservable = new StockQuoteObservable();
        ThreeDeeObserver threeDeeObserver = new ThreeDeeObserver();
        SQObservable.addObserver(threeDeeObserver);
        SQObservable.changeQuote("ENRN", 99f);
        StockEvent e = threeDeeObserver.getLastReceivedStockEvent();
        System.out.printf("StockQuoteObservable: a registered ThreeDeeObserver instance heard that %s is now %f\n",
                          e.getTicker(),
                          e.getQuote());
    }

}
