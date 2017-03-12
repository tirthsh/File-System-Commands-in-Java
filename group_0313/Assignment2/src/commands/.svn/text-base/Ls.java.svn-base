package commands;

import java.util.List;
import fileSystem.*;
import exceptions.*;

public class Ls {
  FileSystem fs = FileSystem.getFileSystemFp();

  /**
   * Helper class for execute, does actual printing.
   * 
   * @param path1 path leading up to last file or directory
   * @param path2 name of last element, file or directory
   * @throws InvalidDirectoryPath if a path isn't valid
   */
  private void helper(String path1, String path2, InvalidDirectoryPath e)
      throws InvalidDirectoryPath {
    // Navigate to the directory in path and fetch all names
    Directory tempDir = fs.goToDirectoryInPath(path1);
    List<String> names = tempDir.getAllNames();
    // If the file or directory exists, loop through the contents
    // Find the file or directory, and print it
    if (names.contains(path2)) {
      if (path2.equals("..")) {
        System.out.println(tempDir.getParent());
      } else if (path2.equals(".")) {
        System.out.println(tempDir);
      } else {
        List<FileSys> contentList = tempDir.getAllContents();
        for (int i = 0; i < contentList.size(); i++) {
          if (contentList.get(i).getName().equals(path2)) {
            System.out.println(contentList.get(i));
          }
        }
      }
    } // If the name isn't found
    else {
      throw e;
    }
  }

  /**
   * executed the Ls command
   * 
   * @param pathlist paths to be listed
   * @throws InvalidDirectoryPath When path isn't valid
   */
  public void execute(String[] pathlist) throws InvalidDirectoryPath {

    for (int i = 0; i < pathlist.length; i++) {

      String path = pathlist[i];
      InvalidDirectoryPath invalidPathException =
          new InvalidDirectoryPath(path);

      // If no path given, print current working directory
      if (path == null) {
        System.out.println(fs.getCurrentWorkingDirectory());

        // If path is given
      } else {
        // Change string into preferred format
        if (path.length() != 1 && path.endsWith("/"))
          path = path.substring(0, path.length() - 1);

        if (path.equals("/"))
          System.out.println(fs.getRoot());

        else if (path.contains("/")) {
          // If path present, must check path
          // Splitting given path into multiple parts to check validity
          String[] parts = path.split("/");
          String path1 = path.substring(0, path.lastIndexOf("/"));
          String path2 = parts[parts.length - 1];

          // If path is absolute
          if (path.startsWith("/")) {
            // If path is in the format of "/something"
            if (parts.length == 2 && fs.isValidPath(path)) {
              System.out.println(fs.goToDirectoryInPath(path));
            } // If path is not in the format of "/something" ie. longer
            else if (fs.isValidPath(path1)) {
              this.helper(path1, path2, invalidPathException);
            } // If the directory isn't valid, print error message
            else {
              throw invalidPathException;
            }

            // If path is relative
          } else {
            if (fs.isValidPath(path1)) {
              this.helper(path1, path2, invalidPathException);
            } else {
              throw invalidPathException;
            }
          }

          // If path is given, but no slashes present
        } else {

          if (path.equals("..")) {
            System.out.println(fs.getCurrentWorkingDirectory().getParent());
          } else if (path.equals(".")) {
            System.out.println(fs.getCurrentWorkingDirectory());
          } else if (fs.isValidPath(path)) {
            System.out.println(fs.goToDirectoryInPath(path));
          } else {
            throw invalidPathException;
          }
        }
      }
    }
  }

  public void manual() {
    System.out.println(
        "List the contents of the directories specified in PATHs, if no PATHs\n"
            + " are given, print contents of the current working directory. \n"
            + "If the given PATHs leads to a file, print the contents of the \n"
            + "file. If -R option is used, then recursively print all sub "
            + "directories");
  }
}