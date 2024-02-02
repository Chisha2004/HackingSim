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
import com.timprogrammiert.Terminal.Commands.ICommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : Tim
 * Date: 01.02.2024
 * Version: 1.0
 */
public class Chmod implements ICommand {
    Computer computer;
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
    private String handleArguments(List<String> argList) throws NotADirectoryException, FileNotFoundException, InvalidArgumentsException, PermissionDeniedException {
        String permissionCode = argList.getFirst();
        if(!(isValid(permissionCode))){throw new InvalidArgumentsException();}
        String filePath = argList.get(1);

        Path path = new Path(filePath);
        PathResolver pathResolver = new PathResolver(computer.getOperatingSystem().getFilesystem());
        BaseFile file = pathResolver.resolvePath(path);

        PermissionChecker pemChecker = new PermissionChecker(file.getPermissions(), computer.getOperatingSystem().getCurrentUser());
        if(file.getFileType().equals(EnumFileTypes.File) && pemChecker.isCanWrite()){
            // Change FILE PERMISSIONS
        } else if (file.getFileType().equals(EnumFileTypes.Directory) && pemChecker.isCanWrite() && pemChecker.isCanExecute()) {
            // CHANGE DIRECTORY PERMISSIONS
        }else {
            throw new PermissionDeniedException();
        }
        return "";
    }

    private boolean isValid(String permissionArguments) {
        // Checks only if the Input type is Correct, not the actual numeric values
        if(permissionArguments.equals("+x")){
            return true;
        } else if ((permissionArguments.length() == 3 && isNumeric(permissionArguments))) {
            return true;
        }else {
            return false;
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
