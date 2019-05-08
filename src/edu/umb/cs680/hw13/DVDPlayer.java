package edu.umb.cs680.hw13;

// stateBearer holds the one non-static field

public interface DVDPlayer {
    
    StateBearer stateBearer = StateBearer.getInstance();

    public static void changeState(State newState) {
        stateBearer.changeState(newState);
    }

    public static State getState() {
        return stateBearer.getState();
    }

    public static void openCloseButtonPushed() {
        getState().openCloseButtonPushed();
    }

    public static void playButtonPushed() {
        getState().playButtonPushed();
    }

    public static void stopButtonPushed() {
        getState().stopButtonPushed();
    }

    public static void open() {
    }

    public static void close() {
    }

    public static void play() {
    }

    public static void stop() {
    }

    public static void lightDisplay() {
        stateBearer.lightDisplay();
    }

    public static void main(String[] args) {
        lightDisplay();
        play();
        changeState(DrawerClosedPlaying.getInstance());
        lightDisplay();
        play();
    }
}


