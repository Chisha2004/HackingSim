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
     * @throws NotADirectoryException If a subDirectory in the Path is not a Directory.
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
        DirectoryObject subTargetDirectory = filesystem.getCurrentDirectory();

        // Handle special cases for ".", "..", and empty paths
        if (pathString.isEmpty() || pathString.equals(".")) {
            return subTargetDirectory;
        } else if (pathString.equals("..") && subTargetDirectory.getParentFolder() != null) {
            return subTargetDirectory.getParentFolder();
        }

        // Split the path into individual segments
        String[] subFiles = pathString.split("/");
        //BaseFile targetFile = null;

        // Iterate through each segment of the path
        for (int i = 0; i < subFiles.length; i++) {
            boolean isLastSegment = (i == subFiles.length - 1);
            String subName = subFiles[i];
            boolean found = false;

            // Search for a matching child in the current directory
            for (BaseFile baseFile : subTargetDirectory.getChildObjects()) {
                if (baseFile.getName().equals(subName)) {
                    // Found a match
                    if (baseFile instanceof DirectoryObject) {
                        // If it's a directory, update targetFile
                        subTargetDirectory = (DirectoryObject) baseFile;
                        found = true;
                        break;
                    } else if (isLastSegment) {
                        // Only return FileObjects if its a File
                        return baseFile;
                    }else {
                        // PathResolver can also throw NotADirectoryException
                        // It happens if a subDirectory in the Path is a File
                        // e.g /Folder/FILE/Folder/File
                        throw new NotADirectoryException(subFiles[i]);
                    }
                }
            }
            // Handle the case where the file is not found
            if (!found) {
                throw new FileNotFoundException(pathString);
            }
        }
        return subTargetDirectory;
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
                        // PathResolver can also throw NotADirectoryException
                        // It happens if a subDirectory in the Path is a File
                        // e.g /Folder/FILE/Folder/File
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

