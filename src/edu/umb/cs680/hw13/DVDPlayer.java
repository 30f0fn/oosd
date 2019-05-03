package edu.umb.cs680.hw13;

// I found singleton implementation here to be ugly, so used stateBearer to hold the one non-static field

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

    // public void lightDisplay() {
        // System.out.println(state.getDisplay());
    // }

    public static void main(String[] args) {
        // player.lightDisplay();
        play();
        changeState(DrawerClosedPlaying.getInstance());
        // player.lightDisplay();
        play();
    }
}


