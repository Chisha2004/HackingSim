package com.timprogrammiert.Exceptions;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String commandName){
        super(commandName + " not found!");
    }
}
