package edu.umb.cs680.hw05;

import java.time.LocalDateTime;

public class File extends FSElement {

    public File(Directory parentDir,
                 String name,
                 Integer size,
                 LocalDateTime creationTime) {
        // why not get creationTime as time of constructor call?
        // creationTime = LocalDateTime.now();
        super(parentDir, name, size, creationTime);

    }; 

    public Boolean isDirectory() {
        return false;
    }

    public Boolean isFile() {
        return true;
    }

    public static void main(String args[]) {
        // File file = new File(...);
        // System.out.printf("%s",
                          // file.getName());
    }
}

