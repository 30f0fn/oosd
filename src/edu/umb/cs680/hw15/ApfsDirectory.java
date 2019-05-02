package edu.umb.cs680.hw15;

import java.util.Comparator;

// modify ordinary ApfsDirectory by sorting children
// apply sort with default comparator to children each time child is added
// if getX is called with custom comparator, build ArrayList return value, sort it with custom comparator, and return it


import java.util.ArrayList;
import java.time.LocalDateTime;

public class ApfsDirectory extends ApfsFSElement {

    private ArrayList<ApfsFSElement> children;

    private Comparator<ApfsFSElement> defaultComparator = 
        (ApfsFSElement e1, ApfsFSElement e2) ->
        Comparator.<String>naturalOrder().compare(e1.getName(), e2.getName());

    public ApfsDirectory(ApfsDirectory parent,
                        String name,
                        LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        children = new ArrayList<ApfsFSElement>();
    }

    public ArrayList<ApfsFSElement> getChildren() {
        return this.children;
    }

    public ArrayList<ApfsFSElement> getChildren(Comparator<ApfsFSElement> comp) {
        ArrayList<ApfsFSElement> children = (ArrayList<ApfsFSElement>) this.getChildren().clone();
        children.sort(comp);
        return children;
    }


    public void appendChild(ApfsFSElement elem) {
        for (ApfsFSElement child : this.children) {
            if (elem.getName() == child.getName()) {
                if (elem.isDirectory() && child.isDirectory()) {
                    throw new RuntimeException("a sibling directory already exists with that name");
                }
                else if (elem.isDirectory() && child.isDirectory()) {
                    throw new RuntimeException("a sibling file already exists with that name");
                } else if (elem.isLink() && child.isLink()) {
                    throw new RuntimeException("a sibling link already exists with that name");
                }
            }
        }
        this.children.add(elem);
        this.children.sort(defaultComparator);
    }

    protected void accept(ApfsFSVisitor v) {
        v.visit(this);
        for(ApfsFSElement e: children) {
            e.accept(v);
        }        
    }

    protected void deleteChild(FSElement child) {
        this.children.remove(child);
    }

    public Integer countChildren() {
        return this.children.size();
    }

    public ArrayList<ApfsDirectory> getSubDirectories() {
        ArrayList<ApfsDirectory> subDirs = new ArrayList<ApfsDirectory>();
        for (ApfsFSElement elem : this.getChildren()) {
            if (elem.isDirectory()) {
                subDirs.add((ApfsDirectory) elem);
            }
        }
        return subDirs;
    }

    public ArrayList<ApfsDirectory> getSubDirectories(Comparator<ApfsFSElement> comp) {
        ArrayList<ApfsDirectory> subDirs = this.getSubDirectories();
        subDirs.sort(comp);
        return subDirs;
    }

    public ArrayList<ApfsFile> getFiles() {
        ArrayList<ApfsFile> files = new ArrayList<ApfsFile>();
        for (ApfsFSElement elem : this.getChildren()) {
            if (elem.isFile()) {
                files.add((ApfsFile) elem);
            }
        }
        return files;
    }

    public ArrayList<ApfsFile> getFiles(Comparator<ApfsFSElement> comp) {
        ArrayList<ApfsFile> files = this.getFiles();
        files.sort(comp);
        return files;
    }

    protected boolean isEmpty() {
        return this.children.isEmpty();
    }

    public Integer getTotalSize() {
        int fileSum = getFiles().stream().mapToInt(f->f.getSize()).sum();
        int dirSum = getSubDirectories().stream().mapToInt(d->d.getTotalSize()).sum();
        return fileSum + dirSum + this.getSize();
    }

    public Boolean isDirectory() {
        return true;        
    }

    public Boolean isFile() {
        return false;
    }

    public Boolean isLink() {
        return false;
    }

}
