package com.timprogrammiert.Filesystem.Permissions;

import com.timprogrammiert.OperatingSystem.Users.User;

/**
 * Author : Tim
 * Date: 02.02.2024
 * Version: 1.0
 */
public class PermissionChecker {
    private final Permissions permissions;
    private User userToCheck;
    private String userPermissions;
    private String groupPermissions;
    private String otherPermissions;

    private boolean canRead;
    private boolean canWrite;
    private boolean canExecute;

    public PermissionChecker(Permissions permissions, User userToCheck) {
        this.permissions = permissions;
        this.userToCheck = userToCheck;
        this.getPermissions();
        this.evaluatePermissions();
    }

    private void getPermissions(){
        // If its a Directory it will be indicated with a d in the pemString
        // We dont want that d to evaluate the Permissions
        String permissionString = permissions.getPermissionString().replace("d", "");

        userPermissions = permissionString.substring(0,3);
        groupPermissions = permissionString.substring(3,6);
        otherPermissions = permissionString.substring(6,9);
    }

    private void evaluatePermissions(){
        String pemString = "";
        if(permissions.getUser().equals(userToCheck)){
            // Check User Permissions
            pemString = userPermissions;
        }else if(permissions.getGroup().hasMember(userToCheck)){
            // Check Group Permissions
            pemString = groupPermissions;
        }else {
            // Check other Permissions
            pemString = otherPermissions;
        }

        canRead = pemString.contains("r");
        canWrite = pemString.contains("w");
        canExecute = pemString.contains("x");
    }

    public boolean isCanRead() {
        return canRead;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    public boolean isCanExecute() {
        return canExecute;
    }
}
