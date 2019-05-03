package edu.umb.cs680.hw10;

import java.util.ArrayList;

abstract class ObserverBase {
    
    private ArrayList<StockEvent> receivedStockEvents;
    private ArrayList<DJIAEvent> receivedDJIAEvents;

    protected ObserverBase() {
        receivedStockEvents = new ArrayList<StockEvent>();
        receivedDJIAEvents = new ArrayList<DJIAEvent>();    
}

    public ArrayList<StockEvent> getReceivedStockEvents() {
        return receivedStockEvents;
    }

    public StockEvent getLastReceivedStockEvent() {
        return receivedStockEvents.get(receivedStockEvents.size() - 1);
    }
    
    public ArrayList<DJIAEvent> getReceivedDJIAEvents() {
        return receivedDJIAEvents;
    }

    public DJIAEvent getLastReceivedDJIAEvent() {
        return receivedDJIAEvents.get(receivedDJIAEvents.size() - 1);
    }

    public void addReceivedStockEvent(StockEvent e) {
        receivedStockEvents.add(e);
    }

    public void addReceivedDJIAEvent(DJIAEvent e) {
        receivedDJIAEvents.add(e);
    }


}
