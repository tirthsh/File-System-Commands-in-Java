package test;

import commands.Exit;

/**
 * A test class for Exit command using insertion testing
 * @author Manan
 *
 */
public class ExitTest {
  
  /**
   * A method to use for integration testing of exit
   * @param args
   */
  public static void main(String[] args) {
    // check creation of the exit object
    Exit ExitCommand = new Exit();
    // should print out type of class as Exit
    System.out.println("Class is of type: " + ExitCommand.getClass()+ "\n");
    
    // By default the ShellOn boolean will be true
    System.out.println("Before executing: " + Exit.ShellOn + "\n");
    
    // Using the execute method turns ShellOn to false
    ExitCommand.execute();
    System.out.println("After executing: " + Exit.ShellOn + "\n");
    
    // Testing the man page for Exit
    ExitCommand.manual();
  }
}
