package edu.umb.cs680.hw08;

import java.time.LocalDateTime;

public class ApfsFile extends ApfsFSElement {

    public ApfsFile(ApfsDirectory parentDir,
                   String name,
                   Integer size,
                   LocalDateTime creationTime) {
        super(parentDir, name, size, creationTime);
            
    }; 

    public Boolean isDirectory() {
        return false;
    }

    public Boolean isFile() {
        return true;
    }

    public Boolean isLink() {
        return false;
    }

    protected void accept(ApfsFSVisitor v) {
        v.visit(this);
    }


    public static void main(String args[]) {
        // File file = new File(...);
        // System.out.printf("%s",
                          // file.getName());
    }
}

