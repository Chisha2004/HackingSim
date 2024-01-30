package com.timprogrammiert.Exceptions;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class FileNotFoundException extends Exception{
    public FileNotFoundException(String fileName){
        super("Invalid path: " + fileName + " not found!");
    }
}
