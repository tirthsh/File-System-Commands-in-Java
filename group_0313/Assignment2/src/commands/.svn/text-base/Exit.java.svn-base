package commands;
/**
 * A class for the exit command that when entered will close the JShell
 */
public class Exit {
  public static boolean ShellOn = true;
  // default constructor does nothing
  public Exit() {}
  /**
   * A method to return the manual for exit. Will be called by Man command.
   */
  public void execute() {
    ShellOn = false;
  }
  public void manual()
  {
    System.out.println("Upon entering 'exit' JShell will exit\n"
        + "usage: exit\n");
  }
}
