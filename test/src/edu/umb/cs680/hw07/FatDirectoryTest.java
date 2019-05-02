package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;
import java.util.ListIterator;

class FatDirectoryTest {
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
        fs.initFileSystem("THEFAT", 1000000000);
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new FatDirectory(null, "ROOT", datetime);
        system = new FatDirectory(root, "SYSTEM", datetime);
        a = new FatFile(system, "a", 1, datetime);
        b = new FatFile(system, "b", 2, datetime);
        c = new FatFile(system, "c", 3, datetime);
        home = new FatDirectory(root, "HOME", datetime);
        d = new FatFile(home, "d", 4, datetime);
        pictures = new FatDirectory(home, "PICTURES", datetime);
        e = new FatFile(pictures, "e", 5, datetime);
        f = new FatFile(pictures, "f", 6, datetime);
    }

    private String[] dirToStringArray(FatDirectory d){

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
                             "ROOT", "",
                             "0", "2", "21",
                             datetime.toString(),
        };
        String[] actual = dirToStringArray(root);
        assertArrayEquals(actual, actual );
    }


    @Test
    public void verifyDirectoryEqualitySystem() {
        String[] expected = {"false", "true",
                             "SYSTEM", "ROOT",
                             "0", "3", "6",
                             datetime.toString(),
        };
        FatDirectory actual = system;
        assertArrayEquals(expected,
                          dirToStringArray(actual) ); }


    @Test
    public void verifyDirectoryEqualityHome() {
        String[] expected = {"false", "true",
                             "HOME", "ROOT",
                             "0", "2", "15",
                             datetime.toString(),
        };
        FatDirectory actual = home;
        assertArrayEquals(expected,
                          dirToStringArray(actual)); }



    @Test
    public void verifyDirectoryEqualityPictures() {
        String[] expected = {"false", "true",
                             "PICTURES", "HOME",
                             "0", "2", "11",
                             datetime.toString(), 
        };
        FatDirectory actual = pictures;
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
        FSElement[] expected = {d, pictures};
        int size = home.getChildren().size();
        FSElement[] actual = home.getChildren().toArray(new FSElement[size]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyChildrenPictures() {
        FSElement[] expected = {e, f};
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
        FatFSElement[] expected = {a, b, c};
        ListIterator<FatFSElement> actual = system.getChildren().listIterator();
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

