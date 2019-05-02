package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class LinkTest {
    private static FileSystem fs;
    private static LocalDateTime datetime;
    private static Directory root;
    private static Directory system;
    private static File a;
    private static File b;
    private static File c;
    private static Directory home;
    private static File d;
    private static Link x;
    private static Directory pictures;
    private static File e;
    private static File f;
    private static Link y;

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
        x = new Link(home, "x", datetime, system);
        pictures = new Directory(home, "pictures", datetime);
        e = new File(pictures, "e", 5, datetime);
        f = new File(pictures, "f", 6, datetime);
        y = new Link(pictures, "y", datetime, e);
    }

    private String[] linkToStringArray(Link l){
        String[] linkInfo = {
            l.isFile().toString(), l.isDirectory().toString(),
            l.getName(), l.getParent().toString(),
            l.getTarget().toString(),
            l.getSize().toString(), 
            l.getCreationTime().toString(),
        };
        return linkInfo;
    }

    @Test
    public void verifyLinkEqualityX() {
        String[] expected = {"false", "false",
                             "x", "home",
                             "system",
                             "0",
                             datetime.toString(),
        };
        String[] actual = linkToStringArray(x);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileEqualityY() {
        String[] expected = {"false", "false",
                             "y", "pictures",
                             "e",
                             "0",
                             datetime.toString(),
        };
        String[] actual = linkToStringArray(y);
        assertArrayEquals(expected, actual);
    }

    @AfterAll
    public static void doSomething() {
        fs.erase();
    }

}

