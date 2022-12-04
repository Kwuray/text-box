//This class provides coordinates for bi-dimensional array
class Coordinates {
  private int row;
  private int column;

  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }

  private void setRow(int row) {
    this.row = row;
  }

  private void setColumn(int column) {
    this.column = column;
  }

  public Coordinates(int row, int column) {
    setRow(row);
    setColumn(column);
  }
}
