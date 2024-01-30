package com.timprogrammiert.Filesystem.Permissions;

import com.timprogrammiert.OperatingSystem.Groups.Group;
import com.timprogrammiert.OperatingSystem.Users.User;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class Permissions {
    User user;
    Group group;
    String permissionString;
    public Permissions(User belongsToUser, Group belongsToGroup, String permissionString){
        this.user = belongsToUser;
        this.group = belongsToGroup;
        this.permissionString = permissionString;
    }
    public String getPermissionString(){
        return permissionString;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setPermissionString(String permissionString) {
        this.permissionString = permissionString;
    }
}
