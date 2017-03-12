package commands;
import fileSystem.*;
import commands.Redirection;

/**
 * Requires a fileSystem for constructor.
 * Returns the path for the current working Directory.
 * @author Jerry 
 */
public class Pwd {
  private FileSystem fs;
  public Pwd(){
    this.fs = FileSystem.getFileSystemFp();
  }
  /**
   * prints the path for the current working directory
   */
  public void execute(){
    System.out.println(this.fs.calculateCurrentPath());
  }
  /**
   * Enters the path for the current working Directory.
   * to an outfile, if outfile has a valid path.
   * @param String
   * @return none
   */
  public void execute(String path){
      if (path.matches(">>.*")){
        path = path.substring(2);
        Redirection.handleRedirection(false, true, path, 
            this.fs.calculateCurrentPath());
      }
      else if (path.matches(">.*")){
        path = path.substring(1);
        Redirection.handleRedirection(true, false, path, 
            this.fs.calculateCurrentPath());
      }
      else{
        System.out.println("pwd: usage: [(>>, >)OUTFILE]");
      }
  }
  /**
   * usage manual for pwd
   * @param none
   * @return none
   */
  public void manual (){
    System.out.println("Returns the path of the current working directory\n"
        + "usage: pwd\n");
  }
}
