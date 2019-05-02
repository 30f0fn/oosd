package edu.umb.cs680.hw07;

import java.util.LinkedList;
import java.time.LocalDateTime;

public class FAT extends FileSystem {

    private static FAT theInstance;
    private static String defaultName = "the FAT filesystem";
    private static int defaultCapacity = 1000000000;

    private static String defaultRootName = "theFATSRoot";

    public static FAT getFileSystem() {
        if (theInstance == null) {
            theInstance = new FAT();
            theInstance.initFileSystem(defaultName, defaultCapacity);
        }
        return theInstance;
    }

    private LinkedList<FatDirectory> rootDirs;

    public FAT() {
        rootDirs = new LinkedList<FatDirectory>();
    }

    protected FSElement createDefaultRoot(){
        return new FatDirectory(null, "root", LocalDateTime.now());
    }

    protected void setDefaultRoot(FSElement dir) {
        if (rootDirs.contains(dir)) {
                rootDirs.remove(dir);
            }
        rootDirs.addFirst((FatDirectory) dir);
    }

    protected FatDirectory getDefaultRoot() {
        return rootDirs.getFirst();
    }

    public LinkedList<FatDirectory> getRootDirs() {
        return this.rootDirs;
    }

    public void setRootDir(FatDirectory dir) {
        dir.parent = null;
        if (! this.rootDirs.contains(dir)) {
            this.rootDirs.add(dir);
        }
    }

    public int getUsed() {
        return getRootDirs().stream().mapToInt(d->d.getTotalSize()).sum();
    }

    public void erase() {
        this.rootDirs.clear();
        // FSElement newRoot = this.createDefaultRoot();
        // setDefaultRoot(newRoot);
    }

    public String toString() {
        return "Filesystem: the hw7 FAT FileSystem instance";
    }



}
