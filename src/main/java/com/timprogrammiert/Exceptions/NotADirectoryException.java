package com.timprogrammiert.Exceptions;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class NotADirectoryException extends Exception{
    public NotADirectoryException(String fileName){
        super(fileName + " is not a Directory!");
    }
}
