package edu.umb.cs680.hw10;

import java.util.ArrayList;

class TableChartObserver implements MyObserver {

    private ArrayList<StockEvent> receivedStockEvents;
    private ArrayList<DJIAEvent> receivedDJIAEvents;

    public ArrayList<StockEvent> getReceivedStockEvents() {
        return receivedStockEvents;
    }

    public ArrayList<DJIAEvent> getReceivedDJIAEvents() {
        return receivedDJIAEvents;
    }

    public void update(Object event) {
        if (event instanceof StockEvent) {
            receivedStockEvents.add((StockEvent) event);
        } else if (event instanceof DJIAEvent) {
            receivedDJIAEvents.add((DJIAEvent) event);
        }
    }

}
