package edu.umb.cs680.hw15;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;
import java.util.ListIterator;

class ApfsReverseAlphaDirectoryTest {
    private static APFS fs;
    private static LocalDateTime datetime;
    private static ApfsDirectory root;
    private static ApfsDirectory system;
    private static ApfsFile a;
    private static ApfsFile b;
    private static ApfsFile c;
    private static ApfsDirectory home;
    private static ApfsFile d;
    private static ApfsLink x;
    private static ApfsDirectory pictures;
    private static ApfsFile e;
    private static ApfsFile f;
    private static ApfsLink y;

    private static ApfsNamesReverseAlphabeticallyComparator comp;


    @BeforeAll
    public static void setUp() {
        fs = APFS.getFileSystem();
        fs.erase();
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new ApfsDirectory(null, "root", datetime);
        system = new ApfsDirectory(root, "system", datetime);
        a = new ApfsFile(system, "a", 1, datetime);
        b = new ApfsFile(system, "b", 2, datetime);
        c = new ApfsFile(system, "c", 3, datetime);
        home = new ApfsDirectory(root, "home", datetime);
        d = new ApfsFile(home, "d", 4, datetime);
        x = new ApfsLink(home, "x", datetime, system);
        pictures = new ApfsDirectory(home, "pictures", datetime);
        e = new ApfsFile(pictures, "e", 5, datetime);
        f = new ApfsFile(pictures, "f", 6, datetime);
        y = new ApfsLink(pictures, "y", datetime, e);

        comp = new ApfsNamesReverseAlphabeticallyComparator();
    }

    @Test
    public void verifyChildrenRoot() {
        FSElement[] expected = {system, home};
        int size = root.getChildren().size();
        FSElement[] actual = root.getChildren(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenSystem() {
        FSElement[] expected = {c, b, a};
        int size = system.getChildren().size();
        FSElement[] actual = system.getChildren(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenHome() {
        FSElement[] expected = {x, pictures, d};
        int size = home.getChildren().size();
        FSElement[] actual = home.getChildren(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenPictures() {
        FSElement[] expected = {y, f, e};
        int size = pictures.getChildren().size();
        FSElement[] actual = pictures.getChildren(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesRoot() {
        FSElement[] expected = {system, home};
        int size = root.getSubDirectories().size();
        FSElement[] actual = root.getSubDirectories(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesSystem() {
        FSElement[] expected = {};
        int size = system.getSubDirectories().size();
        FSElement[] actual = system.getSubDirectories(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesHome() {
        FSElement[] expected = {pictures};
        int size = home.getSubDirectories().size();
        FSElement[] actual = home.getSubDirectories(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesPictures() {
        FSElement[] expected = {};
        int size = pictures.getSubDirectories().size();
        FSElement[] actual = pictures.getSubDirectories(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesRoot() {
        FSElement[] expected = {};
        int size = root.getFiles().size();
        FSElement[] actual = root.getFiles(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesSystem() {
        FSElement[] expected = {c, b, a};
        int size = system.getFiles().size();
        FSElement[] actual = system.getFiles(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesHome() {
        FSElement[] expected = {d};
        int size = home.getFiles().size();
        FSElement[] actual = home.getFiles(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesPictures() {
        FSElement[] expected = {f, e};
        int size = pictures.getFiles().size();
        FSElement[] actual = pictures.getFiles(comp).toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyAppendChild() {
        ApfsFSElement[] expected = {c, b, a};
        ListIterator<ApfsFSElement> actual = system.getChildren(comp).listIterator();
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], actual.next());
        }
        setUp();
    }

    @AfterAll
    public static void doSomething() {
        fs.erase();
    }

}

