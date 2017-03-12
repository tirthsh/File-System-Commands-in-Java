package commands;
import java.net.MalformedURLException;
//import java.util.List;
import java.io.*;
import java.net.*;

import fileSystem.Directory;
import fileSystem.FileSystem;
import fileSystem.File;


public class Curl {
  
  private FileSystem fs;
  
  /*
   * Default constructor for curl command.
   */
  public Curl(){
    this.fs = FileSystem.getFileSystemFp();
  }
  
  /*
   * A void method which uses try catch method to check if its possible to 
   * open the URL and connect to the server. If it can't then print an 
   * exception. Create a new file of type File where the name of the file is 
   * retrieved from the helper function get_file_name(). Add the file in the 
   * current working directory. If the file already exists, then set its 
   * new contents to nothing (ie. empty the file). Read and append all contents
   * from the URL to the file created in the working directory.
   */
  public void execute(String link){ //note: path cannot have more than 1 arg
      try 
      {
        URL myURL = new URL(link);
        URLConnection myURLConnection = myURL.openConnection();
        myURLConnection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(
            myURLConnection.getInputStream()));
        //get name of the file using helper function get_file_name()
        String fileName = getFileName(link);
        //create new file of type File with no contents (empty file)
        File newFile = new File("",fileName);
        //will return false if file already exists
        Directory curr_dir;
        curr_dir = fs.getCurrentWorkingDirectory();
        if(!(curr_dir.addFile(newFile))) //then over-write the file
          newFile.setContents(""); //in other words, set contents to nothing
        //user helper function to add contents to the file
        addContentsToFile(newFile,in);
      }
      catch (MalformedURLException e){
        System.out.println("Failed to open URL"); }
      catch (IOException e){
        System.out.println("Failed connection"); } 
  }   
  
 
 /*
  * Method which returns name of the file of type String.  Takes in the URL 
  * as its parameter, splits the URL by a delimiter '/' and stores it in a list
  * of Strings. Returns the last string in the list - which will be name of
  *  the name file.  
  */
  private String getFileName(String link){
    String [] split = link.split("/");
    int length = split.length;
    String fileName = split[length-1];
    return fileName;
  }

  /*
   * A void method which adds contents to the file name specified. Takes in the
   * file name of type File and a BufferedReader which helps to read all lines
   * the file. Append each line read from the URL to the new file in the 
   * working directory.  
   */
   
  private void addContentsToFile(File fileName, BufferedReader in)
      throws IOException{
    String inputLine;
    while ((inputLine = in.readLine())!= null)
      fileName.append(inputLine);
  }
   
  /*
   * A method to return the manual for curl. Will be called by Man command.
   */
  
  public void manual()
  {
    System.out.println("transfers file and its contents from server to current "
        + "working directory on your local server. \n usage: curl [URL]\n");
  }
  

}
