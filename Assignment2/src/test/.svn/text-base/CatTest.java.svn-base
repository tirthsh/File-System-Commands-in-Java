package test;
import java.util.Arrays;
import java.util.List;

import commands.Cat;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * integration testing of cat command with redirection and fileSystem
 * @author jerry
 *
 */
public class CatTest {
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // integration testing of cat with fileSystem
    //setup
    FileSystem fs = FileSystem.getFileSystemFp();
    Cat cmd = new Cat();
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
    String catcmd;
    // start of commands
    // should print contents of file1
    catcmd = "new/file1";
    executecmd(catcmd, cmd);
    // should print no such file error
    catcmd = "bogus";
    executecmd(catcmd, cmd);
    //should print is a directory error\
    catcmd = "new/a1";
    executecmd(catcmd, cmd);
    //should print out contents of file1 followed by file2
    catcmd = "new/file1 new/a1/a2/file2";
    executecmd(catcmd, cmd);
    //should print out contents of file1 followed by no such file error
    //followed by is directory error
    catcmd = "new/file1 bogus new/a1";
    executecmd(catcmd, cmd);
    // should print out contents of file1 followed by file2
    fs.setCurrentWorkingDirectory(dir);
    catcmd = "../new/file1 a1/a2/./file2";
    executecmd(catcmd, cmd);
    // should print out the contents of file2 followed by file1
    fs.setCurrentWorkingDirectory(a2);
    catcmd = "/new/a1/a2/file2 /new/a1/../file1";
    executecmd(catcmd, cmd);
    // testing collaboration with redirection
    catcmd = "/new/a1/a2/file2 /new/a1/../file1 >/new/a1/file";
    executecmd(catcmd, cmd);
    for (File f: a1.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
    catcmd = "/new/a1/a2/file2 /new/a1/../file1 >>/new/a1/file";
    executecmd(catcmd, cmd);
    for (File f: a1.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
  }
  /**
   * converts string command in to something usable by cat.
   * @param cmd
   * @param exe
   */
  public static void executecmd(String cmd, Cat exe){
    List<String> cmdlist = Arrays.asList(cmd.split("\\s"));
    exe.execute(cmdlist);
  }
}
