package com.timprogrammiert.Terminal.Commands.ChangeDirectory;

import com.timprogrammiert.Computer.Computer;
import com.timprogrammiert.Exceptions.FileNotFoundException;
import com.timprogrammiert.Exceptions.NotADirectoryException;
import com.timprogrammiert.Exceptions.PermissionDeniedException;
import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.Filesystem;
import com.timprogrammiert.Filesystem.Path.Path;
import com.timprogrammiert.Filesystem.Path.PathResolver;
import com.timprogrammiert.Filesystem.Permissions.PermissionChecker;
import com.timprogrammiert.OperatingSystem.OperatingSystem;
import com.timprogrammiert.Terminal.Commands.ICommand;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */
public class ChangeDirectory implements ICommand {
    Filesystem filesystem;
    OperatingSystem operatingSystem;
    @Override
    public String run(Computer computer, String[] args) {
        this.operatingSystem = computer.getOperatingSystem();
        this.filesystem = computer.getOperatingSystem().getFilesystem();
        return changeDirectory(args[0]);
    }

    /**
     * Changes the current directory in the filesystem to the specified path.
     *
     * @param pathString The string representation of the desired path.
     * @return An empty string if the directory change is successful, or an error message if an exception occurs.
     */
    private String changeDirectory(String pathString) {
        Path path = new Path(pathString);
        PathResolver pathResolver = new PathResolver(filesystem);
        DirectoryObject dirToCd;

        try {
            // Resolve the path to obtain the corresponding BaseFile.
            BaseFile resolvedObject = pathResolver.resolvePath(path);

            if (resolvedObject instanceof DirectoryObject) {
                dirToCd = (DirectoryObject) resolvedObject;

                // To cd in Directory need Reade & Execute
                PermissionChecker pemChecker = new PermissionChecker(dirToCd.getPermissions(), operatingSystem.getCurrentUser());
                if(!(pemChecker.isCanExecute() && pemChecker.isCanRead())) throw new PermissionDeniedException();

                filesystem.setCurrentDirectory(dirToCd);
            } else {
                // Throw an exception if the resolved object is not a directory.
                throw new NotADirectoryException(pathString);
            }
        } catch (FileNotFoundException | NotADirectoryException | PermissionDeniedException e) {
            return e.getMessage();
        }

        // Return an empty string indicating a successful directory change.
        return "";
    }

}
