package commands;
import java.util.List;

import exceptions.InvalidDirectoryPath;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * given a directory path, navigates through the fileSystem to that path
 * @author jerry
 *
 */
public class Cd {
   private FileSystem fs;
   public Cd(){
     this.fs = FileSystem.getFileSystemFp();
   }
   /**
    * given a directory path, if the directory path is valid,
    * change current working directory to that directory
    * @param path
    */
   public void execute(String path){
     String[] pathStep = null;
     boolean hasMatch = false;
     Directory dir = this.fs.getCurrentWorkingDirectory();
     
     try{
       // try to get step by step rep of directory path
       pathStep = getPathStep(path);
     }
     catch (NullPointerException e){
       // catch cases where user inputs nothing "null"
       System.out.println("Cd lacks Dir input\ncd: usage: DIR");
       return;
     }
     // special case "/", go to root
     if (path.equals("/")){
       fs.setCurrentWorkingDirectory(fs.getRoot());
       return;
     }
     dir = getDirectoryBeforeLastStep(pathStep, dir);
     // at the last pathstep, loop through the current working directory's
     // files/directories
     try{
       lastStepCheckDirectoryPath(pathStep[pathStep.length-1],
           dir, path);
     }
     catch (InvalidDirectoryPath e){
       System.out.println(e.getMessage());
     }
   }
   /**
    * given the last step of the path string, check if that step
    * leads to a file or Directory. return true if yes. 
    * print error message if it leads to a file change current working directory
    *  if it leads to directory.
    *  
    *  assume special characters .. and . are taken care of.
    * @param lastStep
    * @param dir
    */
   private void lastStepCheckDirectoryPath(String lastStep, Directory dir,
       String originalPath) throws InvalidDirectoryPath{
     boolean hasmatch = false;
     List<Directory> dirList = dir.getDirContents();
     List<File> fileList = dir.getFileContents();
     if (lastStep.equals("..")){
       hasmatch = true;
       if (!dir.isRoot()){
         dir = dir.getParent();
         this.fs.setCurrentWorkingDirectory(dir);
       }
     }
     else if (lastStep.equals(".")){
       hasmatch = true;
     }
     for (int f=0; f<fileList.size();f++){
       // print error message if there's a file
       if (lastStep.equals(fileList.get(f).getName())){
         throw new InvalidDirectoryPath
         ("cd: " + originalPath + ": is not a directory\n");
       }
     }
     for (int ld=0; ld<dirList.size(); ld++){
       if (lastStep.equals(dirList.get(ld).getName())){
         hasmatch = true;
         dir = dirList.get(ld);
         this.fs.setCurrentWorkingDirectory(dir);
       }
     }
     if (!hasmatch){
       throw new InvalidDirectoryPath("cd: " + originalPath +
           ": no such file or directory\n");
     }
   }
   /**
    * Helper function for cd. Also used in echo. Functions takes in an array
    * representing the steps of a filepath and returns the furthest you can go along that path
    * @param pathstep
    * @param dir
    * @return Directory
    */
   public Directory getDirectoryBeforeLastStep(String[] pathstep, 
       Directory dir){
     if (pathstep.length > 1){
       // check if path is absolute. if true, move to root
       if (pathstep[0].equals("")){
         dir = this.fs.getRoot();
       }
       for (int i=0; i<pathstep.length-1; i++){
         // check special characters .. and . first
         if (pathstep[i].equals("..")){
           if (!dir.isRoot()){
             dir = dir.getParent();
           }
         }
         else if (pathstep[i].equals(".")){
           
         }
         // if there's a subdirectory match, go to that subdirectory
         else {
           List<Directory> dirList = dir.getDirContents();
           for (int d=0; d<dirList.size(); d++){
             if (pathstep[i].equals(dirList.get(d).getName())){
               dir = dirList.get(d);
             }
           }
         }
       }
     }
     return dir;
   }
   private static String[] getPathStep(String path){
     String[] pathStep = path.split("/");
     return pathStep;
   }
   /**
    * manual for cd
    */
   public void manual(){
     System.out.println("Changes the current working directory to"
         + "that specified in the given path\n" +
         "usage: cd DIR\n");
   }
}
