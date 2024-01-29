package com.timprogrammiert.Terminal.Commands.ChangeDirectory;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Terminal.Commands.ICommand;

import java.util.Arrays;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class ChangeDirectory implements ICommand {
    @Override
    public String run(Computer computer, String[] args) {
        return Arrays.toString(args);
    }
}
