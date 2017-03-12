package test;
import fileSystem.*;
import commands.*;

/**
 * integration testing of redirection and fileSystem
 * @author jerry
 *
 */
public class RedirectionTest {
  public static void main(String[] args) {
    FileSystem fs = FileSystem.getFileSystemFp();
    Directory dir = new Directory("new");
    Directory a1 = new Directory("a1");
    Directory a2 = new Directory("a2");
    Directory a3 = new Directory("a3");
    File file1 = new File("story of Baskin Robbins", "file1");
    File file2 = new File("story of Ben and Jerrys", "file2");
    dir.addDir(a1);
    a1.addDir(a2);
    dir.addDir(a3);
    a1.setParent(dir);
    a2.setParent(a1);
    a3.setParent(dir);
    a2.addFile(file2);
    dir.addFile(file1);
    fs.getCurrentWorkingDirectory().addDir(dir);
    // case 1: overwrite file1 with "cat"
    Redirection.handleRedirection(true, false, "new/file1", "cat");
    System.out.println(file1.getContents());
    // case 2: append file2 with "cat"
    Redirection.handleRedirection(false, true, "new/a1/a2/file2", "cat");
    System.out.println(file2.getContents());
    // case 3: testing overwrite with absolute paths
    // should print robot
    fs.setCurrentWorkingDirectory(a3);
    Redirection.handleRedirection(true, false, "/new/file1", "robot");
    System.out.println(file1.getContents());
    // case 4 testing append with absolute paths
    // should print 
    // story of Ben and Jerrys\ncat\nrobot
    fs.setCurrentWorkingDirectory(a2);
    Redirection.handleRedirection(false, true, "/new/a1/a2/file2", "robot");
    System.out.println(file2.getContents());
    // case 5: error where both overwrite & append set to true
    Redirection.handleRedirection(true, true, "/new/a1/a2/file2", "robot");
    // case 6: file to overwrite DNE and needs to be created
    Redirection.handleRedirection(true, false, "/new/file", "stone wall");
    for (File f: dir.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
    // case 7: file to append DNE and needs to be created
    fs.setCurrentWorkingDirectory(fs.getRoot());
    Redirection.handleRedirection(false, true, "new/a1/file", "stone wall");
    for (File f: a1.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
    // case 8: directory path to file invalid, print error
    Redirection.handleRedirection(false, true, "new/ap/file", "stone wall");
    Redirection.handleRedirection(true, false, "new/ap/file", "stone wall");
    // case 9 overwrite with special characters
    Redirection.handleRedirection(true, false, "/new/a1/../../new/file1",
        "stone ocean");
    for (File f: a1.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
    // case 10 append with special characters
    Redirection.handleRedirection(false, true, "/new/./../new/a1/a2/file2",
        "stone ocean");
    for (File f: a2.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
  }
}
