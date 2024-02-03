package com.timprogrammiert.OperatingSystem.Groups;

import com.timprogrammiert.OperatingSystem.Users.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Tim
 * Date: 30.01.2024
 * Version: 1.0
 */
public class Group {
    String groupName;
    Map<String, User> groupMembers;
    GroupInfo groupInfo;

    /**
     * @deprecated
     */
    public Group(String groupName) {
        this.groupMembers = new HashMap<>();
        this.groupName = groupName;
    }
    public Group(String groupName, GroupInfo groupInfo) {
        this.groupMembers = new HashMap<>();
        this.groupName = groupName;
        this.groupInfo = groupInfo;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean hasMember(User userToSearch){
        return groupMembers.containsKey(userToSearch.getUserName());
    }
    public void addMemberToGroup(User user){
        groupMembers.put(user.getUserName(), user);
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }
}
