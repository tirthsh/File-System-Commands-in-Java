package test;

import commands.Pwd;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * integration testing of pwd command with redirection and fileSystem
 * @author jerry
 *
 */
public class PwdTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // integration testing of pwd with fileSystem
    FileSystem fs = FileSystem.getFileSystemFp();
    Pwd cmd;
    Directory dir = new Directory("new");
    Directory a1 = new Directory("a1");
    Directory a2 = new Directory("a2");
    Directory a3 = new Directory("a3");
    File file1 = new File("story of Baskin Robbins", "file1");
    File file2 = new File("story of Ben and Jerrys", "file2");
    fs.getRoot().addDir(dir);
    dir.addDir(a1);
    a1.addDir(a2);
    dir.addDir(a3);
    a2.addFile(file2);
    dir.addFile(file1);

    cmd = new Pwd();
    // assuming Nick fixed calculateCurrentPath
    // should return /
    cmd.execute();
    fs.setCurrentWorkingDirectory(a3);
    // should return /new/a3 
    cmd.execute();
    // should overwrite /new/a3 to file1
    cmd.execute(">/new/file1");
    System.out.println(file1.getContents());
    // should append /new/a3 to file2
    cmd.execute(">>/new/a1/a2/file2");
    System.out.println(file2.getContents());
  }

}
