package com.timprogrammiert.Filesystem.Files;

import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Permissions.Permissions;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class FileObject extends BaseFile {
    DirectoryObject parentObject;
    public FileObject(Permissions permissions, String name, EnumFileTypes fileType, DirectoryObject parentObject){
        super(permissions, name, fileType);
        this.parentObject = parentObject;
    }
}
