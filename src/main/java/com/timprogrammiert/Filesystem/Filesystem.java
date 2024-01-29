package com.timprogrammiert.Filesystem;

import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.Files.FileObject;
import com.timprogrammiert.Filesystem.Permissions.Permissions;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class Filesystem {
    DirectoryObject currentDirectory;
    DirectoryObject rootFolder;
    public Filesystem(){
        testFilesystem();
    }

    private void testFilesystem(){
        rootFolder = new DirectoryObject(new Permissions(), "/", EnumFileTypes.Directory);
        DirectoryObject bin = new DirectoryObject(new Permissions(), "bin", EnumFileTypes.Directory, rootFolder);
        DirectoryObject etc = new DirectoryObject(new Permissions(), "etc", EnumFileTypes.Directory, bin);
        FileObject fileObject1 = new FileObject(new Permissions(), "TestFile", EnumFileTypes.File,etc);

        rootFolder.addChild(bin);
        bin.addChild(etc);
        etc.addChild(fileObject1);

        DirectoryObject log = new DirectoryObject(new Permissions(), "log", EnumFileTypes.Directory, bin);

        currentDirectory = rootFolder;
    }

    public DirectoryObject getCurrentDirectory(){
        return currentDirectory;
    }

    public DirectoryObject getRootFolder(){
        return rootFolder;
    }

}
