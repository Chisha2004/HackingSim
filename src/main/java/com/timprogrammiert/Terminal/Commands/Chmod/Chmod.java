package com.timprogrammiert.Terminal.Commands.Chmod;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Exceptions.FileNotFoundException;
import com.timprogrammiert.Exceptions.NotADirectoryException;
import com.timprogrammiert.Filesystem.BaseFile;
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
        } catch (NotADirectoryException | FileNotFoundException e) {
            return e.getMessage();
        }
    }
    private String handleArguments(List<String> argList) throws NotADirectoryException, FileNotFoundException {
        String permissionCode = argList.getFirst();
        String filePath = argList.get(1);

        Path path = new Path(filePath);
        PathResolver pathResolver = new PathResolver(computer.getOperatingSystem().getFilesystem());
        BaseFile file = pathResolver.resolvePath(path);

        PermissionChecker pemChecker = new PermissionChecker(file.getPermissions(), computer.getOperatingSystem().getCurrentUser());


        return "";
    }
}
