package edu.umb.cs680.hw10;

import java.util.HashMap;
import java.util.ArrayList;

class StockQuoteObservableMulticast {

    private ArrayList<StockQuoteObserver> observers;
    private boolean hasChanged;

    private HashMap<String, Float> tickerToQuote;

    public StockQuoteObservableMulticast() {
        observers = new ArrayList<StockQuoteObserver>();
        tickerToQuote = new HashMap<String, Float>();
    }

    public void addObserver(StockQuoteObserver o) {
        observers.add(o);
    }

    protected ArrayList<StockQuoteObserver> getObservers() {
        return observers;
    }

    public void setChanged() {
        hasChanged = true;
    }

    public void clearChanged() {
        hasChanged = false;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void notifyObservers(StockEvent obj) {
        for (StockQuoteObserver observer : observers) {
            observer.update(obj);
        }
        clearChanged();

    }

    public void changeQuote(String ticker, Float quote) {
        tickerToQuote.put(ticker, quote);
        setChanged();
        notifyObservers(new StockEvent(ticker, quote));
    }

    public Float getQuote(String ticker) {
        return tickerToQuote.get(ticker);
    }




}
