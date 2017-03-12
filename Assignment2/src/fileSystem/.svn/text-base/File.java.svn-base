package fileSystem;


public class File implements FileSys {
  private String contents; // Contents inside the file
  private String name; // Name of the file
  
  private Directory parent;
  
  /**
   * This method constructs a File object and populates it with a String
   *
   * @param contents String to be created into the file object
   * @param name name of the file
   */
  public File(String contents, String name) {
    this.setContents(contents);
    this.setName(name);
  }

  /**
   * This method replaces the contents of this file object with a new String s
   *
   * @param s String to replace current contents
   */
  public void replace(String s) {
    this.setContents(s);
  }

  /**
   * This method appends a new String s to the end of contents in this file
   * object
   *
   * @param s String to append to current contents
   */
  public void append(String s) {
    this.setContents(this.getContents() + s);
  }

  /**
   * This method returns the contents in this file
   * 
   * @return returns a string
   */
  public String getContents() {
    return contents;
  }

  /**
   * This method Sets the contents of this file
   * 
   * @param contents String to be set as contents
   */
  public void setContents(String contents) {
    this.contents = contents + "\n";
  }
  
  /**
   * This method returns a string that is the name of this file
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
   * This method returns the parent directory of this file
   * 
   * @return Directory parent directory of this file
   */
  public Directory getParent() {
    return parent;
  }

  /**
   * This method sets the parent directory of this file
   * 
   * @param parent Parent directory to be set
   */
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * Over rides toString, prints contents of file
   */
  public String toString(){
    return this.getContents();
  }
  
  /**
   * Interface method, used to distinguish between a file and a directory
   * 
   * @return boolean True if is a directory, otherwise false.
   */
  public boolean isDir(){
    return false;

  }
}
