package edu.umb.cs680.hw10;

import java.util.LinkedList;

abstract class ObserverBase {
    
    private LinkedList<StockEvent> receivedStockEvents;
    private LinkedList<DJIAEvent> receivedDJIAEvents;

    protected ObserverBase() {
        receivedStockEvents = new LinkedList<StockEvent>();
        receivedDJIAEvents = new LinkedList<DJIAEvent>();    
    }

    public LinkedList<StockEvent> getReceivedStockEvents() {
        return receivedStockEvents;
    }

    public StockEvent getLastReceivedStockEvent() {
        return receivedStockEvents.get(receivedStockEvents.size() - 1);
    }
    
    public LinkedList<DJIAEvent> getReceivedDJIAEvents() {
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
