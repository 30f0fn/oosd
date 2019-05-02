package edu.umb.cs680.hw10;

import java.util.ArrayList;

class LineChartObserver implements MyObserver {

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
        // System.out.printf("LineChart received %d: quote is %f\n",
        //                   ((Event) event).getClass().getName(),
        //                   ((Event) event).getQuote());
    }

}
