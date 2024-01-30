package com.timprogrammiert.Filesystem.Path;

import com.timprogrammiert.Exceptions.FileNotFoundException;
import com.timprogrammiert.Exceptions.NotADirectoryException;
import com.timprogrammiert.Filesystem.BaseFile;
import com.timprogrammiert.Filesystem.Directories.DirectoryObject;
import com.timprogrammiert.Filesystem.Filesystem;

import java.util.Arrays;
import java.util.List;

/**
 * Author : Tim
 * Date: 29.01.2024
 * Version: 1.0
 */


/**
 * A utility class for resolving file paths within a filesystem.
 */
public class PathResolver {
    Filesystem filesystem;

    /**
     * Constructor for the PathResolver class.
     *
     * @param filesystem The filesystem to be used for path resolution.
     */
    public PathResolver(Filesystem filesystem) {
        this.filesystem = filesystem;
    }

    /**
     * Resolves a given Path, either as a relative or global path.
     *
     * @param path The Path object to be resolved.
     * @return The BaseFile corresponding to the resolved path.
     * @throws FileNotFoundException If the specified path is not found.
     */
    public BaseFile resolvePath(Path path) throws FileNotFoundException, NotADirectoryException {
        String pathString = path.getPathString();

        // Check if the path is global or relative and delegate to the appropriate resolver
        if (pathString.startsWith("/")) {
            return resolveGlobalPath(pathString);
        } else {
            return resolveRelativePath(pathString);
        }
    }

    /**
     * Resolves a relative file path starting in the current directory.
     *
     * @param pathString The path to resolve, where each segment is separated by '/'.
     * @return The BaseFile corresponding to the resolved path.
     * @throws FileNotFoundException If the specified path is not found.
     */
    private BaseFile resolveRelativePath(String pathString) throws FileNotFoundException, NotADirectoryException {
        DirectoryObject currentDirectory = filesystem.getCurrentDirectory();

        // Handle special cases for ".", "..", and empty paths
        if (pathString.isEmpty() || pathString.equals(".")) {
            return currentDirectory;
        } else if (pathString.equals("..") && currentDirectory.getParentFolder() != null) {
            return currentDirectory.getParentFolder();
        }

        // Split the path into individual segments
        String[] subFiles = pathString.split("/");
        BaseFile targetFile = null;

        // Iterate through each segment of the path
        for (String file : subFiles) {
            // Search for a matching child in the current directory
            boolean found = false;
            for (BaseFile baseFile : currentDirectory.getChildObjects()) {
                if (baseFile.getName().equals(file)) {
                    // Found a match, update targetFile and set found to true
                    targetFile = baseFile;
                    found = true;
                    break;
                }
            }

            // Handle the case where the file is not found
            if (!found) {
                throw new FileNotFoundException(pathString);
            }

            // Update current directory for the next iteration
            if (!(targetFile instanceof DirectoryObject)) {
                throw new NotADirectoryException(file);
            }
            currentDirectory = (DirectoryObject) targetFile;
        }

        return targetFile;
    }


    /**
     * Resolves the global file path (absolute path).
     *
     * @param pathString The global path to be resolved.
     * @return The BaseFile corresponding to the resolved global path.
     */
    private BaseFile resolveGlobalPath(String pathString) throws FileNotFoundException, NotADirectoryException {
        // Check if the path is the root ("/")
        if (pathString.equals("/")) {
            return filesystem.getRootFolder();
        }

        // Split the path into sub-names, filter out blank elements, and collect them into a list
        List<String> subNames = Arrays.stream(pathString.split("/"))
                .filter(s -> !s.isBlank())
                .toList();

        // Start with the root folder
        DirectoryObject targetFile = filesystem.getRootFolder();

        // Iterate through each sub-name in the path
        for (int i = 0; i < subNames.size(); i++) {
            String subName = subNames.get(i);
            boolean isLastSegment = (i == subNames.size() - 1);

            boolean found = false;

            // Search for a matching child in the current directory
            for (BaseFile subFile : targetFile.getChildObjects()) {
                if (subFile.getName().equals(subName)) {
                    // Found a match
                    if (subFile instanceof DirectoryObject) {
                        // If it's a directory, update targetFile
                        targetFile = (DirectoryObject) subFile;
                        found = true;
                        break;
                    } else if (isLastSegment) {
                        // Only return FileObjects if its a File
                        return subFile;
                    }else {
                        throw new NotADirectoryException(subNames.get(i));
                    }
                }
            }

            // Handle the case where the sub-name is not found
            if (!found) {
                throw new FileNotFoundException(pathString);
            }
        }

        // Return the target directory after resolving the path
        return targetFile;
    }


}

