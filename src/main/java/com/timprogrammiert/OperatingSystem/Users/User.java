package com.timprogrammiert.OperatingSystem.Users;

import com.timprogrammiert.OperatingSystem.Groups.Group;

import java.util.*;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class User {
    String password;
    String userName;
    AccountInfo accountInfo;
    Map<String, Group> groupList;
  
    public User(String userName, String password, AccountInfo accountInfo){
        this.password = password;
        this.userName = userName;
        this.accountInfo = accountInfo;
        this.groupList = new HashMap<>();
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void addToGroup(Group group){
        groupList.put(group.getGroupName(), group);
    }

    public Group getSpecificGroup(String groupName){
        return groupList.get(groupName);
    }

    public Collection<Group> getAllGroups(){
        return  groupList.values();

    }
}
