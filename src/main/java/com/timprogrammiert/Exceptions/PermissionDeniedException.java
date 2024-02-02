package com.timprogrammiert.Exceptions;

/**
 * Author : Tim
 * Date: 02.02.2024
 * Version: 1.0
 */
public class PermissionDeniedException extends Exception{
    public PermissionDeniedException() {
        super("Permission denied");
    }
}
