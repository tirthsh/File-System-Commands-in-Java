package exceptions;

public class UsageError extends Exception{
  public UsageError(){
  }
  public UsageError(String message){
    super(message);
  }
}
