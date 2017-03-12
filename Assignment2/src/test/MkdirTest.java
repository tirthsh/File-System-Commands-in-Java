package test;

import java.util.List;
import java.util.ArrayList;

import commands.Mkdir;
import fileSystem.Directory;
import fileSystem.FileSystem;

/**
 * integration testing of mkdir command with fileSystem
 */
public class MkdirTest {

  public static void main(String [] args){
    FileSystem fs = FileSystem.getFileSystemFp();
    List<String> path = new ArrayList<String>();
    Mkdir addDir = new Mkdir();
    
    //testCase: adding a directory
    path.add("a1");
    addDir.execute(path);
    System.out.println(path + " exists: " + fs.getCurrentWorkingDirectory().
        DirExists("a1"));
    
    
    //testCase: trying to create a directory that already has special characters
    path.clear();
    path.add("f#Fdfaf!!#!!@");
    //shouldn't create the directory
    addDir.execute(path);
    System.out.println(path + " exists: " + fs.getCurrentWorkingDirectory().
        DirExists("f#Fdfaf!!#!!@"));

    
    //testCase: trying to create a directory that already exists
    path.clear();
    path.add("a1");
    //shouldn't create the directory 
    addDir.execute(path);
    System.out.println(path + " already exists: " + 
    fs.getCurrentWorkingDirectory().DirExists("a1"));
            
  }

}
