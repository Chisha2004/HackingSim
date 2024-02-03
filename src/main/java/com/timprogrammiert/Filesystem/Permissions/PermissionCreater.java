package com.timprogrammiert.Filesystem.Permissions;

import com.timprogrammiert.Exceptions.InvalidArgumentsException;
import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.EnumFileTypes;

/**
 * Author : Tim
 * Date: 01.02.2024
 * Version: 1.0
 */
public class PermissionCreater {
    String permissionCode;
    Permissions oldPermission;
    boolean isDirectory;
    public PermissionCreater(BaseFile fileBelongsPermission, String pemCode) throws InvalidArgumentsException {
        this.oldPermission = fileBelongsPermission.getPermissions();
        this.isDirectory = (fileBelongsPermission.getFileType() == EnumFileTypes.Directory);
        this.permissionCode = pemCode;
    }

    public void updatePermission() throws InvalidArgumentsException {
        StringBuilder pemStringBuilder = new StringBuilder();
        if(isDirectory){pemStringBuilder.append("d");}
        for (int i = 0; i < 3; i++) {
            switch (permissionCode.charAt(i)){
                case '0' -> pemStringBuilder.append("---");
                case '1' -> pemStringBuilder.append("--x");
                case '2' -> pemStringBuilder.append("-w-");
                case '3' -> pemStringBuilder.append("-wx");
                case '4' -> pemStringBuilder.append("r--");
                case '5' -> pemStringBuilder.append("r-x");
                case '6' -> pemStringBuilder.append("rw-");
                case '7' -> pemStringBuilder.append("rwx");
                default -> throw new InvalidArgumentsException();
            }
        }
        String pemString = pemStringBuilder.toString();
        oldPermission.setPermissionString(pemString);
    }


}
