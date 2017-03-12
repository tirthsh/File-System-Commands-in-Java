package test;

import fileSystem.*;
import commands.*;
import exceptions.*;

public class LsRecursiveTest {
  public static void main(String[] args) throws InvalidDirectoryPath{
    /*
     * Set up fileSystem as below
     *                               root
     *            /             /          \         \           \
     *          f1             f2          f5        a5          a6
     *      /      \         /    \ 
     *     f3      a1       f4     a3
     *     /               /
     *    a2              a4
     */

    FileSystem fs = FileSystem.getFileSystemFp();

    Directory f1 = new Directory("f1");
    Directory f2 = new Directory("f2");
    Directory f3 = new Directory("f3");
    Directory f4 = new Directory("f4");
    Directory f5 = new Directory("f5");

    File a1 = new File("Some contents1", "a1");
    File a2 = new File("Some contents2", "a2");
    File a3 = new File("Some contents3", "a3");
    File a4 = new File("Some contents4", "a4");
    File a5 = new File("Some contents5", "a5");
    File a6 = new File("Some contents6", "a6");

    f1.addDir(f3);
    f1.addFile(a1);
    f3.addFile(a2);

    f2.addDir(f4);
    f2.addFile(a3);
    f4.addFile(a4);

    fs.getRoot().addDir(f1);
    fs.getRoot().addDir(f2);
    fs.getRoot().addDir(f5);
    fs.getRoot().addFile(a5);
    fs.getRoot().addFile(a6);

    LsRecursive lsr = new LsRecursive();
    String [] paths = {"/", "/f1"};
    lsr.execute(paths);
  } 
}