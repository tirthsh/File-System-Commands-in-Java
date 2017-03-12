package commands;
import java.util.Arrays;
import java.util.List;

import exceptions.*;
import fileSystem.*;
/**
 * handles file redirection for any cases where redirection is specified
 * @author jerry
 *
 */
public class Redirection {
  public Redirection(){
  }
  /**
   * call this method whenever you need a redirection.
   * requires: String filepath (assume that > or >> is stripped off)
   *           String contents to append to file
   */
  public static void handleRedirection(boolean overwrite, boolean append, 
      String filepath, String contents){
    try{
      // overwrite and append should throw error if both true
      if (overwrite && append){
        throw new MutuallyExclusiveTruths(
            "cmd method error: should not have overwrite and append both true");
      }
      else{
        if (overwrite){
          handleOverwrite(filepath, contents);
        }
        if (append){
          handleAppend(filepath, contents);
        }
      }
    }
    catch (MutuallyExclusiveTruths e){
      System.out.println(e.getMessage());
    }

  }
  /**
   * check whether the file to redirect to exists in filesystem
   * if so, return that file. If it can't find it, return a created file
   * If the directory path leading to the file is invalid, then a path error
   * will be called.
   * @throws Exception 
   */
  private static File fileExists(String path){
    FileSystem fs = FileSystem.getFileSystemFp();
    Directory curdir = fs.getCurrentWorkingDirectory();
    String[] parts = path.split("/");
 // use isvalidpath to check if everything leading up to the filepath in question is valid
    if (parts.length > 1){
      String directorypath = join("/", 
          Arrays.copyOfRange(parts, 0, parts.length - 1));
      System.out.println("\n Directory path: " +
          directorypath + "\n");
      try {
        if (fs.isValidPath(directorypath)){
          curdir = fs.goToDirectoryInPath(directorypath);
        }
        else {
          throw new InvalidDirectoryPath(directorypath);
        }
      }
      catch (InvalidDirectoryPath e){
        // should return error if the directory path is invalid
        System.out.println(e.getMessage() + " is not a valid file path");
        return null;
      }
    }
    // check if the very last step in the path leads to a file
    // return file if true
   
    List<File> fileList = curdir.getFileContents();
    for (int f=0; f<fileList.size(); f++){
      if (fileList.get(f).getName().equals(parts[parts.length-1])){
        return fileList.get(f);
      }
    }
    // otherwise create a file at currently traversed directory
    File redirect = new File("", parts[parts.length-1]);
    curdir.addFile(redirect);
    return redirect;
  }
  private static void handleOverwrite(String filepath, String contents){
    File overwriteTo = fileExists(filepath);
    if (overwriteTo != null){
      overwriteTo.setContents(contents);
    }
  }
  private static void handleAppend(String filepath, String contents){
    File appendTo = fileExists(filepath);
    if (appendTo != null){
      appendTo.append(contents);
    }
  }
  private static String join(String delim, String[] str){
    String retString = "";
    for (int i=0; i<str.length-1; i++){
      retString += str[i] + "/";
    }
    retString += str[str.length-1];
    return retString;
  }
}
