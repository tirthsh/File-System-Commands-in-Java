package test;
import java.util.Arrays;
import java.util.List;
import commands.Grep;
import fileSystem.Directory;
import fileSystem.File;
import fileSystem.FileSystem;
/**
 * integration testing of Grep with fileSystem and redirection
 * @author jerry
 *
 */
public class GrepTest {
  public static void main(String[] args) {
    FileSystem fs = FileSystem.getFileSystemFp();
    Grep grep = new Grep();
    Directory dir = new Directory("new");
    Directory a1 = new Directory("a1");
    Directory a2 = new Directory("a2");
    Directory a3 = new Directory("a3");
    File file1 = new File("story of Baskin Robbins\n"
        + "story of\n" + "bash", "file1");
    File file2 = new File("story of Ben and Jerrys\n" + "basha \n"
        + "okuyasu nijimura", "file2");
    dir.addDir(a1);
    a1.addDir(a2);
    dir.addDir(a3);
    a1.setParent(dir);
    a2.setParent(a1);
    a3.setParent(dir);
    a2.addFile(file2);
    dir.addFile(file1);
    fs.getCurrentWorkingDirectory().addDir(dir);
    // test finding a string in a file
    String cmd1 = "story new/file1";
    executecmd(cmd1, grep);
    // test finding a string in multiple files
    cmd1 = "story new/file1 new/a1/a2/file2";
    executecmd(cmd1, grep);
    // test when filepath is invalid
    cmd1 = "story new new/file1";
    executecmd(cmd1, grep);
    cmd1 = "-R story /";
    executecmd(cmd1, grep);
    cmd1 = "-R story / >file1";
    executecmd(cmd1, grep);
    for (File f: fs.getRoot().getFileContents()){
      System.out.println("file Name: " + f.getName() + 
          "\ncontents: " + f.getContents());
    }
  }
  /**
   * converts string command in to something usable by grep.
   * @param cmd
   * @param exe
   */
  public static void executecmd(String cmd, Grep exe){
    List<String> cmdlist = Arrays.asList(cmd.split("\\s"));
    exe.execute(cmdlist);
  }
}
