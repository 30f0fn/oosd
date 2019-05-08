package edu.umb.cs680.hw13;

class DrawerOpen implements State {

    // todo should protected be private?
    protected static DrawerOpen instance = new DrawerOpen();

    // static {
    //     instance = new DrawerOpen();
    // }

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

    public String getDisplay() {
        return "Drawer is open.";
    }

}
