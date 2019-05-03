package edu.umb.cs680.hw13;

class DrawerClosedNotPlaying implements State {

    protected static DrawerClosedNotPlaying instance;
    protected static String display;

    static {
        display = "Drawer is closed; the player is stopped.";
    }

    private DrawerClosedNotPlaying() {}

    protected static DrawerClosedNotPlaying getInstance() {
        if (instance==null) {
            instance = new DrawerClosedNotPlaying();
        }
        return instance;
    }

    public void openCloseButtonPushed() {
        DVDPlayer.open();
        DVDPlayer.changeState(DrawerOpen.getInstance());
    }

    public void playButtonPushed() {
        DVDPlayer.play();
        System.out.println("hmm");
        DVDPlayer.changeState(DrawerClosedPlaying.getInstance());
        System.out.println(DVDPlayer.getState());
    }

    public void stopButtonPushed() {}

    // protected static String getDisplay() {
        // return display;
    // }

}
