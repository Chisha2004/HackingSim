package com.timprogrammiert.Terminal.Commands.List;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Exceptions.FileNotFoundException;
import com.timprogrammiert.Exceptions.NotADirectoryException;
import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.EnumFileTypes;
import com.timprogrammiert.Filesystem.Path.Path;
import com.timprogrammiert.Filesystem.Path.PathResolver;
import com.timprogrammiert.Filesystem.Permissions.Permissions;
import com.timprogrammiert.Terminal.Commands.ICommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class Ls implements ICommand {
    Computer computer;
    @Override
    public String run(Computer computer, String[] args) {
        this.computer = computer;
        try {
           return handleArguments(args);
        } catch (FileNotFoundException | NotADirectoryException e){
            return e.getMessage();
        }
    }
    private String handleArguments(String[] args) throws FileNotFoundException, NotADirectoryException {
        boolean detailed = false;
        List<String> arguments = new ArrayList<>(Arrays.asList(args));

        if (arguments.remove("-al")) {
            detailed = true;
        }

        // If empty -> getCurrent; else resolve Path
        DirectoryObject targetObject = arguments.isEmpty()
                ? computer.getOperatingSystem().getFilesystem().getCurrentDirectory()
                : pathToDirectory(arguments);

        return detailed ? listAll(targetObject) : listSimple(targetObject);
    }

    /**
     * Converts a path provided in the form of command-line arguments to a DirectoryObject.
     *
     * @param arguments A list of command-line arguments, where the first argument is the path.
     * @return The DirectoryObject corresponding to the resolved path.
     * @throws FileNotFoundException    If the specified path is not found.
     * @throws NotADirectoryException    If the resolved path does not represent a directory.
     */
    private DirectoryObject pathToDirectory(List<String> arguments) throws FileNotFoundException, NotADirectoryException {
        String pathString = arguments.getFirst();
        Path path = new Path(pathString);

        if (path.isValidPath()) {
            PathResolver pathResolver = new PathResolver(computer.getOperatingSystem().getFilesystem());
            BaseFile targetObject = pathResolver.resolvePath(path);

            if (targetObject.getFileType().equals(EnumFileTypes.Directory)) {
                return (DirectoryObject) targetObject;
            } else {
                // If the resolved object is not a directory, throw an exception
                throw new NotADirectoryException(pathString + " is not a directory.");
            }
        } else {
            // If the path is not valid, throw an exception
            throw new FileNotFoundException("Invalid path: " + pathString);
        }
    }

    private String listAll(DirectoryObject directoryToList) {
        StringBuilder output = new StringBuilder();

        // Append detailed info for the current directory
        output.append(getDetailedInfoOfObject(directoryToList, "."));

        // Append detailed info for the parent directory
        output.append(getDetailedInfoOfObject(directoryToList.getParentFolder(), ".."));

        // Append detailed info for each child object
        for (BaseFile baseFile : directoryToList.getChildObjects()) {
            output.append(getDetailedInfoOfObject(baseFile, ""));
        }

        return output.toString().strip();
    }

    private String getDetailedInfoOfObject(BaseFile baseFile, String aliasName) {
        if(baseFile == null) return "";
        String fileName = aliasName.isEmpty() ? baseFile.getName() : aliasName;
        Permissions permissions = baseFile.getPermissions();

        // 4095 Jan 10 10:10 -> atm placeholder
        return String.format("%s %s %s 4095 Jan 10 10:10 %s\n",
                permissions.getPermissionString(),
                permissions.getUser().getUserName(),
                permissions.getGroup().getGroupName(),
                fileName
        );
    }

    private String listSimple(DirectoryObject directoryToList){
        StringBuilder output = new StringBuilder();
        for (BaseFile baseFile : directoryToList.getChildObjects()){
            output.append(baseFile.getName()).append("\n");
        }
        return output.toString().strip();
    }

}
