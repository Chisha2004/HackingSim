package com.timprogrammiert.Terminal;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.Exceptions.CommandNotFoundException;

import java.util.Scanner;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class Terminal {
    Computer computer;

    Filesystem filesystem;

    TerminalInputHandler inputHandler;

    // Flag indicating whether the terminal is running
    boolean isRunning = true;

    /**
     * Constructor for the Terminal class.
     *
     * @param computer The computer associated with the terminal.
     */
    public Terminal(Computer computer) {
        this.computer = computer;
        this.filesystem = this.computer.getOperatingSystem().getFilesystem();
        inputHandler = new TerminalInputHandler(computer);
        initTerminal();
    }

    /**
     * Initializes the terminal, prompting the user for input in a loop.
     */
    private void initTerminal() {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        String result = "";
        // Continuous loop for user interaction
        while (isRunning) {
            System.out.print(currentDirectoryToString() + "> ");
            input = scanner.nextLine();

            try {
                // Process user input using the TerminalInputHandler
                result = inputHandler.handleInput(input);
                if(result.isEmpty()){System.out.print("");}
                else {
                    System.out.println(result);
                }

            } catch (CommandNotFoundException e) {
                // Handle command not found exception and display an error message
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Converts the current directory structure to a string representation.
     *
     * @return A string representing the current directory.
     */
    private String currentDirectoryToString() {
        DirectoryObject currentDirectory = filesystem.getCurrentDirectory();
        StringBuilder folderString = new StringBuilder();

        // Build the directory string by traversing parent folders
        while (currentDirectory.getParentFolder() != null) {
            folderString.insert(0, currentDirectory.getName() + "/");
            currentDirectory = currentDirectory.getParentFolder();
        }

        // Insert the root directory separator
        folderString.insert(0, "/");
        return folderString.toString();
    }
}

