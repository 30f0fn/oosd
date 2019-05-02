package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class FatFileTest {
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
        fs.initFileSystem("theFAT", 1000000000);
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new FatDirectory(null, "ROOT", datetime);
        system = new FatDirectory(root, "SYSTEM", datetime);
        a = new FatFile(system, "A", 1, datetime);
        b = new FatFile(system, "B", 2, datetime);
        c = new FatFile(system, "C", 3, datetime);
        home = new FatDirectory(root, "HOME", datetime);
        d = new FatFile(home, "D", 4, datetime);
        pictures = new FatDirectory(home, "PICTURES", datetime);
        e = new FatFile(pictures, "E", 5, datetime);
        f = new FatFile(pictures, "F", 6, datetime);

    }

    private String[] fileToStringArray(FatFile f){
        String[] fileInfo = {
            f.isFile().toString(), f.isDirectory().toString(),
            f.getName(), f.getParent().getName(),
            f.getSize().toString(), 
            f.getCreationTime().toString(),
        };
        return fileInfo;
    }

    @Test
    public void verifyFileEqualityA() {
        String[] expected = {"true", "false",
                             "A", "SYSTEM",
                             "1",
                             datetime.toString(),
        };
        String[] actual = fileToStringArray(a);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileEqualityB() {
        String[] expected = {"true", "false",
                             "B", "SYSTEM",
                             "2",
                             datetime.toString(),
        };
        String[] actual = fileToStringArray(b);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileEqualityC() {
        String[] expected = {"true", "false",
                             "C", "SYSTEM",
                             "3",
                             datetime.toString(),
        };
        String[] actual = fileToStringArray(c);
        assertArrayEquals(expected, actual);
    }


    @Test
    public void verifyFileEqualityD() {
        String[] expected = {"true", "false",
                             "D", "HOME",
                             "4",
                             datetime.toString(),
        };
        String[] actual = fileToStringArray(d);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileEqualityE() {
        String[] expected = {"true", "false",
                             "E", "PICTURES",
                             "5",
                             datetime.toString(),
        };
        String[] actual = fileToStringArray(e);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileEqualityF() {
        String[] expected = {"true", "false",
                             "F", "PICTURES",
                             "6",
                             datetime.toString(),
        };
        String[] actual = fileToStringArray(f);
        assertArrayEquals(expected, actual);
    }
    
    @AfterAll
    public static void doSomething() {
        fs.erase();
    }

}

