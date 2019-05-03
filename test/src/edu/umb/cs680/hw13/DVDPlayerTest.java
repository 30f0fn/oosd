package edu.umb.cs680.hw13;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


class DVDPlayerTest {

    private static State notPlaying;
    private static State playing;
    private static State open;
    

    @BeforeAll
    public static void setUp() {
        notPlaying = DrawerClosedNotPlaying.getInstance();
        playing = DrawerClosedPlaying.getInstance();
        open = DrawerOpen.getInstance();

    }

    @Test
    public void verifyPlayer() {
        assertEquals(notPlaying, DVDPlayer.getState());
        DVDPlayer.stopButtonPushed();
        assertEquals(notPlaying, DVDPlayer.getState());
        DVDPlayer.playButtonPushed();
        assertEquals(playing, DVDPlayer.getState());
        DVDPlayer.openCloseButtonPushed();
        assertEquals(playing, DVDPlayer.getState());
        DVDPlayer.playButtonPushed();
        assertEquals(playing, DVDPlayer.getState());
        DVDPlayer.stopButtonPushed();
        assertEquals(notPlaying, DVDPlayer.getState());
        DVDPlayer.openCloseButtonPushed();
        assertEquals(open, DVDPlayer.getState());
        DVDPlayer.stopButtonPushed();
        assertEquals(open, DVDPlayer.getState());
        DVDPlayer.openCloseButtonPushed();
        assertEquals(notPlaying, DVDPlayer.getState());
        DVDPlayer.openCloseButtonPushed();
        DVDPlayer.playButtonPushed();
        assertEquals(playing, DVDPlayer.getState());        
    }


}
