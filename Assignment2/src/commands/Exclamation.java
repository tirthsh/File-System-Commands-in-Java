package commands;

public class Exclamation {

  public Exclamation () {}
  
  public void execute(int numberInput) {
    
    int loopIndex = 0;
    
    // loop through the history array and print every element
    while (loopIndex < history_array.size())
      {
        System.out.println((loopIndex+1) + ". " + history_array.get(loopIndex));
        loopIndex++;
        numberOfCommands++;
      }
    }
  }
}
