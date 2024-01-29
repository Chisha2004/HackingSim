package com.timprogrammiert.Terminal;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Terminal.Commands.ChangeDirectory.ChangeDirectory;
import com.timprogrammiert.Exceptions.CommandNotFoundException;
import com.timprogrammiert.Terminal.Commands.ICommand;
import com.timprogrammiert.Terminal.Commands.List.Ls;

import java.util.Arrays;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class TerminalInputHandler {
    Computer computer;

    /**
     * Constructor for the TerminalInputHandler class.
     *
     * @param computer The computer associated with the terminal.
     */
    public TerminalInputHandler(Computer computer) {
        this.computer = computer;
    }

    /**
     * Handles the user input by extracting and executing the corresponding command.
     *
     * @param inputString The user input string.
     * @return The result of executing the command.
     * @throws CommandNotFoundException If the entered command is not recognized.
     */
    public String handleInput(String inputString) throws CommandNotFoundException {
        // Extract the command and arguments from the input string
        ICommand command = substractCommand(inputString);
        String[] arguments = substractArguments(inputString);

        // Check if a valid command was extracted
        if (command == null) {
            throw new CommandNotFoundException(inputString);
        } else {
            // Execute the command and return the result
            return command.run(computer, arguments);
        }
    }

    /**
     * Extracts the command from the input string and returns the corresponding ICommand instance.
     *
     * @param input The user input string.
     * @return The ICommand instance representing the extracted command.
     */
    private ICommand substractCommand(String input) {
        String[] splittedInput = input.split(" ");
        String commandName = splittedInput[0];

        // Match the command name to the corresponding ICommand implementation
        switch (commandName) {
            case "cd":
                return new ChangeDirectory();
            case "ls":
                return new Ls();
            default:
                return null;
        }
    }

    /**
     * Extracts and returns the arguments from the input string.
     *
     * @param input The user input string.
     * @return An array of arguments extracted from the input string.
     */
    private String[] substractArguments(String input) {
        String[] splittedInput = input.split(" ");

        // Copy the arguments from the input string (excluding the command)
        return Arrays.copyOfRange(splittedInput, 1, splittedInput.length);
    }
}

