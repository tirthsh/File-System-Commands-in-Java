package driver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


  public class Validator {

  public String deleteLeadingWhiteSpace(String command){
    String noLeadingString = command.replaceAll("^\\s+", "");
    return noLeadingString;
  }
    
  /*
   * This method takes in a string command and parses the string into a string
   * array by spaces. Returns the parsed array of strings. 
   */
  public String [] splitStringBySpace(String command){
    
      String whiteSpacesRemvoved = this.deleteLeadingWhiteSpace(command);
      //split by spaces
      String [] splitStr = whiteSpacesRemvoved.split("\\s+");
      return splitStr;
  }

  /*
   * This method validates if the number of arguments entered in the string 
   * command is correct. If it is then returns the following string list: 
   * {"true", "command","path1","path2",...,"path n"}. If the command enterd
   * is not one of the commands or if the number of arguments entered is 
   * incorrect then the following format is printed: {"false","null","null"}.
   */
  public String [] ValidateCommand(String command){
    
    //store all valid commands into a string array
    String[] allCommands = {"cat","cd","ls","mkdir","history","pushd",
        "exit","echo","pwd","man","mv","cp","curl","grep","popd"};
    //use helper function to parse the string input by spaces
    String[] splitStr = splitStringBySpace(command);
    int length_of_command = splitStr.length;
    boolean valid = false;
    boolean exclamation = false;
    String stringToCheck = splitStr[0].substring(1);

    
    //the string array that is returned when command doesn't exist or number 
    //arguments entered are incorrect
    String [] errorPair = new String[3];
    errorPair[0] = "false";
    errorPair[1] = "null";
    errorPair[2] = "null";
    
    //the string array that is returned when it has one or no paths
    String [] firstCasePair = new String[3]; firstCasePair[0] = "true";
    
    //the string array that is returned when it has more than 1 path
    String [] secondCasePair = new String[splitStr.length + 1];
    secondCasePair[0] = "true"; secondCasePair[1] = splitStr[0];
    
    
    //List<String> myList = Arrays.asList(all_commands);
    //check if command entered is a valid command
    if((Arrays.asList(allCommands).contains(splitStr[0])) || 
        splitStr[0].startsWith("!")){
      
      //use respectable helper functions for each valid command to figure out
      //if number of arguments entered for that specific argument is correct
      if (splitStr[0].equals("cat")){ valid = cat(length_of_command); }
      //need at least 1 arg(can take more)
      else if (splitStr[0].equals("mkdir")){ valid = mkdir(length_of_command); }
      else if (splitStr[0].equals("pushd")){ valid = pushd(length_of_command); }
      else if (((splitStr[0]).equals("mv")) || (splitStr[0].equals("cp"))){
        valid = cpOrMv(length_of_command); }
      else if (splitStr[0].equals("grep")){ valid = grep(length_of_command); }
      else if (splitStr[0].equals("popd")){ valid = popd(length_of_command); }
      //for exclamation command, length must be and must start with '!'
      else if (splitStr[0].startsWith("!") && (length_of_command == 1)){
        exclamation = true; valid = exclamation(stringToCheck); }
      else if (splitStr[0].equals("curl")){ valid = curl(length_of_command); }
      else if ((splitStr[0].equals("exit")) || (splitStr[0].equals("echo")) || 
          (splitStr[0].equals("pwd")) || (splitStr[0].equals("cd")) || 
          (splitStr[0].equals("history")) || (splitStr[0].equals("man")) ||
          (splitStr[0].equals("ls"))){ valid = true; }
      else valid = false; //valid is false by default
      
      //print appropriate string array - depending on its validity
      if(valid){
        if (splitStr.length == 1){
          if(exclamation){
            firstCasePair[1] = "!"; firstCasePair[2] = stringToCheck; 
          }
          else{
            firstCasePair[1] = splitStr[0]; firstCasePair[2] = null; 
          }
          return firstCasePair; }
        else if (splitStr.length == 2){
          firstCasePair[1] = splitStr[0]; firstCasePair[2] = splitStr[1];
          return firstCasePair; }
        else{
          int i;
          for (i=2; i <= splitStr.length; i++)
            secondCasePair[i] = splitStr[i-1];
          return secondCasePair; //returns {boolean, command, path}
        } } 
      else{ return errorPair; } } //doesn't have the right number of arguments

    else{    
      //not one of the commands ... returns {false, null, null}
      return errorPair; } }
  
  /*
   * This method returns a boolean for the cat command. cat needs to have at 
   * least 1 argument. Can more have due to redirection and since you can cat
   * more than 1 file now. Returns true if it has otherwise false.
   */
  public boolean cat(int length){
        
    if (length == 1)
      return false;
    //can now display information for 1 or more files
    return true;
  }
  
  
  /*
   * This method returns a boolean for the mkdir command. mkdir needs to have 
   * at least 1 argument since it can create more than 1 directories at the
   * same time. Returns true if it has, otherwise false.
   */
  public boolean mkdir(int length){
    
    if (length >= 2)
      return true;
    return false; 
  }
  
  /*
   * This method returns a boolean for the pushd command. pushd command needs
   * 1 argument. Returns true if it does, otherwise false.
   */
  public boolean pushd(int length){
    
    if (length == 2) 
      return true;
    return false;
  }
  
  /*
   * This method returns a boolean for the popd command. popd command needs
   * no arguments. Returns true if it doesnt have any, otherwise false.
   */
  public boolean popd(int length){

    if (length == 1) 
      return true;
    return false;
  }
  
  /*
   * This method returns a boolean for the cp or mv command. cp or mv commands
   * needs to have 2 arguments (orginalfile_path newfile_path). Returns true if
   * it does, otherwise false.
   */
  public boolean cpOrMv(int length){

    if(length == 3) 
      return true;
    return false;
  }
  
  /*
   * This method returns a boolean for the grep command. grep command
   * needs to at least have 2 arguments. Can have more than 2 arguments due to 
   * redirection. Returns true if it does, otherwise false.
   */
  
  public boolean grep(int length){

    //boolean option = false;
    if (length >= 2) //need at least 2 arg 
      return true;      
    return false;
  }
  
  /*
   * This method returns a boolean for the curl command. curl command
   * can only have 1 argument. Returns true if it does, otherwise false.
   */
  
  public boolean curl(int length){
    
    if(length == 2)
      return true;
    return false;
  }
  
  /*
   * This method returns a boolean for the exclamation command. exclamation
   * command can only have 1 string argument. Returns true if string entered 
   * has only numbers else returns false.
   */
  public boolean exclamation(String stringToCheck){
    //reg expression for 'only digits' 
    Pattern reg = Pattern.compile("\\d+");
    Matcher m = reg.matcher(stringToCheck);
    return m.matches(); 
  }
  
}
