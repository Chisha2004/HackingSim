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
    MetaData metaData;
    protected BaseFile(Permissions permissions, String name, EnumFileTypes fileTypes){
        this.permissions = permissions;
        this.name = name;
        this.fileType = fileTypes;
        this.metaData = new MetaData();
    }
    public String getName(){
        return name;
    }

    public EnumFileTypes getFileType() {
        return fileType;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public MetaData getMetaData() {
        return metaData;
    }
}
