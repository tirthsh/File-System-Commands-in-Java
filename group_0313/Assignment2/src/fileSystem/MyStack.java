package fileSystem;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A Stack class to use for pushd and popd commands. Implements the stack using
 * an ArrayList and contains the standard stack methods such as push, pop, 
 * and is_empty. 
 */
public class MyStack {

  // Create a global ArrayList
  ArrayList<Directory> StkList = new ArrayList<Directory>();
  
  // Constructor instantiates nothing new 
  public MyStack() {}
  
  /* method to push a directory onto the stack
   */
  public void push(Directory directory){
    StkList.add(directory);
  }
  
  /* method to pop a directory off the stack
   * REQ: Stack cant be empty
   */
  public Directory pop(){   
    if (StkList.isEmpty())
    {
      throw new EmptyStackException();
    }
    return StkList.remove(StkList.size()-1);  

  }
}
