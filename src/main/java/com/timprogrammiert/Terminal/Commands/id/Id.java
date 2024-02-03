package com.timprogrammiert.Terminal.Commands.id;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.OperatingSystem.Groups.Group;
import com.timprogrammiert.OperatingSystem.Users.User;
import com.timprogrammiert.Terminal.Commands.ICommand;

import java.util.Collection;
import java.util.List;

/**
 * Author : Tim
 * Date: 03.02.2024
 * Version: 1.0
 */
public class Id implements ICommand {
    Computer computer;
    @Override
    public String run(Computer computer, String[] args) {
        this.computer = computer;
        return handleArguments();
    }

    private String handleArguments(){
        User currentUser = computer.getOperatingSystem().getCurrentUser();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentUser.getUserName()).append(" ").append(currentUser.getAccountInfo().getUid()).append(" ");

        Collection<Group> userGroups = currentUser.getAllGroups();
        for (Group group : userGroups){
            stringBuilder.append(group.getGroupName()).append(" ").append(group.getGroupInfo());
        }
        return stringBuilder.toString();
    }
}
