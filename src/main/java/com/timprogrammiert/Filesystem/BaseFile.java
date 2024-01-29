package com.timprogrammiert.Filesystem;

import com.timprogrammiert.Filesystem.Permissions.Permissions;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class BaseFile {
    Permissions permissions;
    String name;
    EnumFileTypes fileType;
    protected BaseFile(Permissions permissions, String name, EnumFileTypes fileTypes){
        this.permissions = permissions;
        this.name = name;
        this.fileType = fileTypes;
    }
    public String getName(){
        return name;
    }

    public EnumFileTypes getFileType() {
        return fileType;
    }
}
