package test;

import commands.Cd;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * integration testing of cd command with fileSystem
 * @author jerry
 *
 */
public class CdTest {
//integration testing for cd with filesystem
  public static void main(String[] args) {
    FileSystem fs = FileSystem.getFileSystemFp();
    Cd cmd = new Cd();
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
    cmd.execute("new/a1");
    //should print "a1"
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    //should print "a2"
    cmd.execute("/new/a1/a2");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    //should print "a3"
    cmd.execute("../.././a3");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    //should print "a1"
    cmd.execute("/new/a1");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    //should print "new"
    cmd.execute("..");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    //should return not a directory error
    cmd.execute("file1");
    //should return no such directory error
    cmd.execute("bogus/bogus");
    cmd.execute("/");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    fs.setCurrentWorkingDirectory(a2);
    cmd.execute(".");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
    fs.setCurrentWorkingDirectory(a2);
    cmd.execute("..");
    System.out.println(fs.getCurrentWorkingDirectory().getName());
  }

}
