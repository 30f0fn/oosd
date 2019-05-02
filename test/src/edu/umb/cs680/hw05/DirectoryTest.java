package edu.umb.cs680.hw05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class DirectoryTest{
    private static FileSystem fs;
    private static LocalDateTime datetime;
    private static Directory root;
    private static Directory system;
    private static File a;
    private static File b;
    private static File c;
    private static Directory home;
    private static File d;
    private static Directory pictures;
    private static File e;
    private static File f;

    @BeforeAll
    public static void setUp() {
        fs = FileSystem.getFileSystem();
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new Directory(null, "root", datetime);
        system = new Directory(root, "system", datetime);
        a = new File(system, "a", 1, datetime);
        b = new File(system, "b", 2, datetime);
        c = new File(system, "c", 3, datetime);
        home = new Directory(root, "home", datetime);
        d = new File(home, "d", 4, datetime);
        pictures = new Directory(home, "pictures", datetime);
        e = new File(pictures, "e", 5, datetime);
        f = new File(pictures, "f", 6, datetime);
    }

    private String[] dirToStringArray(Directory d){

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
        assertArrayEquals(actual, actual );
    }


    @Test
    public void verifyDirectoryEqualitySystem() {
        String[] expected = {"false", "true",
                             "system", "root",
                             "0", "3", "6",
                             datetime.toString(),
        };
        Directory actual = system;
        assertArrayEquals(expected,
                          dirToStringArray(actual) ); }


    @Test
    public void verifyDirectoryEqualityHome() {
        String[] expected = {"false", "true",
                             "home", "root",
                             "0", "2", "15",
                             datetime.toString(),
        };
        Directory actual = home;
        assertArrayEquals(expected,
                          dirToStringArray(actual)); }



    @Test
    public void verifyDirectoryEqualityPictures() {
        String[] expected = {"false", "true",
                             "pictures", "home",
                             "0", "2", "11",
                             datetime.toString(), 
        };
        Directory actual = pictures;
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
        // for (int i = 0 ; i < 3; i++) {
            // assertEquals(expected[i], actual[i]);
        // }
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

    @AfterAll
    public static void doSomething() {
        fs.erase();
    }

}

