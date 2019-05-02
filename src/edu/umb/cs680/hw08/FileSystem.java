package edu.umb.cs680.hw08;

import java.util.LinkedList;
import java.time.LocalDateTime;

public abstract class FileSystem {

    private String name;
    private Integer capacity;
    private Integer id;

    // want subclasses to be singletons; a singleton constructor should be private
    // but can't inherit from a class with private constructor, so this is only protected
    protected FileSystem() {} 

    public FSElement initFileSystem(String name,
                                    Integer capacity){
        this.name = name;
        this.capacity = capacity;
        FSElement root = createDefaultRoot();
        // no capacity check here b/c root size is automatically 0
        if (root.getSize() > capacity) {
            throw new RuntimeException("insufficient capacity to initialize filesystem, uhoh");
        }
        setDefaultRoot(root);
        return root;
    }

    protected abstract FSElement createDefaultRoot() ;
    // these are differently implemented per subclass so leave abstract

    protected abstract void setDefaultRoot(FSElement dir) ;

    protected abstract FSElement getDefaultRoot() ;

    public int getCapacity() {
        return this.capacity;
    }

    public String getName() {
        return this.name;
    }

    public abstract int getUsed() ;
    // js sugests returning getDefaultRoot().getSize()
    // but that is 0
    // need instead getTotalSize of *all* root directories (I guess)
    // so it should only be defined for subclasses, which can access their related directory classes

    public int getRemaining() {
        return getCapacity() - getUsed();
    }

    // public abstract void delete(FSElement elem) ;

    public abstract void erase() ;


    public String toString() {
        return "FileSystem: The hw8 FileSystem instance.\n";
    }

    public static void main(String args[]) {
        FileSystem apfs = APFS.getFileSystem();
        System.out.println(apfs.toString());
    }
}
