package edu.umb.cs680.hw13;

class DrawerOpen implements State {

    protected static DrawerOpen instance;
    protected static String display;

    static {
        display = "Drawer is open.";
        instance = new DrawerOpen();
    }

    private DrawerOpen() {}

    protected static DrawerOpen getInstance() {
        return instance;
    }

    public void openCloseButtonPushed() {
        DVDPlayer.close();
        DVDPlayer.changeState(DrawerClosedNotPlaying.getInstance());
    }

    public void playButtonPushed() {
        DVDPlayer.close();
        DVDPlayer.play();
        DVDPlayer.changeState(DrawerClosedPlaying.getInstance());
    }

    public void stopButtonPushed() { }

}
