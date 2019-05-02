package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ApfsFileSearchVisitor implements ApfsFSVisitor  {

    private ArrayList<ApfsFile> foundFiles = new ArrayList<ApfsFile>();

    private Predicate soughtFeature;

    public void visit(ApfsLink link) { }

    public void visit(ApfsDirectory dir) { }

    public void visit(ApfsFile file) {
        if (soughtFeature.test(file)) {
            foundFiles.add(file);
        }
    }

    public ArrayList<ApfsFile> search(Predicate feature) {
        soughtFeature = feature;
        APFS.getFileSystem().getRootDir().accept(this);
        return foundFiles;
    }

    
}
