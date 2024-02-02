package com.timprogrammiert.OperatingSystem.Groups;

import com.timprogrammiert.OperatingSystem.Users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Tim
 * Date: 30.01.2024
 * Version: 1.0
 */
public class Group {
    String groupName;
    List<User> groupMembers;

    public Group(String groupName) {
        this.groupMembers = new ArrayList<>();
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean hasMember(User userToSearch){
        return groupMembers.contains(userToSearch);
    }
    public void addMemberToGroup(User userName){
        groupMembers.add(userName);
    }
}
