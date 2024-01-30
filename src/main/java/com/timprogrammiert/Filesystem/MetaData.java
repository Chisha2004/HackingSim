package com.timprogrammiert.Filesystem;

import java.sql.Timestamp;

/**
 * Author : Tim
 * Date: 30.01.2024
 * Version: 1.0
 */
public class MetaData {
    Timestamp createdDate;
    Timestamp modifiedDate;
    int modifiedCounter;

    public MetaData() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.modifiedDate = this.createdDate;
        this.modifiedCounter = 0;
    }

    public void updateModified(){
        modifiedDate = new Timestamp(System.currentTimeMillis());
        modifiedCounter++;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public int getModifiedCounter(){
        return modifiedCounter;
    }
}
