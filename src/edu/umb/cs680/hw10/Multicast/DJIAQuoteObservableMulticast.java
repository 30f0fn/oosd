package edu.umb.cs680.hw10;

import java.util.ArrayList;

class DJIAQuoteObservableMulticast {

    private ArrayList<DJIAQuoteObserver> observers;
    private boolean hasChanged;

    private float quote;


    public DJIAQuoteObservableMulticast() {
        observers = new ArrayList<DJIAQuoteObserver>();
    }

    public void addObserver(DJIAQuoteObserver o) {
        observers.add(o);
    }

    protected ArrayList<DJIAQuoteObserver> getObservers() {
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

    public void notifyObservers(DJIAEvent obj) {
        for (DJIAQuoteObserver observer : observers) {
            observer.update(obj);
        }
        clearChanged();

    }

    public float getQuote() {
        return quote;
    }

    public void setQuote(float newQuote) {
        quote = newQuote;
        setChanged();
        notifyObservers(new DJIAEvent(quote));
        
    }

}
