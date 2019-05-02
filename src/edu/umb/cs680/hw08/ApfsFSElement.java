package edu.umb.cs680.hw08;

import java.time.LocalDateTime;

public abstract class ApfsFSElement extends FSElement {

    protected ApfsDirectory parent;
    private String owner;
    private LocalDateTime lastModified;
    private static Integer maxNameLength = 11;

    protected ApfsFSElement(ApfsDirectory parent,
                            String name,
                            Integer size,
                            LocalDateTime creationTime) {
        super(name, size, creationTime);
        if (size > APFS.getFileSystem().getRemaining()) {
            throw new RuntimeException("insufficient remaining space");
        }
        if (parent != null) {
            parent.appendChild(this);
            this.parent = parent;
        } 
        this.lastModified = creationTime;
    }

    public ApfsDirectory getParent() {
        return this.parent;
    }

    protected abstract void accept(ApfsFSVisitor v);

    public LocalDateTime getLastModified() {
        return this.lastModified;
    }

    public String toString() {
        return this.getName();
    }

    protected Integer getMaxNameLength() {
        return this.maxNameLength;
    }

    public abstract Boolean isDirectory() ;

    public abstract Boolean isFile() ;

    public abstract Boolean isLink() ;

    public static void main(String args[]) {
        // FileSystem fs = FileSystem.getFileSystem();
        // System.out.printf("FSElement: blah");
    }

}
