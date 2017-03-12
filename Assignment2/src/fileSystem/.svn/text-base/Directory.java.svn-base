package fileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSys {
  // Directory objects of Parent and self
  private Directory parent;
  private String name;
  private Boolean root = false;

  // Using array list to hold Directory object and File objects
  private List<Directory> DirContents = new ArrayList<Directory>();
  private List<File> FileContents = new ArrayList<File>();
  private ArrayList<String> DirName = new ArrayList<String>();
  private ArrayList<String> FileName = new ArrayList<String>();

  /**
   * Constructor method, sets name as parameter taken, and selfDir to the object
   * being created
   * 
   * @param name name of the Directory object
   */
  public Directory(String name) {
    this.setName(name);
    this.DirName.add("..");
    this.DirName.add(".");
  }
  
  /**
   * Returns true iff this object is a Directory, implemented method from
   * FileSys
   */
  public boolean isDir() {
    return true;
  }

  /**
   * This method adds a Directory object to the list of contents, while setting
   * the parent of the Directory object to this directory, return true. If a
   * directory with the same name already exists, return false.
   * 
   * @param dir directory object to be added
   * @return boolean true if successfully added dir otherwise false
   */
  public boolean addDir(Directory dir) {
    if (!DirName.contains(dir.getName())) {
      DirContents.add(dir);
      DirName.add(dir.getName());
      dir.setParent(this);
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method adds a File object to the list of contents, while setting the
   * parent of the File object to this file, return true. If a file with the
   * same name already exists, return false.
   * 
   * @param file file object to be added
   * @return boolean true if successfully added file otherwise false
   */
  public boolean addFile(File file) {
    if (!FileName.contains(file.getName())) {
      FileContents.add(file);
      FileName.add(file.getName());
      file.setParent(this);
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method returns an array list of objects containing all contents in
   * this directory
   * 
   * @return returns an array list of objects
   */
  public List<FileSys> getAllContents() {
    List<FileSys> AllContents = new ArrayList<FileSys>();
    AllContents.addAll(this.getDirContents());
    AllContents.addAll(this.getFileContents());
    return AllContents;
  }

  /**
   * This method returns names of all contents in this directory
   * 
   * @return list of Strings
   */
  public List<String> getAllNames() {
    List<String> names = new ArrayList<String>();
    names.addAll(this.getDirName());
    names.addAll(this.getFileName());
    names.add("..");
    names.add(".");
    return names;
  }

  /**
   * Returns directory object with name as specified in parameter name.
   * 
   * Requirement: Must contain this directory object.
   * 
   * @param name name of directory object to be fetched
   * @return Directory with name name
   */
  public Directory getSubDirWithName(String name) {
    int i = 0;
    if (name.equals("..")) {
      return this.parent;
    } else if (name.equals(".")) {
      return this;
    } else {
      while (!DirContents.get(i).getName().equals(name)) {
        i++;
      }
      return DirContents.get(i);
    }
  }

  /**
   * Returns file object with name as specified in parameter name.
   * 
   * Requirement: Must contain this file object.
   * 
   * @param name name of directory object to be fetched
   * @return File with name name
   */
  public File getFileWithName(String name) {
    int i = 0;
    while (!FileContents.get(i).getName().equals(name)) {
      i++;
    }
    return FileContents.get(i);
  }

  /**
   * Checks to see if a directory with name name exists
   * 
   * @param name name of directory to be checked
   * @return boolean true if exists otherwise false
   */
  public boolean DirExists(String name) {
    if (DirName.contains(name)||name.equals("..") || name.equals(".")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks to see if a file with name name exists
   * 
   * @param name name of file to be checked
   * @return boolean true if exists otherwise false
   */
  public boolean FileExists(String name) {
    if (FileName.contains(name)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the current directory is root
   * 
   * @return boolean true if it is root other wise false
   */
  public boolean isRoot() {
    return root;
  }

  /**
   * Make the current directory root
   * 
   * Can only be used once, by the fileSystem class, so no modifier
   */
  void makeRoot() {
    root = true;
  }

  /**
   * This method returns a string representation this directory, printing a
   * string array list of all contents.
   */
  public String toString() {
    List<FileSys> AllContentList = this.getAllContents();
    int size = AllContentList.size();

    String b = (this.getName()) + ":\n";
    // Loop through AllContentList and add each object's name to String Array
    for (int i = 0; i < size; i++) {
      b += ((AllContentList.get(i).getName()) + "  ");
    }
    return b;
  }

  /**
   * Deletes the directory with the name name as the parameter
   * 
   * @param String name of directory to be deleted
   * @return false if directory doesn't exist, true if deleted successfully
   * 
   */
  public boolean deleteDirectory(String name) {
    if (!this.getDirName().contains(name)) {
      return false;
    } else {
      this.DirContents.remove(this.getSubDirWithName(name));
      return true;
    }
  }

  /**
   * Deletes the directory with the name name as the parameter
   * 
   * @param String name of file to be deleted
   * @return false if file doesn't exist, true if deleted successfully
   * 
   */
  public boolean deleteFile(String name) {
    if (!this.getFileName().contains(name)) {
      return false;
    } else {
      this.FileContents.remove(this.getFileWithName(name));
      return true;
    }
  }

  /**
   * This method returns the parent of this directory
   * 
   * @return returns a Directory object
   */
  public Directory getParent() {
    return parent;
  }

  /**
   * This method Sets the parent of this directory
   * 
   * @param parent Parent directory to be set for this directory
   */
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * This method returns a string that is the name of this directory
   * 
   * @return returns an String
   */
  public String getName() {
    return name;
  }

  /**
   * This method Sets the name of this directory
   * 
   * @param name Name to be set for this directory
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method returns an array list of Directory objects containing all sub
   * directories in this directory
   * 
   * @return returns an array list of Directory
   */
  public List<Directory> getDirContents() {
    return DirContents;
  }

  /**
   * This method returns an array list of File objects containing all files in
   * this directory
   * 
   * @return returns an array list of File
   */
  public List<File> getFileContents() {
    return FileContents;
  }

  /**
   * This method returns the names of sub directories in this directory
   * 
   * @return List of Strings
   */
  public ArrayList<String> getDirName() {
    return DirName;
  }

  /**
   * This method returns the names of files in this directory
   * 
   * @return List of Strings
   */
  public ArrayList<String> getFileName() {
    return FileName;
  }

  /**
   * This method returns True if this Directory is root, else false.
   * @return boolean 
   */
  public Boolean getRoot() {
    return root;
  }
}
