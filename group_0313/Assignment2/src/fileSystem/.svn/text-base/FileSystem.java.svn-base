package fileSystem;

public class FileSystem {

  private Directory root;
  private Directory currentWorkingDirectory;
  private static FileSystem fp;

  /**
   * Constructor for class
   */
  private FileSystem() {
    root = new Directory("root");
    root.makeRoot();
    setCurrentWorkingDirectory(root);
  }

  /**
   * Singleton design
   * 
   * @return FileSystem returns a new file system if one doesn't already exist
   */
  public static FileSystem getFileSystemFp() {
    if (fp == null) {
      fp = new FileSystem();
    }
    return fp;
  }

  /**
   * Sets working direcory to root, and returns root
   * 
   * @return Directory root directory
   */
  public Directory goToAndReturnRoot() {
    setCurrentWorkingDirectory(root);
    return root;
  }

  /**
   * Helper of isValidPath, needed to handle absolute and relative path
   * 
   * @param a 1 if absolute, 0 if relative
   * @param parts split string of path by '/'
   * @param curDir starting directory
   * @return boolean true if valid otherwise false
   */
  private boolean isValidPathHelper(int a, String[] parts, Directory curDir) {
    Directory dir = curDir;
    for (int i = a; i < parts.length; i++) {
      if (!(dir.DirExists(parts[i]))) {
        return false;
      } else if (parts[i].equals("..")) {
        dir = dir.getParent();
      } else if (parts[i].equals(".")) {
        continue;
      } else {
        dir = dir.getSubDirWithName(parts[i]);
      }
    }
    return true;
  }

  /**
   * Check if a given path is valid in the current file system.
   * 
   * Requirement: Can be absolute or relative path, however the path must not
   * end with a file. ie: path must consist entirely of directories.
   * 
   * @param path String path to be checked
   * @return boolean true if valid otherwise false
   */
  public boolean isValidPath(String path) {
    // Split the path by denominator '/'
    String[] parts = path.split("/");

    // If its an absolute path
    if (path.startsWith("/")) {
      Directory curDir = getRoot();
      return isValidPathHelper(1, parts, curDir);
    }

    // If its a relative path
    else {
      Directory curDir = getCurrentWorkingDirectory();
      return isValidPathHelper(0, parts, curDir);
    }
  }

  /**
   * Helper function for goToDirectoryInPath, needed to handle absolute and
   * relative path
   * 
   * @param a 1 if absolute, 0 if relative
   * @param parts split string of path by '/'
   * @param curDir starting directory
   * @return Last directory in path
   */
  private Directory goToDirectoryInPathHelper(int a, String[] parts,
      Directory curDir) {
    Directory dir = curDir;
    for (int i = a; i < parts.length; i++) {
      String h = parts[i];
      dir = dir.getSubDirWithName(h);
    }
    return dir;
  }

  /**
   * Returns the last directory in path
   * 
   * Requirement: Must be valid path, can be absolute or relative path, however
   * the path must not end with a file. ie: path must consist entirely of
   * directories.
   * 
   * @param path String path to be navigated with
   * @return Directory last directory in path
   */
  public Directory goToDirectoryInPath(String path) {
    // Split the path by delimiter '/'
    String[] parts = path.split("/");

    // If its an absolute path
    if (path.startsWith("/")) {
      Directory curDir = getRoot();
      return goToDirectoryInPathHelper(1, parts, curDir);
    }

    // If its a relative path
    else {
      Directory curDir = getCurrentWorkingDirectory();
      return goToDirectoryInPathHelper(0, parts, curDir);
    }
  }

  /**
   * Gets current working directory
   * 
   * @return Directory current working directory
   */
  public Directory getCurrentWorkingDirectory() {
    return currentWorkingDirectory;
  }

  /**
   * Sets current working directory
   * 
   * @param currentWorkingDirectory Directory to be set
   */
  public void setCurrentWorkingDirectory(Directory currentWorkingDirectory) {
    this.currentWorkingDirectory = currentWorkingDirectory;
  }

  /**
   * Returns root
   * 
   * @return Directory root
   */
  public Directory getRoot() {
    return root;
  }

  /**
   * Calculates the absolute path of the current working directory
   * 
   * @return String representation of Path
   */
  public String calculateCurrentPath() {
    if (!(getCurrentWorkingDirectory().isRoot())) {
      Directory curDir = getCurrentWorkingDirectory();
      String path = "";
      while (!curDir.isRoot()) {
        path = curDir.getName() + "/" + path;
        curDir = curDir.getParent();
      }
      path = "/" + path;
      return path;
    } else {
      return "/";
    }
  }
}
