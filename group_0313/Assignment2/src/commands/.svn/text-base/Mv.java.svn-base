package commands;

import exceptions.*;
import fileSystem.*;

public class Mv {
  FileSystem fs = FileSystem.getFileSystemFp();

  /**
   * Executes the Move command
   * 
   * @param from From path to be copied
   * @param to To path to be copied to
   * @throws InvalidDirectoryPath If any pathing is invalid
   */
  public void execute(String from, String to) throws InvalidDirectoryPath {
    InvalidDirectoryPath invalidToPathException = new InvalidDirectoryPath(to);
    InvalidDirectoryPath invalidFromPathException =
        new InvalidDirectoryPath(from);

    if (!fs.isValidPath(to)) {
      throw invalidToPathException;
    }

    Directory toDir = fs.goToDirectoryInPath(to);
    FileSys toBeMoved = null;

    // Change string into preferred format
    if (from.length() != 1 && from.endsWith("/"))
      from = from.substring(0, from.length() - 1);

    if (from.contains("/")) {
      // If path present, must check path
      // Splitting given path into multiple parts to check validity
      String[] parts = from.split("/");
      String path1 = from.substring(0, from.lastIndexOf("/"));
      String path2 = parts[parts.length - 1];

      // If path is absolute
      if (from.startsWith("/")) {

        // if path is specifically in the form of "/something"
        if (parts.length == 2) {
          Directory tempdir = fs.getRoot();
          if (tempdir.getDirName().contains(path2)) {
            toBeMoved = fs.getRoot().getSubDirWithName(path2);
            fs.getRoot().deleteDirectory(parts[1]);
          } else if (tempdir.getFileName().contains(path2)) {
            toBeMoved = fs.getRoot().getFileWithName(path2);
            fs.getRoot().deleteFile(parts[1]);
          } else {
            throw invalidFromPathException;
          }

          // if not in specific format, ie. longer
        } else if (fs.isValidPath(path1)) {
          Directory tempdir = fs.goToDirectoryInPath(path1);
          if (!tempdir.getAllNames().contains(path2)) {
            throw invalidFromPathException;
          } else if (tempdir.getDirName().contains(path2)) {
            toBeMoved = tempdir.getSubDirWithName(path2);
            tempdir.deleteDirectory(path2);
          } else {
            toBeMoved = tempdir.getFileWithName(path2);
            tempdir.deleteFile(path2);
          }
        } else {
          throw invalidFromPathException;
        }

        // if path is relative
      } else {
        if (fs.isValidPath(path1)) {
          Directory tempdir = fs.goToDirectoryInPath(path1);
          if (!tempdir.getAllNames().contains(path2)) {
            throw invalidFromPathException;
          } else if (tempdir.getDirName().contains(path2)) {
            toBeMoved = tempdir.getSubDirWithName(path2);
            tempdir.deleteDirectory(path2);
          } else {
            toBeMoved = tempdir.getFileWithName(path2);
            tempdir.deleteFile(path2);
          }
        } else {
          throw invalidFromPathException;
        }
      }

      // If path is given, but no slashes present
    } else {
      if (fs.isValidPath(from)) {
        Directory tempdir = fs.getCurrentWorkingDirectory();
        if (!tempdir.getAllNames().contains(from)) {
          throw invalidFromPathException;
        } else if (tempdir.getDirName().contains(from)) {
          toBeMoved = tempdir.getSubDirWithName(from);
          tempdir.deleteDirectory(from);
        } else {
          toBeMoved = tempdir.getFileWithName(from);
          tempdir.deleteFile(from);
        }
      } else {
        throw invalidFromPathException;
      }
    }

    //Adding the directory to the To path, and setting its parent
    if (toBeMoved.isDir()) {
      if (!toDir.addDir((Directory) toBeMoved)) {
        throw invalidToPathException;
      } else {
        toBeMoved.setParent(toDir);
      }
    } else {
      if (!toDir.addFile((File) toBeMoved)) {
        throw invalidToPathException;
      } else {
        toBeMoved.setParent(toDir);
      }
    }
  }
  public void manual() {
    System.out.println("Move item OLDPATH to NEWPATH. Both"
        + " OLDPATH and \nNEWPATH may be relative to the current directory "
        + "or may be full \npaths. If NEWPATH is a directory "
        + "move the item into the directory.");
  }
}