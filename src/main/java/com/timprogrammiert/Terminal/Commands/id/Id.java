package com.timprogrammiert.Terminal.Commands.id;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Terminal.Commands.ICommand;

/**
 * Author : Tim
 * Date: 03.02.2024
 * Version: 1.0
 */
public class Id implements ICommand {

    @Override
    public String run(Computer computer, String[] args) {
        return handleArguments();
    }

    private String handleArguments(){
        return "";
    }
}
