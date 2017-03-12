package test;

import commands.History;
import driver.*;
/**
 * integration testing for History command with JShell and fileSystem
 * @author Manan
 *
 */
public class HistoryTest {
  /** testing history methods via integration testing
   * @param args
   */
  public static void main(String args[]) {
    // test creation of object
    History HistoryCommand = new History();
    // should print out type of class as History
    System.out.println("Class is of type: " + HistoryCommand.getClass()+ "\n");
    
    /* test adding history elements, usually JShell will add to an array in 
     * History by calling History.add(Str input), lets test that
     * Shouldnt return/print anything
     */
    HistoryCommand.add("pwd");
    HistoryCommand.add("cd A2");
    
    /* History array should contain those two entries, print the arrayList
     * using printContents method, this test will test printContents and add
     * Should print:
     * pwd
     * cd A2
     */
    HistoryCommand.printContents();
    
    /* History command when executed returns all the previous entries 
     * with a corresponding number
     * Should print:
     * 1.pwd
     * 2.cd A2
     */
    HistoryCommand.execute();
    System.out.println("\n");

    /* To test the optional arg for a number to begin history with,
     * lets add a few elements and test if the history prints corrctly from
     * that index
     * Entries so far:
     * 1.pwd
     * 2.cd A2
     */
    HistoryCommand.add("dog woof");
    HistoryCommand.add("mkdir A2");
    HistoryCommand.add("ls");
    
    /* So the Contents are now:
     * 1.pwd
     * 2.cd A2
     * 3.dog woof
     * 4.mkdir A2
     * 5.ls
     * Test from History 3 should print last 3 commands but we must
     * add in History 3 as JShell would do
     */
    HistoryCommand.add("history 3");
    HistoryCommand.executeUpto(2);
    System.out.println("\n");
    
    
    /* If the optional argument is greater than the toal ammount of entries
     * in the history, then return error
     */
    HistoryCommand.executeUpto(999);
    System.out.println("\n");
    
    /* Test the man page method for History
     */
    HistoryCommand.manual();
    System.out.println("\n");
  }
  
}
