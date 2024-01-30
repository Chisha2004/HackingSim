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

    }

    public DirectoryObject getCurrentDirectory(){
        return currentDirectory;
    }

    public DirectoryObject getRootFolder(){
        return rootFolder;
    }

    public void setCurrentDirectory(DirectoryObject currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public void setRootFolder(DirectoryObject rootFolder) {
        this.rootFolder = rootFolder;
    }
}
