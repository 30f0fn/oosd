package edu.umb.cs680.hw03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    public void verifyGetInstanceReturnsSame() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        assertSame(s1, s2);
    }
}

