package com.timprogrammiert;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.OperatingSystem.OperatingSystem;
import com.timprogrammiert.Terminal.Terminal;

public class Main {
    public static void main(String[] args) {
        DeviceManager deviceManager = new DeviceManager();

        Filesystem filesystem = new Filesystem();
        OperatingSystem os = new OperatingSystem(filesystem);
        Computer computer = new Computer(os);
        deviceManager.addComputer(computer);

        Terminal terminal = new Terminal(computer);
    }
}