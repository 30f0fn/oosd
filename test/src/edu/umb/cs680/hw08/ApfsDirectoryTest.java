package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;
import java.util.ListIterator;

class ApfsDirectoryTest {
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
    }

    private String[] dirToStringArray(ApfsDirectory d){

        String[] dirInfo = {
            d.isFile().toString(), d.isDirectory().toString(),
            d.getName(), d.getParent() != null ? d.getParent().getName() : "",
            d.getSize().toString(), d.countChildren().toString(), d.getTotalSize().toString(),
            d.getCreationTime().toString(),
        };
        return dirInfo;
    }

    @Test
    public void verifyDirectoryEqualityRoot() {
        String[] expected = {"false", "true",
                             "root", "",
                             "0", "2", "21",
                             datetime.toString(),
        };
        String[] actual = dirToStringArray(root);
        assertArrayEquals(expected, actual );
    }


    @Test
    public void verifyDirectoryEqualitySystem() {
        String[] expected = {"false", "true",
                             "system", "root",
                             "0", "3", "6",
                             datetime.toString(),
        };
        ApfsDirectory actual = system;
        assertArrayEquals(expected,
                          dirToStringArray(actual) ); }


    @Test
    public void verifyDirectoryEqualityHome() {
        String[] expected = {"false", "true",
                             "home", "root",
                             "0", "3", "15",
                             datetime.toString(),
        };
        ApfsDirectory actual = home;
        assertArrayEquals(expected,
                          dirToStringArray(actual)); }



    @Test
    public void verifyDirectoryEqualityPictures() {
        String[] expected = {"false", "true",
                             "pictures", "home",
                             "0", "3", "11",
                             datetime.toString(), 
        };
        ApfsDirectory actual = pictures;
        assertArrayEquals(expected,
                          dirToStringArray(actual) ); }


    @Test
    public void verifyChildrenRoot() {
        FSElement[] expected = {system, home};
        int size = root.getChildren().size();
        FSElement[] actual = root.getChildren().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenSystem() {
        FSElement[] expected = {a, b, c};
        int size = system.getChildren().size();
        FSElement[] actual = system.getChildren().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenHome() {
        FSElement[] expected = {d, x, pictures};
        int size = home.getChildren().size();
        FSElement[] actual = home.getChildren().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenPictures() {
        FSElement[] expected = {e, f, y};
        int size = pictures.getChildren().size();
        FSElement[] actual = pictures.getChildren().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesRoot() {
        FSElement[] expected = {system, home};
        int size = root.getSubDirectories().size();
        FSElement[] actual = root.getSubDirectories().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesSystem() {
        FSElement[] expected = {};
        int size = system.getSubDirectories().size();
        FSElement[] actual = system.getSubDirectories().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesHome() {
        FSElement[] expected = {pictures};
        int size = home.getSubDirectories().size();
        FSElement[] actual = home.getSubDirectories().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifySubDirectoriesPictures() {
        FSElement[] expected = {};
        int size = pictures.getSubDirectories().size();
        FSElement[] actual = pictures.getSubDirectories().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesRoot() {
        FSElement[] expected = {};
        int size = root.getFiles().size();
        FSElement[] actual = root.getFiles().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesSystem() {
        FSElement[] expected = {a, b, c};
        int size = system.getFiles().size();
        FSElement[] actual = system.getFiles().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesHome() {
        FSElement[] expected = {d};
        int size = home.getFiles().size();
        FSElement[] actual = home.getFiles().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFilesPictures() {
        FSElement[] expected = {e, f};
        int size = pictures.getFiles().size();
        FSElement[] actual = pictures.getFiles().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyAppendChild() {
        ApfsFSElement[] expected = {a, b, c};
        ListIterator<ApfsFSElement> actual = system.getChildren().listIterator();
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

