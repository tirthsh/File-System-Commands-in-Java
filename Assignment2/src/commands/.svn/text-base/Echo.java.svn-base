package commands;
import java.util.ArrayList;
import java.util.List;

import exceptions.UsageError;
/**
 * Requires a fileSystem for constructor.
 * Prints out the given String in Standard Output. If an Outfile is provided,
 * redirect output of echo to outfile. 
 * >OUTFILE rewrites contents in Outfile with the new string
 * >>OUTFILE appends given string to Outfile
 */
public class Echo{
  public Echo(){
  }
  /**
   * Given a list of strings, either print the string to standard output
   * or Overwrite the outfile with the string or append the string to contents 
   * in outfile and print nothing. If outfile doesn't exist, create a file with
   * those contents.
   */
  public void execute(List<String>cmd){
    try {
      cmd = ParseQuotes(cmd);
    }
    catch (UsageError e){
      System.out.println(e.getMessage());
      return ;
    }
    // if list size is greater than one then check for possible redirection
    if (cmd.size() > 1){
      String filepath = "";
      boolean toRedirect = false;
      boolean append = false;
      boolean overwrite = false;
      String writeString = "";
      for (int i=0; i<cmd.size(); i++){
        filepath = cmd.get(i);
        // check if you must append to a file 
        // file path starts with ">>"
        if (filepath.matches(">>.*")){
          toRedirect = true;
          append = true;
          overwrite = false;
          filepath = filepath.substring(2);
        }
        // check if you must overwrite the file
        // file path starts with ">"
        else if (filepath.matches(">.*")){
          toRedirect = true;
          append = false;
          overwrite = true;
          filepath = filepath.substring(1);
        }
        else if (filepath.matches("\".*\"")){
          // remove the beginning end quotes
          writeString += filepath.substring(1, filepath.length()-1) + " ";
        }
      }
      if (toRedirect){
        Redirection.handleRedirection(overwrite, append, filepath, writeString);
      }
      else{
        System.out.println(writeString);
      }
    }
    else{
      if (cmd.get(0).matches("\".*\"")){
        System.out.println(cmd.get(0).substring(1, 
            cmd.get(0).length()-1));
      }
    }
  }
  /**
   * takes in a list of string arguments and check them for quotes.
   * for every closed quotation bracket, 
   * concatonate the split strings into a single string
   * example: "hello, world" -> "hello world"
   * @param cmd
   * @return
   */
  private List<String> ParseQuotes(List<String> cmd)
    throws UsageError{
    // echo can't have cmd with quotes not closed (echo "hello)
    boolean evenbracket = true;
    String quoteString = "";
    List<String> quoteCont = new ArrayList<String>();
    int i = 0;
    while (i<cmd.size()){
      if(evenbracket){
        // for case with 
        if (cmd.get(i).startsWith("\"")){
          if (cmd.get(i).endsWith("\"")){
            quoteCont.add(cmd.get(i));
          }
          else{
            quoteString += cmd.get(i);
            evenbracket = false; 
          }
          i++;
        }
        else{
          quoteCont.add(cmd.get(i));
          i++;
        }
      }

      else{
        if (cmd.get(i).endsWith("\"")){
          evenbracket = true;
          quoteString += " " + cmd.get(i);
          quoteCont.add(quoteString);
          quoteString = "";
          i++;
        }
        else {
          quoteString += " " + cmd.get(i);
          i++;
        }
      }
    }
    if (!evenbracket){
        throw new UsageError("Echo command arguments without closed quotes");
    }
    return quoteCont;
  }
  /**
   * manual for echo
   */
  public void manual(){
    System.out.println("Prints the string given to Standard output\n"
    +"redirect output to file if file path + >>/> is given\n"
    +">> appends to file, > overwrites file\n"
    +"create new file with the given string if filepath not valid\n"
    + "usage: echo STRING [>>OUTFILE || >OUTFILE]\n");
  }
}
