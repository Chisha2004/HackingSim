package com.timprogrammiert.OperatingSystem;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Files.FileObject;
import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.Filesystem.Permissions.Permissions;
import com.timprogrammiert.OperatingSystem.Groups.Group;
import com.timprogrammiert.OperatingSystem.Users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class OperatingSystem {
    Filesystem filesystem;
    Map<String, Group> groupList;
    Map<String, List<User>> userList;
    User currentUser;

    public OperatingSystem(Filesystem filesystem){
        this.filesystem = filesystem;
        this.userList = new HashMap<>();
        this.groupList = new HashMap<>();
        this.currentUser = createUser("root", "root");
        createStartFileStructure();
    }
    public Filesystem getFilesystem(){
        return filesystem;
    }
    public User createUser(String userName, String password){
        if(userList.containsKey(userName)){
            // Return null if user already exists ?
            return null;
        }else {
            User newUser = new User(userName, password);
            Group userGroup = createGroup(userName);
            userGroup.addMemberToGroup(newUser);
            return newUser;
        }
    }
    public Group createGroup(String groupName){
        if(groupList.containsKey(groupName)){
            return groupList.get(groupName);
        }else {
            Group newGroup = new Group(groupName);
            groupList.put(groupName, newGroup);
            return newGroup;
        }
    }
    private void createStartFileStructure(){
        Permissions testPermissions = new Permissions(createUser("testUser", "testPass"), createGroup("root"), "drwxrwxr--" );

        Permissions dirPermissions = new Permissions(currentUser, createGroup("root"), "drwxrwxr--" );
        Permissions filePermissions = new Permissions(currentUser, createGroup("root"), "-rwxrwxr--" );
        DirectoryObject rootFolder = new DirectoryObject(dirPermissions, "/", EnumFileTypes.Directory);

        DirectoryObject bin = new DirectoryObject(testPermissions, "bin", EnumFileTypes.Directory, rootFolder);

        DirectoryObject etc = new DirectoryObject(dirPermissions, "etc", EnumFileTypes.Directory, bin);
        FileObject fileObject1 = new FileObject(filePermissions, "TestFile", EnumFileTypes.File,etc);

        rootFolder.addChild(bin);
        bin.addChild(etc);
        etc.addChild(fileObject1);

        DirectoryObject log = new DirectoryObject(dirPermissions, "log", EnumFileTypes.Directory, bin);
        bin.addChild(log);
        filesystem.setRootFolder(rootFolder);
        filesystem.setCurrentDirectory(rootFolder);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
