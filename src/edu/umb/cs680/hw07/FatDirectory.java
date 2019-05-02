package edu.umb.cs680.hw07;

import java.util.LinkedList;
import java.time.LocalDateTime;

public class FatDirectory extends FatFSElement {

    private LinkedList<FatFSElement> children;

    public FatDirectory(FatDirectory parent,
                        String name,
                        LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        children = new LinkedList<FatFSElement>();
    }

    public LinkedList<FatFSElement> getChildren() {
        return this.children;
    }


    public LinkedList<FatDirectory> getSubDirectories() {
        LinkedList<FatDirectory> subDirs = new LinkedList<FatDirectory>();
        for (FatFSElement elem : this.getChildren()) {
            if (elem.isDirectory()) {
                subDirs.add((FatDirectory) elem);
            }
        }
        return subDirs;
    }

    public LinkedList<FatFile> getFiles() {
        LinkedList<FatFile> files = new LinkedList<FatFile>();
        for (FatFSElement elem : this.getChildren()) {
            if (elem.isFile()) {
                files.add((FatFile) elem);
            }
        }
        return files;
    }


    public void appendChild(FatFSElement elem) {
        for (FatFSElement child : this.children) {
            if (elem.getName() == child.getName()) {
                if (elem.isDirectory() && child.isDirectory()) {
                    throw new RuntimeException("a sibling directory already exists with that name");
                }
                else if (elem.isDirectory() && child.isDirectory()) {
                    throw new RuntimeException("a sibling file already exists with that name");
                } 
            }
        }
        this.children.add(elem);
    }






    public Integer countChildren() {
        return this.children.size();
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


    // public FATDirectory FatDirectory(Directory dir, String str, LocalDateTime ldt) {
        // super(dir, str, ldt);
    // }

}
