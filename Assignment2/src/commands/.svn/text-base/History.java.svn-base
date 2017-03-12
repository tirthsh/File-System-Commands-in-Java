package commands;

import java.util.ArrayList;
import java.util.List;

import driver.JShell;

/**
 * Requires a fileSystem for constructor.
 * Returns the fileContents of every file with a valid filePath. 
 */
public class History{
  private int numberOfCommands =1;
  
  // create a List of String to hold the inputs
  List<String> history_array = new ArrayList<String>();  
  
  public History() {}
  
  /**
   * Method that adds each input to the history_array
   * @param input
   */
  public void add(String input)
  {
    history_array.add(input);
  }
  
  public void printContents(){
    for(int i = 0; i < history_array.size(); i++) {
      String value = history_array.get(i);
      System.out.println(value);
    }
    System.out.println("\n");
  }
  
  /**
   * Method that prints all the elements in the history array with a number
   * corresponding to when the input was entered.
   * @param none
   */
  public void execute() 
  {
    int loopIndex = 0;
    
    // loop through the history array and print every element
    while (loopIndex < history_array.size())
      {
        System.out.println((loopIndex+1) + ". " + history_array.get(loopIndex));
        loopIndex++;
        numberOfCommands++;
    }
  }
  
  /**
   * Method that prints all the elements in the history array with a number
   * corresponding to when the input was entered.
   * @param howMany, the index you want to start from
   */
  public void executeUpto(int howMany)
  {
    int index = 0;
    // if the history for more commands was asked then there was entered
    // return error
    if (howMany > history_array.size() && howMany > 0)
    {
      System.out.println("Index is out of range, not enough previous history "+
     "inputs. Please enter an index within range of number of commands.");
    }
    else if (howMany == 0) 
    {
    }
    else {
      
      // get the difference between the size of the array and what index they
      // want to start the history from (aka the number to start from) 
      index = (history_array.size() - howMany);
      
      // keep looping from index you wanted until the end
      while (index < history_array.size())
      {
        try{
          System.out.println(index+1 + ". " + history_array.get(index));
          index++;
          howMany++;
        }
        
        // KNOWN BUG: If history.execute() is entered before 
        // history.executeUpTo() then gives indexOutOfRangeError
        // Index: X, Size: X
        catch (IndexOutOfBoundsException e){
        }
      }
    }
  }
  
//  public String numberExecute(int number) {
//   String command = history_array.get(number);
    
//  }
    
  /**
   * A method to return number of entries in the history_array as an int.
   * @return the number of entries in the history_array (number of commands 
   * input into JShell)
   */
   public int ammountOfentries()
   {
     return history_array.size();
   }
   
   /**
    * A method to return the manual for history. Will be called by Man command.
    */
  public void manual()
  {
    System.out.println("returns all the previous commands entered into JShell "
        + "(with an optional index\nparamater that prints the recent number of"
        + "index entries).\n" + "usage: history " + "[index]\n");
  }
    
}
