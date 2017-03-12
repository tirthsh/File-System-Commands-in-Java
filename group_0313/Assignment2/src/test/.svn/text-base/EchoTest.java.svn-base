package test;

import java.util.ArrayList;
import java.util.List;

import commands.Echo;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * integration testing of Echo command with redirection and fileSystem
 * @author jerry
 *
 */
public class EchoTest {
  // integration testing for echo with filesystem
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    FileSystem fs = FileSystem.getFileSystemFp();
    Echo cmd = new Echo();
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
    List<String> cmdlist = new ArrayList<String>();
    cmdlist.add("\"nothing special\"");
    // should print out "nothing special"
    cmd.execute(cmdlist);
    // should overwrite what's in file1 to "nothing special"
    cmdlist.add(">new/file1");
    cmd.execute(cmdlist);
    System.out.println(file1.getContents());
    // should append nothing special to file2
    cmdlist.remove(1);
    cmdlist.add(">>new/a1/a2/file2");
    cmd.execute(cmdlist);
    System.out.println(file2.getContents());
    // should create new file news & append "nothing special" to it
    cmdlist.remove(1);
    cmdlist.add(">news");
    cmd.execute(cmdlist);
    for (File f: fs.getRoot().getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
    // should create new file news & append "nothing special" to it
    cmdlist.remove(1);
    cmdlist.add(">>new/a1/news");
    cmd.execute(cmdlist);
    for (File f: a1.getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
    // should print nothing special newtech
    cmdlist.remove(1);
    cmdlist.add("newtech");
    cmd.execute(cmdlist);
    cmdlist.remove(1);
    cmdlist.add(">");
    cmd.execute(cmdlist);
  }
}
