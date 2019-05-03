package edu.umb.cs680.hw11;

import java.util.ArrayList;

public class ObservableEnhanced<T> {

    private ArrayList<ObserverEnhanced<T>> observers;
    private boolean hasChanged;

    public ObservableEnhanced() {
        observers = new ArrayList<ObserverEnhanced<T>>();
    }

    public void addObserver(ObserverEnhanced<T> o) {
        // System.out.println(o);
        observers.add(o);
    }

    protected ArrayList<ObserverEnhanced<T>> getObservers() {
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

    public void notifyObservers(T event) {
        for (ObserverEnhanced<T> observer : observers) {
            observer.update(event);
        }
        clearChanged();
    }
}
