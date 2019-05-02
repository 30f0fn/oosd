package edu.umb.cs680.hw08;

import java.time.LocalDateTime;

public class ApfsLink extends ApfsFSElement {

    private ApfsFSElement target;

    public ApfsLink(ApfsDirectory parentDir,
                String name,
                LocalDateTime creationTime,
                ApfsFSElement target) {
        super(parentDir, name, 0, creationTime);
        this.target = target;
    }; 

    protected void accept(ApfsFSVisitor v) {
        v.visit(this);
    }

    public ApfsFSElement getTarget() {
        return this.target;
    }

    public Boolean isDirectory() {
        return false;
    }

    public Boolean isFile() {
        return false;
    }

    public Boolean isLink() {
        return true;
    }


}
