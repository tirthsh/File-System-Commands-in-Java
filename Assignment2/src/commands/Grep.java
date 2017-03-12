package commands;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InvalidDirectoryPath;
import exceptions.UsageError;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * 
 * @author jerry
 *
 */
public class Grep {
 public Grep(){
   
 }
 /**
  * given a list of string arguments, process grep.
  * if first option is -r/-R,  recursively traverse the directory
    and, for all lines in all files that contain REGEX, print the path to the file
    (including the filename), then a colon, then the line that contained REGEX. 
  * else print any lines containing REGEX in PATH
  * @param cmd
  */
 public void execute(List<String>cmd){
   try{
    if (cmd.size() < 2){
      throw new UsageError("Grep USAGE: [-R] REGEX PATH...");
    }
   }
   catch (UsageError e){
     System.out.println(e.getMessage());
   }
   // check first argument, which may match the option -r.
   if (cmd.get(0).toLowerCase().matches("-r")){
     handleGrepOptR(cmd);
   }
   // else first argument is regex
   else{
     handleGrep(cmd);
   }
 }
 /**
  * handles grep for a file without option -R
  * @param regex
  * @param cmd
  */
 private void handleGrep(List<String>cmd){
   String retString = "";
   Boolean toRedirect = false;
   Boolean append = false;
   Boolean overwrite = false;
   String redirPath = "";
   Pattern regex = Pattern.compile(cmd.get(0));
   for (int i=1; i<cmd.size(); i++){
    if (cmd.get(i).matches(">.*")){
      toRedirect = true;
      append = false;
      overwrite = true;
      redirPath = cmd.get(i);
    }
    else if (cmd.get(i).matches(">>.*")){
      toRedirect = true;
      append = true;
      overwrite = false;
      redirPath = cmd.get(i);
    }
    else{
      String fileContents = "";
      try{  
        fileContents = findFile(cmd.get(i));
      }
      catch (InvalidDirectoryPath e){
        System.out.println(e.getMessage());
      }
      // split fileContents by newline, check if each line matches the regex 
      String[] line = fileContents.split("\n");
      for (String each: line){
        if (contains(regex, each)){
          retString += each + "\n";
        }
      }
    }
   }
   if (toRedirect){
     Redirection.handleRedirection(overwrite, append, redirPath, retString);
   }
   else{
     System.out.println(retString);
   }
 }
 /**
  * handles grep for option -R, which extends grep to recursively
  * search through directories.
  * @param regex
  * @param cmd
  */
 private void handleGrepOptR(List<String>cmd){
   String retString = "";
   Boolean toRedirect = false;
   Boolean append = false;
   Boolean overwrite = false;
   String redirPath = "";
   FileSystem fs = FileSystem.getFileSystemFp();
   Directory curdir = fs.getCurrentWorkingDirectory();
   Pattern regex = Pattern.compile(cmd.get(1));
   for (int i=2; i<cmd.size(); i++){
     if (cmd.get(i).matches(">.*")){
       toRedirect = true;
       append = false;
       overwrite = true;
       redirPath = cmd.get(i).substring(1);
     }
     else if (cmd.get(i).matches(">>.*")){
       toRedirect = true;
       append = true;
       overwrite = false;
       redirPath = cmd.get(i).substring(2);
     }
     //check if filepath is a valid directory path
     else if (fs.isValidPath(cmd.get(i))){
       curdir = fs.goToDirectoryInPath(cmd.get(i));
       retString += recursiveGrep(regex, cmd.get(i), curdir);
     }
     // otherwise check if it's a valid file
     else{
       String fileContents = "";
       try{  
         fileContents = findFile(cmd.get(i));
       }
       catch (InvalidDirectoryPath e){
         System.out.println(e.getMessage());
       }
       // split fileContents by newline, check if each line matches the regex 
       String[] line = fileContents.split("\n");
       for (String each: line){
         if (contains(regex, each)){
           retString += each + "\n";
         }
       }
     }
   }
   if (toRedirect){
     Redirection.handleRedirection(overwrite, append, redirPath, retString);
   }
   else{
     System.out.println(retString);
   }
 }
 /**
  * given a file path, returns the file's contents if the file exists.
  * @param path
  * @return
  * @throws InvalidDirectoryPath
  */
 private String findFile(String path) throws InvalidDirectoryPath{
   FileSystem fs = FileSystem.getFileSystemFp();
   Directory curdir = fs.getCurrentWorkingDirectory();
   String[] parts = path.split("/");
   // use isvalidpath to check if everything leading up to the filepath in question is valid
   if (parts.length > 1){
     String directorypath = join("/", 
         Arrays.copyOfRange(parts, 0, parts.length - 1));
       if (fs.isValidPath(directorypath)){
         curdir = fs.goToDirectoryInPath(directorypath);
       }
       else {
         throw new InvalidDirectoryPath(path + 
             " is not a valid file path");
       }
   }
   // check if the very last step in the path leads to a file
   // return file's contents if true
   if (curdir.FileExists(parts[parts.length -1])){
    return curdir.getFileWithName(parts[parts.length -1]).getContents();
   }
   // throw error if false
   else{
     throw new InvalidDirectoryPath(path + 
         " is not a valid file path");
   }
 }
 /**
  * give a directory and a regex, recursively traverse that directory, finding every instance
  * of that regex contained in the file in that directory
  * @param regex
  * @param dir
  * @return
  */
 private String recursiveGrep(Pattern regex, String path, Directory dir){
   String retString = "";
   if (dir.getDirContents().size() > 0){
     for (Directory d : dir.getDirContents()){
       path += "/" + d.getName();
       retString += recursiveGrep(regex, path, d);
     }
   }; 
   if (dir.getFileContents().size() > 0){
     for (File f : dir.getFileContents()){
       String contents = f.getContents();
       String[] line = contents.split("\n");
       for (String each: line){
         if (contains(regex, each)){
           String filepath = path + "/" + f.getName();
           retString += filepath + ": " + each + "\n";
         }
       }
     }
   }
   return retString;
 }
 /**
  * returns true if a subsequence of the line contains the regex
  * @param regex
  * @param fileContents
  * @return
  */
 private boolean contains(Pattern regex, String fileContents){
   Matcher m = regex.matcher(fileContents);
   return m.find();
 }
 private static String join(String delim, String[] parts){
   String ret = "";
   for (int i=0; i < parts.length-1; i++){
      ret += parts[i] + "/";
   }
   ret += parts[parts.length -1];
   return ret;
 }
 /**
  * manual for grep
  */
 public void manual(){
   System.out.println("looks for a regex in given filepath(s),"
       + " output the lines that contains that regex.\n"+
       "if -r option is selected and a directory path is given, "
       + "then recursively traverse through the directory and output any"
       +"any lines in any files that contain the regex,\n"
       +"as filepath: line\n" +
       "Usage: Grep [-R] REGEX PATH...");
 }
}
