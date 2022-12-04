//This class provides coordinates for bi-dimensional array
class Coordinates {
  private int row;
  private int column;

  protected int getRow() {
    return this.row;
  }

  protected int getColumn() {
    return this.column;
  }

  protected void setRow(int row) {
    this.row = row;
  }

  protected void setColumn(int column) {
    this.column = column;
  }

  protected Coordinates(int row, int column) {
    setRow(row);
    setColumn(column);
  }
}
