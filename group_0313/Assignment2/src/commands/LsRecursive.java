package commands;

import exceptions.*;
import fileSystem.*;

public class LsRecursive {
  FileSystem fs = FileSystem.getFileSystemFp();

  Ls list = new Ls();

  /**
   * Executes the list command recursively
   * @param pathlist paths to be listed
   * @throws InvalidDirectoryPath if any path isn't valid
   */
  public void execute(String[] pathlist) throws InvalidDirectoryPath {
    for (int i = 0; i < pathlist.length; i++) {
      System.out.println("---------------");
      String path = pathlist[i];
      InvalidDirectoryPath invalidToPathException = 
          new InvalidDirectoryPath(path);
      
      // Change string into preferred format
      if (path.length() != 1 && path.endsWith("/"))
        path = path.substring(0, path.length() - 1);
      
      //If path isn't valid
      if(!fs.isValidPath(path)){
        throw invalidToPathException;
      } else{
        System.out.println(fs.goToDirectoryInPath(path)+"\n");
        Directory tempcws = fs.getCurrentWorkingDirectory();
        listRecursive(fs.goToDirectoryInPath(path));
        fs.setCurrentWorkingDirectory(tempcws);
      }
    }
  }
  
  /**
   * Recursive function, recursively traverses the file sytem
   * @param dir Starting directory
   */
  public void listRecursive(Directory dir){
    //Base case, if no more sub directories print all files
    if (dir.getDirContents().isEmpty()){
      fs.setCurrentWorkingDirectory(dir);
      System.out.println(fs.calculateCurrentPath()+":");
      for (int i = 0; i < dir.getFileName().size(); i++)
        System.out.println(dir.getFileName().get(i));
    }else{
      //Recursively call function on all sub directories.
      fs.setCurrentWorkingDirectory(dir);
      System.out.println(fs.calculateCurrentPath()+":");
      for (int i =0; i < dir.getFileName().size(); i++)
        System.out.print(dir.getFileName().get(i)+"  ");
      System.out.println("\n");
      for (int i=0; i < dir.getDirContents().size(); i++)
        listRecursive(dir.getDirContents().get(i));
      System.out.println("\n");
    }
  }
}