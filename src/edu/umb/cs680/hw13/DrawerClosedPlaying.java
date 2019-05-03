package edu.umb.cs680.hw13;

class DrawerClosedPlaying implements State {

    protected static DrawerClosedPlaying instance;
    protected static String display;

    private DrawerClosedPlaying() {}

    static {
        display = "The player is playing.";
        instance = new DrawerClosedPlaying();
    }

    public void openCloseButtonPushed() {
    }

    protected static DrawerClosedPlaying getInstance() {
        return instance;
    }

    public void playButtonPushed() {}

    public void stopButtonPushed() {
        DVDPlayer.stop();
        DVDPlayer.changeState(DrawerClosedNotPlaying.getInstance());
    }

}
