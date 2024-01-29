package com.timprogrammiert.OperatingSystem;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Filesystem.Filesystem;
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
    User currentUser;

    public OperatingSystem(Filesystem filesystem){
        this.filesystem = filesystem;
        this.userList = new ArrayList<>();
    }
    public Filesystem getFilesystem(){
        return filesystem;
    }
}
