package edu.umb.cs680.hw13;

class DrawerClosedPlaying implements State {

    protected static DrawerClosedPlaying instance;

    private DrawerClosedPlaying() {}

    static {
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

    public String getDisplay() {
        return "The player is playing.";
    }


}
