package edu.umb.cs680.hw10;

abstract class SinglecastObserverBase extends ObserverBase implements MyObserver {

    public void update(Object event) {
        if (event instanceof StockEvent) {
            addReceivedStockEvent((StockEvent) event);
        } else if (event instanceof DJIAEvent) {
            addReceivedDJIAEvent((DJIAEvent) event);
        }
    }

}
