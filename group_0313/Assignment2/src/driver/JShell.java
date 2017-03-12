// ********************
// Assignment2:
// Student1: Yinan Nick Gong
// UTORID user_name: gongyi2
// UT Student #: 999709873
// Author: Nick :)
//
// Student2: Jerry Cheng
// UTORID user_name: jcheng38
// UT Student #: 1001561543
// Author: Jerry
//
// Student3:Manan Patel
// UTORID user_name:patelm55
// UT Student #:1000679795
// Author:
//
// Student4:Tirth Shah
// UTORID user_name:shahtirt
// UT Student #:1001396937
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *******************
package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Hashtable;


import commands.*;
import exceptions.InvalidDirectoryPath;
import fileSystem.FileSystem;


public class JShell {

  // String.matches(^[-a-zA-Z_]+)
  public static final String EXIT_COMMAND = "exit";

  /**
   * Main driver method, checks the input for command and path validitiy, if
   * valid and file/dir exists, execute that command on the file/dir and reflect
   * changes to file system.
   * 
   * @param input from user
   * @throws InvalidDirectoryPath
   */
  public static void main(final String[] args) throws IOException,
      ClassNotFoundException, InstantiationException, IllegalAccessException, InvalidDirectoryPath {

    // Create a buffered reader to read the input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    History HistoryCommand = new History();
    Exit ExitCommand = new Exit();

    System.out.println("Enter some commands, or " + EXIT_COMMAND + " to quit");

    // keep repeating until exit command
    boolean on = true;
    while (Exit.ShellOn) {
      System.out.print("> ");

      // store the input line as a string
      String input = br.readLine();

      // create objects of each command
      FileSystem fs = FileSystem.getFileSystemFp();
      HistoryCommand.add(input);
      Cat CatCommand = new Cat();
      Cd CdCommand = new Cd();
      Curl CurlCommand = new Curl();
      Cp CpCommand = new Cp();
      Echo EchoCommand = new Echo();
      Grep GrepCommand = new Grep();
      Ls LsCommand = new Ls();
      Man ManCommand = new Man();
      //Mkdir MkdirCommand = new Mkdir();
      Mv MvCommand = new Mv();
      Pwd PwdCommand = new Pwd();
      PushD PushdCommand = new PushD();
      Popd PopdCommand = new Popd();
      Redirection RedirCommand = new Redirection();
      
      //Hashtable<String, String> commands = new Hashtable<String, String>();
      //commands.put("cd", "Cd");
      //commands.put("cat", "Cat");
      //commands.put("cp", "Cp");
      //commands.put("curl", "Curl");
      //commands.put("mkdir", "Mkdir");
      //commands.put("mv", "Mv");
      //commands.put("history", "History");
      //commands.put("ls", "Ls");
      //commands.put("man", "Man");
      //commands.put("pushd", "Pushd");
      //commands.put("popd", "Popd");
      //commands.put("echo", "Echo");
      //commands.put("pwd", "Pwd");
      
      //Class<?> is_class = Class.forName((String) commands.get(splitStr[0]));
      //commandSuper file = (commandSuper) is_class.newInstance(); 
      
      //file.getMethods();

      // create a Validator object to validate the command
      Validator validator = new Validator();
      String[] isValid = validator.ValidateCommand(input);

      if (input.length() == 0) {}
      // if input is 1 char, return error
      else if (input.length() == 1) {
        System.out.println(
            "That is not a valid command, please enter a" + " valid command.");}

      // else its a possible viable output
      else {
        // call and store the validator method validate_command
        // which checks for the correct commands, arguments and paths
        // if validator returns true for 1st index then its a valid command
        if (isValid[0].equals("true")) {
          
          // if the command is history by itself, run the execute method
          if (isValid[1].equals("history") && isValid[2] == null) {
            HistoryCommand.execute();
          }
          // if the command is history and a number, convert that string to an
          // int and pass it to the executeUpto method
          else if (isValid[1].equals("history") && isValid.length == 3) {
            int historyIndex = 0;
            try {
              historyIndex = Integer.parseInt(isValid[2]);
            } 
            //if the history index is not an int
            catch (NumberFormatException e) {
              System.out.println("Index for history is invalid");
            }
            HistoryCommand.executeUpto(historyIndex);
          }
          // if the command is cd, call the Cd execute method
          else if (isValid[1].equals("cd")) {
            CdCommand.execute(isValid[2]);
          }
          else if (isValid[1].equals("curl")) {
            CurlCommand.execute(isValid[2]); 
          }
          else if (isValid[1].equals("cp")) {
            try {
            CpCommand.execute(isValid[2], isValid[3]);
            }
            catch (InvalidDirectoryPath e) {
              System.out.println("Invalid path, please enter a valid path");
            }
          }
          // if the command is ls, call the Ls execute method
          else if (isValid[1].equals("ls")) {
            String[] path = Arrays.copyOfRange(isValid, 2, isValid.length);
            LsCommand.execute(path);
          } 
          else if (isValid[1].equals("cat")) {
            String[] path = Arrays.copyOfRange(isValid, 2, isValid.length);
            List<String> listPath = new ArrayList<String>(Arrays.asList(path));
            CatCommand.execute(listPath);
          }
          else if (isValid[1].equals("exit")) {
            Exit.ShellOn = false;
            System.out.println("Exiting, Thank you for using JShell");
          }
          else if (isValid[1].equals("echo")) {
            String[] path = Arrays.copyOfRange(isValid, 2, isValid.length);
            List<String> listPath = new ArrayList<String>(Arrays.asList(path));
            EchoCommand.execute(listPath);
          }
          else if (isValid[1].equals("grep")){
            String[] grepList = Arrays.copyOfRange(isValid, 2, isValid.length);
            List<String> grepList1 = 
                new ArrayList<String>(Arrays.asList(grepList));
            GrepCommand.execute(grepList1); }
          else if (isValid[1].equals("mv")) {
            try {
              MvCommand.execute(isValid[2], isValid[3]);
            } catch (InvalidDirectoryPath e) {
              e.printStackTrace();
            }
          }
          else if (isValid[1].equals("pwd")) {
            PwdCommand.execute();
          } 
          else if (isValid[1].equals("pushd")) {
            PushdCommand.execute(isValid[2]);
          }
          else if (isValid[1].equals("popd")) {
            PopdCommand.execute();
          }
          else if (isValid[1].equals("mkdir")) {
          //  String[] path = Arrays.copyOfRange(isValid, 2, isValid.length);
          //  List<String> listPath = new ArrayList<String>(Arrays.asList(path));
          //  MkdirCommand.execute(listPath);
          } 
          else if (isValid[1].equals("man")) {
            try {
            ManCommand.execute(isValid[2]);
            }
            catch (NullPointerException e) {
              System.out.println("Man lacks Command input\n"
                  + "Usage: Man Command");
            }
          }
        }
        // if the validator determines the command entered is valid, return
        // error.
        else {
          System.out.println("That is not a valid command, please enter"
              + " a valid command.");
        }
      }
    }
  }
}