FSElement
- datafields: name:String, size:Int, creation:TimeLocalDateTime 
- constructor takes (parent, name, size, creationTime)
- getParent()
- getSize() returns this.size
- other getters and setters
- isDirectory() boolean

FileSystem
- singleton pattern
- hidden constructor
- getFileSystem()
- getRootDirs()
    - return type LinkedList<Directory>

Directory
- subclasses FSElement
- constructor takes (parent: Directory, name: String)
- getChildren() : LinkedList<FSElement>
- appendChild() : void
- countChildren() : int
- getSubDirectories(): LinkedList<Directory>
- getFiles() : LinkedList<File>
- getTotalSize() : int recursive-sum

File
- isFile()
- isDirectory()

