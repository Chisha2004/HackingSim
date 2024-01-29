package com.timprogrammiert.Filesystem.Path;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class Path {
    String pathString;
    public Path(String pathString){
        this.pathString = pathString;
    }
    public boolean isValidPath(){
        return true;
    }

    public String getPathString(){
        return pathString;
    }
}
