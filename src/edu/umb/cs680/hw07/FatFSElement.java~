package edu.umb.cs680.hw07;

import java.time.LocalDateTime;

public abstract class FatFSElement extends FSElement {

    protected FatDirectory parent;
    private static Integer maxNameLength = 11;

    protected FatFSElement(FatDirectory parent,
                           String name,
                           Integer size,
                           LocalDateTime creationTime) {
        super(name.toUpperCase(), size, creationTime);
        if (name.length() > maxNameLength) {
            throw new RuntimeException("that name is too long");
        }
        if (parent != null) {
            parent.appendChild(this);
            this.parent = parent;
        }
    }

    public FatDirectory getParent() {
        return this.parent;
    }

    public String toString() {
        return this.getName();
    }

    protected Integer getMaxNameLength() {
        return this.maxNameLength;
    }

    public static void main(String args[]) {
    }

}
