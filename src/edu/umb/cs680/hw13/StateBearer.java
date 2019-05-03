package edu.umb.cs680.hw13;

class StateBearer {

    private State state;
    private static StateBearer instance;

    private StateBearer() {
        state = DrawerClosedNotPlaying.getInstance();
    }
    
    protected static StateBearer getInstance() {
        if (instance==null) {
            instance = new StateBearer();
        }
        return instance;
    }

    protected State getState() {
        return state;
    }

    protected void changeState(State newState) {
        state = newState;
    }
    
}
