package commands;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileSystem.Directory;
import fileSystem.FileSystem;

public class Mkdir 
{
  FileSystem fs = FileSystem.getFileSystemFp();

  /*
   * A void method which prints usage message saying cannot create the
   * directory entered.
   */
  private void cannotAdd(String path) {
    System.out.println("mkdir: cannot add " + path); }
  
  /*
   * Method returns boolean - returns true if path has special characters,
   * else false. Takes in path name of type String. Uses in-build matches() 
   * method to find out if path has special characters or not.
   */
  private boolean hasSpecialChar(String path){
    Pattern regex = Pattern.compile("^[a-zA-Z0-9]*");
    Matcher m = regex.matcher(path);
    if(!m.matches()){
      return true; }
    return false; }
    //check for the special characters
    //boolean hasSpecialChar = path.matches("^[\\w.-]+$");
    //return hasSpecialChar; } 

  /*
   * A void method which iterates through all paths entered and creates the 
   * directory if valid path. Method takes in a list of type Strings where
   * each string is a path name. Note: Cannot have special characters in the 
   * directory name - skips through next path if it has, else creates the 
   * directory in the path entered. The method handles of both absolute and 
   * relative path.
   */
  public void execute(List <String> pathnames) {
    for (int i = 0; i < pathnames.size(); i++) {
      Directory root = fs.getRoot();
      String path = pathnames.get(i);
      //call helper function to check if path has special chars or not
      //print usage message and skip to next iteration (ie. next path)
      
      if((hasSpecialChar(path))){ cannotAdd(path); continue; }
      
      // Change string into preferred format
      if (path.length() != 1 && path.endsWith("/"))
        path = path.substring(0, path.length() - 1);

      if (path.contains("/")) {
        // If path present, must check path
        // Splitting given path into multiple parts to check validity
        String[] parts = path.split("/");
        String path1 = path.substring(0, path.lastIndexOf("/"));
        String path2 = parts[parts.length - 1];

        // If path is absolute
        if (path.startsWith("/")) {
          // If path is in the format of "/something"
          Directory dir = new Directory(path2);
          if (parts.length == 2) {
            if (!root.addDir(dir)) this.cannotAdd(path2); }
          // If path is not in the format of "/something" ie. longer
          else if (fs.isValidPath(path1)) {
            Directory tempdir = fs.goToDirectoryInPath(path1);
            if (!tempdir.addDir(dir)) {
              this.cannotAdd(path2); }
          } // If the directory isn't valid, print error message
          else 
            this.cannotAdd(path2);
        // If path is relative
        } else {
          Directory dir = new Directory(path2);
          if (fs.isValidPath(path1)) {
            Directory tempdir = fs.goToDirectoryInPath(path1);
            if (!tempdir.addDir(dir)) {
              this.cannotAdd(path2); } }else{ this.cannotAdd(path2); } }
        // If path is given, but no slashes present
      } else { Directory tempdir = new Directory(path);
        if (!(fs.getCurrentWorkingDirectory().addDir(tempdir))) {
          this.cannotAdd(path); } } } }

  
  /**
   * Manual page for mkdir class. Will be called by Man command.
   */
  public void manual(){
  System.out.println("Create the DIRECTORY (ies), if they do not already "
      + "exist.\n usage: mkdir DIR ...");
  }
  
}
