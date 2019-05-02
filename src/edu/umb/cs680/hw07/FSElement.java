package edu.umb.cs680.hw07;

import java.util.LinkedList;
import java.time.LocalDateTime;

public abstract class FSElement {

    // protected Directory parent;
    private String name;
    private Integer size;
    private LocalDateTime creationTime;

    protected FSElement(String name,
                        Integer size,
                        LocalDateTime creationTime) {
        if (name.length() > getMaxNameLength()) {
            throw new RuntimeException("that name is too long"); 
        }
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public abstract FSElement getParent() ;

    // public abstract FSElement setParent() ;

     public String getName() {
        return this.name;
    }

    public Integer getSize() {
        return this.size;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public abstract Boolean isDirectory() ;

    public abstract Boolean isFile() ;

    public String toString() {
        return this.getName();
    }

    protected abstract Integer getMaxNameLength();

    public static void main(String args[]) {
    }

}
