package edu.umb.cs680.hw10;

import java.util.HashMap;


class StockQuoteObservable extends MyObservable {

    private HashMap<String, Float> tickerToQuote;

    public void changeQuote(String ticker, Float quote) {
        tickerToQuote.put(ticker, quote);
        setChanged();
        notifyObservers(new StockEvent(ticker, quote));
    }

    public Float getQuote(String ticker) {
        return tickerToQuote.get(ticker);
    }




}
