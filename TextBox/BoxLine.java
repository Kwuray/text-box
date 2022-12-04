class BoxLine {

  public enum LineType {
    HORIZONTAL,
    VERTICAL
  }

//declaration
//----------------------------------
  protected Coordinates startCoord;
//----------------------------------


//set & get methods
//----------------------------------

  protected void setCoord(int row, int column) {
    this.startCoord = new Coordinates(row, column);
  }
//----------------------------------


// Constructor
//----------------------------------
  protected BoxLine(int row, int column) {
    setCoord(row, column);
  }
//----------------------------------

}
