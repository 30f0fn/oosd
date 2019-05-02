package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class ApfsFileTest {
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
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new ApfsDirectory(null, "root", datetime);
        system = new ApfsDirectory(root, "system", datetime);
        a = new ApfsFile(system, "a", 1, datetime);
        b = new ApfsFile(system, "b", 2, datetime);
        c = new ApfsFile(system, "c", 3, datetime);
        home = new ApfsDirectory(root, "home", datetime);
        d = new ApfsFile(home, "d", 4, datetime);
        x = new ApfsLink(home, "x", datetime, system);
        // pictures = new ApfsDirectory(home, "pictures", datetime);
        // e = new ApfsFile(pictures, "e", 5, datetime);
        // f = new ApfsFile(pictures, "f", 6, datetime);
        // y = new ApfsLink(pictures, "y", datetime, e);
    }

    // private String[] fileToStringArray(ApfsFile f){
    //     String[] fileInfo = {
    //         f.isFile().toString(), f.isDirectory().toString(),
    //         f.getName(), f.getParent().getName(),
    //         f.getSize().toString(), 
    //         f.getCreationTime().toString(),
    //     };
    //     return fileInfo;
    // }

    @Test
    public void dumbTest() {
        assertEquals(1, 1);
    }

    // @Test
    // public void verifyFileEqualityA() {
    //     String[] expected = {"true", "false",
    //                          "a", "system",
    //                          "1",
    //                          datetime.toString(),
    //     };
    //     String[] actual = fileToStringArray(a);
    //     assertArrayEquals(expected, actual);
    // }

    // @Test
    // public void verifyFileEqualityB() {
    //     String[] expected = {"true", "false",
    //                          "b", "system",
    //                          "2",
    //                          datetime.toString(),
    //     };
    //     String[] actual = fileToStringArray(b);
    //     assertArrayEquals(expected, actual);
    // }

    // @Test
    // public void verifyFileEqualityC() {
    //     String[] expected = {"true", "false",
    //                          "c", "system",
    //                          "3",
    //                          datetime.toString(),
    //     };
    //     String[] actual = fileToStringArray(c);
    //     assertArrayEquals(expected, actual);
    // }


    // @Test
    // public void verifyFileEqualityD() {
    //     String[] expected = {"true", "false",
    //                          "d", "home",
    //                          "4",
    //                          datetime.toString(),
    //     };
    //     String[] actual = fileToStringArray(d);
    //     assertArrayEquals(expected, actual);
    // }

    // @Test
    // public void verifyFileEqualityE() {
    //     String[] expected = {"true", "false",
    //                          "e", "pictures",
    //                          "5",
    //                          datetime.toString(),
    //     };
    //     String[] actual = fileToStringArray(e);
    //     assertArrayEquals(expected, actual);
    // }

    // @Test
    // public void verifyFileEqualityF() {
    //     String[] expected = {"true", "false",
    //                          "f", "pictures",
    //                          "6",
    //                          datetime.toString(),
    //     };
    //     String[] actual = fileToStringArray(f);
    //     assertArrayEquals(expected, actual);
    // }
    
    // @AfterAll
    // public static void doSomething() {
    //     fs.erase();
    // }

}

