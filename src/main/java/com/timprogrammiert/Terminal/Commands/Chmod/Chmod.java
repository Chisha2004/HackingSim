package com.timprogrammiert.Terminal.Commands.Chmod;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Exceptions.FileNotFoundException;
import com.timprogrammiert.Exceptions.InvalidArgumentsException;
import com.timprogrammiert.Exceptions.NotADirectoryException;
import com.timprogrammiert.Exceptions.PermissionDeniedException;
import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Path.Path;
import com.timprogrammiert.Filesystem.Path.PathResolver;
import com.timprogrammiert.Filesystem.Permissions.PermissionChecker;
import com.timprogrammiert.Filesystem.Permissions.PermissionCreater;
import com.timprogrammiert.Terminal.Commands.ICommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Chmod class implements the ICommand interface, providing functionality
 * for changing permissions of files and directories in the terminal.
 * Author : Tim
 * Date: 01.02.2024
 * Version: 1.0
 */
public class Chmod implements ICommand {
    Computer computer;
    private final static int FILE_PATH_INDEX = 1;

    /**
     * Executes the Chmod command with the provided arguments.
     *
     * @param computer The Computer instance representing the current system.
     * @param args     The array of command-line arguments.
     * @return A String representing the result or an empty String if no action is taken.
     */
    @Override
    public String run(Computer computer, String[] args) {
        if(args.length == 0 || args.length == 1){
            return "";
        }
        this.computer = computer;
        List<String> argList = new ArrayList<>(Arrays.asList(args));
        argList.removeIf(String::isBlank);
        try {
            return handleArguments(argList);
        } catch (NotADirectoryException | FileNotFoundException | InvalidArgumentsException | PermissionDeniedException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the provided arguments for the Chmod command.
     *
     * @param argList The list of command-line arguments.
     * @return A String representing the result of the Chmod operation.
     * @throws NotADirectoryException      If the resolved path does not represent a directory.
     * @throws FileNotFoundException      If the specified path is not found.
     * @throws InvalidArgumentsException   If the provided arguments are invalid.
     * @throws PermissionDeniedException   If the user lacks the necessary permissions.
     */
    private String handleArguments(List<String> argList) throws NotADirectoryException, FileNotFoundException,
            InvalidArgumentsException, PermissionDeniedException {
        String permissionCode = argList.getFirst();
        if(!(isValid(permissionCode))){throw new InvalidArgumentsException();}
        String filePath = argList.get(FILE_PATH_INDEX);

        Path path = new Path(filePath);
        PathResolver pathResolver = new PathResolver(computer.getOperatingSystem().getFilesystem());
        BaseFile file = pathResolver.resolvePath(path);

        PermissionChecker pemChecker = new PermissionChecker(file.getPermissions(), computer.getOperatingSystem().getCurrentUser());
        PermissionCreater pemCreater = new PermissionCreater(file, permissionCode);
        if ((file.getFileType().equals(EnumFileTypes.File) && pemChecker.isCanWrite()) || file.getFileType().equals(EnumFileTypes.Directory) &&
                pemChecker.isCanWrite() && pemChecker.isCanExecute()) {
                pemCreater.updatePermission();
        } else {
            throw new PermissionDeniedException();
        }
        return "";
    }

    /**
     * Checks if the given permission arguments are valid.
     *
     * @param permissionArguments The permission arguments to validate.
     * @return True if the input type is correct; otherwise, false.
     */
    private boolean isValid(String permissionArguments) {
        // Checks only if the Input type is Correct, not the actual numeric values
        if(permissionArguments.equals("+x")){
            return true;
        } else return (permissionArguments.length() == 3 && isNumeric(permissionArguments));
    }

    /**
     * Checks if a given string represents a numeric value.
     *
     * @param str The string to check.
     * @return True if the string is numeric; otherwise, false.
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

