package commands;

import fileSystem.MyStack;
import fileSystem.FileSystem;

/**
 * Class to push the current working directory onto a stack and change
 * directories to the one given.
 * @author Manan
 */
public class PushD {
  
  // constructor instantiates nothing new
  public PushD() {}
  /**
   * Takes in a path in the fileSystem, finds the current working directory
   * by calling fileSystem method, saves the current working directory onto 
   * the stack and changes the current working directory to path
   * @param path
   * @return none
   */
  public void execute(String path){
    //*******FIND OUT REASON FOR INDEXOUTOOFRANGE FOR PUSHD A2 , A2 DNE *******
      MyStack myStk = new MyStack();
      FileSystem fs = FileSystem.getFileSystemFp();
      myStk.push(fs.getCurrentWorkingDirectory());
      fs.setCurrentWorkingDirectory(fs.goToDirectoryInPath(path));
  }
  
  /**
   * A method to return the manual for pushd. Will be called by Man command.
   */  
  public void manual()
  {
    System.out.println("Saves the current working directory by pushing it onto"
        + "\n the directory stack and then changes the new current working "
        + " directory to one passed as DIR.\n" + "usage: pushd " + "DIR\n");
  }

}
