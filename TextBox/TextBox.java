//This is the main class of this package.
//This represent a textbox, with 4 coordinates for each corner
//It can be drawn or not, entirely or only some sides
public class TextBox {
  public enum oneSide {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT
  }
//declaration
//----------------------------------
  private int width;
  private int height;
  private HorizontalBoxLine topLine;
  private HorizontalBoxLine bottomLine;
  private VerticalBoxLine leftLine;
  private VerticalBoxLine rightLine;
//----------------------------------


//set & get methods
//----------------------------------
  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.width;
  }

  private void setWidth(int width) {
    this.width = width;
  }

  private void setHeight(int height) {
    this.height = height;
  }

  private void setTopLine(int row, int column) {
    this.topLine = new HorizontalBoxLine(row, column);
  }

  private void setBottomLine(int row, int column) {
    this.bottomLine = new HorizontalBoxLine(row, column);
  }

  private void setLeftLine(int row, int column) {
    this.leftLine = new VerticalBoxLine(row, column);
  }

  private void setRightLine(int row, int column) {
    this.rightLine = new VerticalBoxLine(row, column);
  }



  //Set the 4 corners coordinates
  private void setLines(int startRow, int startColumn) {
    this.setTopLine(startRow, startColumn);
    this.setBottomLine(startRow + this.getHeight() - 1, startColumn);
    this.setLeftLine(startRow, startColumn);
    this.setRightLine(startRow, startColumn + this.getWidth() - 1);
  }

//----------------------------------


// Constructor
//----------------------------------
  public TextBox(int startRow, int startColumn, int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
    this.setLines(startRow, startColumn);
  }
//----------------------------------


// Methods
//----------------------------------
  /*Draw each lines*/
  public void draw(char c) {
    this.topLine.draw(this, c);
    this.bottomLine.draw(this, c);
    this.leftLine.draw(this, c);
    this.rightLine.draw(this, c);
  }

  /*Draw one of the fourth lines, with a certain character*/
  public void draw(oneSide side, char c) {
    switch (side) {
      case TOP:
        this.topLine.draw(this, c);
        break;

      case BOTTOM:
        this.bottomLine.draw(this, c);
        break;

      case LEFT:
        this.leftLine.draw(this, c);
        break;

      case RIGHT:
        this.rightLine.draw(this, c);
        break;

      default:
        break;
    }
  }
//----------------------------------
}
