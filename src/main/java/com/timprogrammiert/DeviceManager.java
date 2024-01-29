package com.timprogrammiert;

import com.timprogrammiert.Computer.Computer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class DeviceManager {
    List<Computer> computerList = new ArrayList<>();

    public void addComputer(Computer computerToAdd){
        computerList.add(computerToAdd);
    }
}
