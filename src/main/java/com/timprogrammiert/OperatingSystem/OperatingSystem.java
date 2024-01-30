package com.timprogrammiert.OperatingSystem;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Files.FileObject;
import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.Filesystem.Permissions.Permissions;
import com.timprogrammiert.OperatingSystem.Groups.Group;
import com.timprogrammiert.OperatingSystem.Users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class OperatingSystem {
    Filesystem filesystem;
    List<User> userList;
    List<Group> groupList;
    User currentUser;

    public OperatingSystem(Filesystem filesystem){
        this.filesystem = filesystem;
        this.userList = new ArrayList<>();
        this.groupList = new ArrayList<>();
        this.currentUser = createUser("root", "root");
        createStartFileStructure();
    }
    public Filesystem getFilesystem(){
        return filesystem;
    }
    public User createUser(String userName, String password){
        return new User(userName, password);
    }
    public Group createGroup(String groupName){
        Group group = new Group(groupName);
        groupList.add(group);
        return group;
    }
    private void createStartFileStructure(){
        Permissions permissions = new Permissions(currentUser, createGroup("root"), "rwxrwxr--" );
        DirectoryObject rootFolder = new DirectoryObject(permissions, "/", EnumFileTypes.Directory);
        DirectoryObject bin = new DirectoryObject(permissions, "bin", EnumFileTypes.Directory, rootFolder);
        DirectoryObject etc = new DirectoryObject(permissions, "etc", EnumFileTypes.Directory, bin);
        FileObject fileObject1 = new FileObject(permissions, "TestFile", EnumFileTypes.File,etc);

        rootFolder.addChild(bin);
        bin.addChild(etc);
        etc.addChild(fileObject1);

        DirectoryObject log = new DirectoryObject(permissions, "log", EnumFileTypes.Directory, bin);
        bin.addChild(log);
        filesystem.setRootFolder(rootFolder);
        filesystem.setCurrentDirectory(rootFolder);
    }
}
