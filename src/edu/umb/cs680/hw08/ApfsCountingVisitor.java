package edu.umb.cs680.hw08;

import java.util.ArrayList;

public class ApfsCountingVisitor implements ApfsFSVisitor  {

    private int dirNum = 0;
    private int fileNum = 0;
    private int linkNum = 0;

    private ArrayList<ApfsFile> files = new ArrayList<ApfsFile>();

    public void visit(ApfsLink link) {
        linkNum++;
    }

    public void visit(ApfsDirectory dir) {
        dirNum++;
    }

    public void visit(ApfsFile file) {
        fileNum++;
    }

    public int getDirNum() {
        return dirNum;
    }

    public int getFileNum() {
        return fileNum;
    }

    public int getLinkNum() {
        return linkNum;
    }

}
