package com.timprogrammiert.OperatingSystem;

import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Files.FileObject;
import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.Filesystem.Permissions.Permissions;
import com.timprogrammiert.OperatingSystem.Groups.Group;
import com.timprogrammiert.OperatingSystem.Groups.GroupInfo;
import com.timprogrammiert.OperatingSystem.Users.AccountInfo;
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
    UidManager uidManager;
    GidManager gidManager;
    User currentUser;

    public OperatingSystem(Filesystem filesystem){
        this.filesystem = filesystem;
        this.userList = new HashMap<>();
        this.groupList = new HashMap<>();
        this.uidManager = new UidManager();
        this.gidManager = new GidManager();
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
            AccountInfo accountInfo = new AccountInfo(uidManager.generateUid());
            User newUser = new User(userName, password, accountInfo);
            uidManager.addUserToList(newUser);

            Group userGroup = getOrCreateGroup(userName);
            newUser.addToGroup(userGroup);
            userGroup.addMemberToGroup(newUser);
            return newUser;
        }
    }
    public Group getOrCreateGroup(String groupName){
        if(groupList.containsKey(groupName)){
            return groupList.get(groupName);
        }else {
            GroupInfo groupInfo = new GroupInfo();
            Group newGroup = new Group(groupName, groupInfo);
            groupList.put(groupName, newGroup);
            return newGroup;
        }
    }
    private void createStartFileStructure(){
        Permissions testPermissions = new Permissions(createUser("testUser", "testPass"), getOrCreateGroup("root"), "drwxrwxr--" );

        Permissions dirPermissions = new Permissions(currentUser, getOrCreateGroup("root"), "drwxrwxr--" );
        Permissions filePermissions = new Permissions(currentUser, getOrCreateGroup("root"), "-rwxrwxr--" );
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

    public UidManager getUidManager() {
        return uidManager;
    }

    public GidManager getGidManager() {
        return gidManager;
    }
}
