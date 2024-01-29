package com.timprogrammiert.Computer;

import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.OperatingSystem.OperatingSystem;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class Computer {
    OperatingSystem operatingSystem;

    public Computer(OperatingSystem operatingSystem){
        this.operatingSystem = operatingSystem;
    }

    public OperatingSystem getOperatingSystem(){
        return operatingSystem;
    }
}
