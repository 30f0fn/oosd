package edu.umb.cs680.hw11;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


class DrinkingTest {

    private static ObservableEnhanced<Pour> bartender;
    private static ObservableEnhanced<Sip> drinker;
    private static Glass glass;

    @BeforeAll
    public static void setUp() {
        bartender = new ObservableEnhanced<Pour>();
        drinker = new ObservableEnhanced<Sip>();
        glass = new Glass(100f);
        bartender.addObserver(glass::observePour);
        drinker.addObserver(glass::observeSip);
    }

    @Test
    public void verifyGlass() {
        assertEquals(0f, glass.measureBeer());
        Pour pour = new Pour(80f);
        bartender.setChanged();
        bartender.notifyObservers(pour);
        assertEquals(80f, glass.measureBeer());
        Sip sip = new Sip(10f);
        drinker.setChanged();
        drinker.notifyObservers(sip);
        assertEquals(70f, glass.measureBeer());
        glass.dump();
        assertEquals(0, glass.measureBeer());
    }

    @Test
    public void verifySpill() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
                     ()->{
                         Pour pour = new Pour(120f);
                         bartender.setChanged();
                         bartender.notifyObservers(pour);                 
                     });
        assertTrue(thrown.getMessage().contains("spilled"));
    }

    @Test
    public void verifyScold() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
                     ()->{
                         Sip sip;
                         for (int i = 0; i < 11; i++) {
                             sip = new Sip(10);
                             drinker.setChanged();
                             drinker.notifyObservers(sip);
                         }});
        assertTrue(thrown.getMessage().contains("negative"));
    }



}
