package com.timprogrammiert.Terminal.Commands;

import com.timprogrammiert.Computer.Computer;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */

public interface ICommand {
    String run(Computer computer, String[] args);
}
