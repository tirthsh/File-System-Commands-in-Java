package commands;

import java.util.EmptyStackException;

import fileSystem.Directory;
import fileSystem.MyStack;
import fileSystem.FileSystem;

/**
 * Class to pop the top element from the stack (a working directoy) and change
 * the current working directory -to the one popped.
 * @author Manan
 */
public class Popd {
  
  // constructor instantiates nothing new
  public Popd() {}
 
  /**
   * Takes in a path in the fileSystem, finds the current working directory
   * by calling fileSystem method, saves the current working directory onto 
   * the stack and changes the current working directory to path
   * @param path
   * @return none
   */
  public void execute(){
    try{
      MyStack myStk = new MyStack();
      FileSystem fs = FileSystem.getFileSystemFp();
      Directory poppedEle = myStk.pop();
      fs.setCurrentWorkingDirectory(poppedEle);
    }
    catch (EmptyStackException e) {
      System.out.println("Cant popd because directory stack is empty");
    }
  }
  
  /**
   * A method to return the manual for popd. Will be called by Man command.
   * @param none
   * @return none
   */  
  public void manual()
  {
    System.out.println("Remove the top entry from the directory stack (a"
        + " working directory)and then\nchanges the current working directory" 
        + " to the one popped from directory stack.\n" 
        + "usage: popd " + "DIR\n");
  }

}
