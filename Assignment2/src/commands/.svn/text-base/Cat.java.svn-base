package commands;
import java.util.List;

import exceptions.InvalidDirectoryPath;
import fileSystem.File;
import fileSystem.Directory;
import fileSystem.FileSystem;
/**
 * Requires a fileSystem for constructor.
 * Returns the fileContents of every file with a valid filePath. 
 */
public class Cat{
  private FileSystem fs;

  public Cat(){
    this.fs = FileSystem.getFileSystemFp();
  }
  /**
   * execute(List<String> pathNames)
   * This method takes in a list of filepaths and the current working directory
   * returns the contents of every file object that can be found 
   */
  public void execute(List<String>pathNames){
    String retString = "";
    String redirectpath = "";
    boolean toRedirect = false;
    boolean append = false;
    boolean overwrite = false;
    Directory dir = this.fs.getCurrentWorkingDirectory();
    for (int i=0; i<pathNames.size(); i++){
      if (pathNames.get(i).matches(">>.*")){
        toRedirect = true;
        append = true;
        overwrite = false;
        redirectpath = pathNames.get(i).substring(2);
      }
      else if (pathNames.get(i).matches(">.*")){
        toRedirect = true;
        append = false;
        overwrite = true;
        redirectpath = pathNames.get(i).substring(1);
      }
      else{
        // change each path to a step by step representation of file path
        String[] pathStep = this.getPathStep(pathNames.get(i));
        // if pathStep[0] = "", that means it's an absolute path move to the root
        dir = this.checkAbsolutePath(pathStep, dir);
        // at the last step, look for matching string path in fileContents and
        // dircontents
        dir = this.findMatchingDirectory(pathStep, dir);
        try {
          retString += this.findMatchingString(pathStep, dir, pathNames, i);
        }
        catch (InvalidDirectoryPath e){
          System.out.println(e.getMessage());
        }

      }
    }
    if (toRedirect){
      Redirection.handleRedirection(overwrite, append, redirectpath, retString);
    }
    else{
      System.out.println(retString);
    }
  }
  // if it's an absolute path, go to the root
  private Directory checkAbsolutePath(String[] pathStep, Directory dir){
    if (pathStep[0].equals("")){
      dir = this.fs.getRoot();
    }
    return dir;
  }
  private Directory findMatchingDirectory(String[] pathStep, Directory dir){
    int p = 0;
    // while we're not at the last entry in pathNames
    while (p < pathStep.length-1){
      // loop through the list of subdirectories to find
      // directory matching that name in path, if any
      List<Directory> dirList = dir.getDirContents();
      for (int d=0; d<dirList.size(); d++){
        if (pathStep[p].equals("..") && dir.getParent() != null){
          dir = dir.getParent();
        }
        else if (pathStep[p].equals(dirList.get(d).getName())){
          dir = dirList.get(d);
        }
      }
      p++;
    }
    return dir;
  }
  private String findMatchingString(String[] pathStep, Directory dir, 
      List<String>pathNames, int i) throws InvalidDirectoryPath{
    String retString = "";
    int p = pathStep.length - 1;
    boolean hasMatch = false;
    List<File> fileList = dir.getFileContents();
    List<Directory> dirList = dir.getDirContents();
    // if the last step is the special paths . or .., return error message
    // for directories
    if (pathStep[p] == "." || pathStep[p] == ".."){
      hasMatch = true;
      throw new InvalidDirectoryPath("cat: " + pathNames.get(i) 
      + ": is a directory\n");
    }
    // if a match found in fileList, add file's contents to retString
    for (int f=0; f<fileList.size(); f++){
      if (pathStep[p].equals(fileList.get(f).getName())){
        hasMatch = true;
        retString += fileList.get(f).getContents();
      }
    }
    // if a match is found in dirList, add error message to retString
    for (int ld=0; ld<dirList.size(); ld++){
      if (pathStep[p].equals(dirList.get(ld).getName())){
        hasMatch = true;
        throw new InvalidDirectoryPath("cat: " + pathNames.get(i) 
          + ": is a directory\n");
      }
    }
    if (!hasMatch){
      throw new InvalidDirectoryPath("cat: " + pathNames.get(i) +
          ": no such file or directory\n");
    }
    return retString;
  }
  /**
   * helper method for execute. Used to convert a path String to list of Strings
   * each representing one step in the path
   */
  private String[] getPathStep(String path){
    String[] pathStep = path.split("/");
    return pathStep;
  }
  /**
   * usage manual for cat
   */
  public void manual(){
  System.out.println("returns the contents of a file given "
      + "its path in the filesystem\n" + "usage: cat " + "FILE [FILE...]\n");
  }
}