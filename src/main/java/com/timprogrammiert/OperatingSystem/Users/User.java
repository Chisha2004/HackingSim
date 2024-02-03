package com.timprogrammiert.OperatingSystem.Users;

import com.timprogrammiert.OperatingSystem.Groups.Group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class User {
    String password;
    String userName;
    Map<String, Group> groupList;
    public User(String userName, String password){
        this.password = password;
        this.userName = userName;
        this.groupList = new HashMap<>();
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
    public Group getSpecificGroup(String groupName){
        return groupList.get(groupName);
    }

    public List<Group> getAllGroups(){
        return (List<Group>) groupList.values();
    }
}
