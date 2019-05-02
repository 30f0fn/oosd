package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class FatFSElementTest {
    private static FAT fs;
    private static LocalDateTime datetime;
    private static FatDirectory root;
    private static FatDirectory system;
    private static FatFile a;
    private static FatFile b;
    private static FatFile c;
    private static FatDirectory home;
    private static FatFile d;
    private static FatDirectory pictures;
    private static FatFile e;
    private static FatFile f;

    @BeforeAll
    public static void setUp() {
        fs = new FAT();
        fs.initFileSystem("aFatSystem", 987654321);
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new FatDirectory(null, "root", datetime);
        system = new FatDirectory(root, "system", datetime);
        a = new FatFile(system, "a", 1, datetime);
        b = new FatFile(system, "b", 2, datetime);
        c = new FatFile(system, "c", 3, datetime);
        home = new FatDirectory(root, "home", datetime);
        d = new FatFile(home, "d", 4, datetime);
        pictures = new FatDirectory(home, "pictures", datetime);
        e = new FatFile(pictures, "e", 5, datetime);
        f = new FatFile(pictures, "f", 6, datetime);
    }

    
    @Test
    public void verifyGetParentHome() {
        FatDirectory expected = root;
        FatDirectory actual = home.getParent();
        assertEquals(expected, actual);
    }


    @Test
    public void verifyElemTypeHome() {
        Boolean[] expected = {home.isDirectory(), home.isFile()};
        Boolean actual[] = {true, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyElemTypeA() {
        Boolean[] expected = {a.isDirectory(), a.isFile()};
        Boolean actual[] = {false, true};
        assertArrayEquals(expected, actual);
    }





    @AfterAll
    public static void doSomething() {
        fs.erase();
    }


}

