package exceptions;

public class InvalidDirectoryPath extends Exception{
  public InvalidDirectoryPath(String path){
    super(path);
  }
}
