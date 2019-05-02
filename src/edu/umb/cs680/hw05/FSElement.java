package edu.umb.cs680.hw05;

import java.time.LocalDateTime;

public abstract class FSElement {

    // todo the setter for parent is defined only in subclasses, because it invokes the addChild method of parent which depends on the type of this
    // so it can't be private... how to fix?
    protected Directory parent;
    private String name;
    private Integer size;
    private LocalDateTime creationTime;

    protected FSElement(Directory parent,
                        String name,
                        Integer size,
                        LocalDateTime creationTime) {

        if (parent != null) {
            parent.appendChild(this);
            this.parent = parent;
        }
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public FSElement getParent() {
        return this.parent;
    }

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

    // public StringBuilder getPath() {
    //     StringBuilder leaf = new StringBuilder("/%s".format(this.getName()));
    //     if (this.getParent() == null) {
    //         return leaf;
    //     } else {
    //         return this.getParent().getPath().append(leaf);
    //     }
    // }

    public String toString() {
        return this.getName();
    }

    public static void main(String args[]) {
        // FileSystem fs = FileSystem.getFileSystem();
        // System.out.printf("FSElement: blah");
    }

}
