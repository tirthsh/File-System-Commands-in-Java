package test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.*;


public class ValidatorTest {
  //String input = "mkdir â€œHelloâ€";

  private Validator val;
  
  /*
   * Create an instance of validator.
   */
  @Before
  public void setUp(){
    val = new Validator(); }
  
  /*
   * Check if validator returns false if a wrong command is entered.
   * Use assertTrue to check validity. 
   */
  @Test
  public void testWrongCommand() {
    //sample input
    String input = "pls work";
    String [] valid = val.ValidateCommand(input);
    assertTrue(valid[0].equals("false"));
    assertTrue(valid[1].equals("null"));
    assertTrue(valid[2].equals("null"));
  }
  
  /*
   * Check if validator returns true if a correct command with no 
   * arguments is entered. Use assertTrue to check output of validator. 
   */
  @Test
  public void testRightCommandWithNoArguments(){
    String input = "cd";
    String [] valid = val.ValidateCommand(input);
    assertTrue(valid[0].equals("true"));
    assertTrue(valid[1].equals("cd"));
  }
  
  /*
   * Check if validator returns true if a correct command with 1
   * argument is entered. Use assertTrue to check output of validator. 
   */
  @Test
  public void testRightCommandWithOneArgument(){
    String input = "cat validPath";
    String [] valid = val.ValidateCommand(input);
    assertTrue(valid[0].equals("true"));
    assertTrue(valid[1].equals("cat"));
    assertTrue(valid[2].equals("validPath"));
  }
  
  /*
   * Check if validator returns true if a correct command with multiple
   * arguments is entered. Use assertTrue to check output of validator. 
   */
  @Test
  public void testRightCommandWithMultipleArguments(){
    String input = "ls this is a valid path";
    String [] valid = val.ValidateCommand(input);
    assertTrue(valid[0].equals("true"));
    assertTrue(valid[1].equals("ls"));
    assertTrue(valid[2].equals("this"));
    assertTrue(valid[3].equals("is"));
    assertTrue(valid[4].equals("a"));
    assertTrue(valid[5].equals("valid"));
    assertTrue(valid[6].equals("path"));
  }
  
  
  /*
   *Check ls command with no R option. Use validator to match if the expected
   *output is equal to actual.
   */
  @Test
  public void testlsCommandWithoutROption(){
    String input = "ls validPath";
    String [] valid = val.ValidateCommand(input);
    assertTrue(valid[0].equals("true"));
    assertTrue(valid[1].equals("ls"));
    assertTrue(valid[2].equals("validPath"));
  }

  /*
   *Check ls command with R option. Use validator to match if the expected
   *output is equal to actual.
   */
  @Test
  public void testlsCommandWithROption(){
    String input = "ls -r validPath";
    String [] valid = val.ValidateCommand(input);
    assertTrue(valid[0].equals("true"));
    assertTrue(valid[1].equals("ls"));
    assertTrue(valid[2].equals("-r"));
    assertTrue(valid[3].equals("validPath"));
  }
  
  
  
}
