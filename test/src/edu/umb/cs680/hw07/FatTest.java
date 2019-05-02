package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class FatTest {
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
    public void verifySetRootDir() {
        boolean expected;
        boolean actual;
        FatDirectory newRoot = new FatDirectory(null, "newRoot", datetime);
        assertFalse(fs.getRootDirs().contains(newRoot));
        assertEquals(1, fs.getRootDirs().size());
        fs.setRootDir(newRoot);
        assertTrue(fs.getRootDirs().contains(newRoot));
        assertEquals(2, fs.getRootDirs().size());
    }

    @Test
    public void verifyErase() {
        fs.erase();
        FSElement[] expected = {};
        int size = 0;
        FSElement[] actual = fs.getRootDirs().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
        setUp();
    }

    @AfterAll
    public static void doSomething() {
        fs.erase();
    }

}

