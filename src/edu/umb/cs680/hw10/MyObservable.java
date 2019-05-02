package edu.umb.cs680.hw10;

import java.util.ArrayList;

public class MyObservable {

    private ArrayList<MyObserver> observers;
    private boolean hasChanged;

    public void MyObservable() {
        observers = new ArrayList<MyObserver>();
    }

    public void addObserver(MyObserver o) {
        observers.add(o);
    }

    protected ArrayList<MyObserver> getObservers() {
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

    public void notifyObservers(Object obj) {
        for (MyObserver observer : observers) {
            observer.update(obj);
        }
        clearChanged();

    }
}
