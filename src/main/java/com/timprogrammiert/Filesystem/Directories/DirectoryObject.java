package com.timprogrammiert.Filesystem.Directories;

import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Files.FileObject;
import com.timprogrammiert.Filesystem.Permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class DirectoryObject extends BaseFile {
    List<BaseFile> childObjects;
    DirectoryObject parentFolder;
    public DirectoryObject(Permissions permissions, String name, EnumFileTypes fileType ){
        super(permissions, name, fileType);
        this.childObjects = new ArrayList<>();
        this.parentFolder = null;
    }
    public DirectoryObject(Permissions permissions, String name, EnumFileTypes fileType, DirectoryObject parentFolder){
        super(permissions, name, fileType);
        this.parentFolder = parentFolder;
        this.childObjects = new ArrayList<>();
    }

    public DirectoryObject getParentFolder(){
        return parentFolder;
    }
    public List<BaseFile> getChildObjects(){
        return childObjects;
    }
    public void addChild(BaseFile child){
        childObjects.add(child);
    }
}
