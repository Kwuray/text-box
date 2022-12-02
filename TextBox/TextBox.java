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
  private Coordinates[] coord;
  private int width;
  private int height;
//----------------------------------


//set & get methods
//----------------------------------
  public Coordinates[] getCoord() {
    return this.coord;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.width;
  }

  //Set the 4 corners coordinates
  private void setCoord(Coordinates topLeftCoord) {
    this.coord = new Coordinates[4];
    this.coord[0] = topLeftCoord;
    this.coord[1] = new Coordinates(topLeftCoord.getRow(), topLeftCoord.getColumn() + this.getWidth() - 1);
    this.coord[2] = new Coordinates(topLeftCoord.getRow() + this.getHeight() - 1, topLeftCoord.getColumn());
    this.coord[3] = new Coordinates(topLeftCoord.getRow() + this.getHeight() - 1, topLeftCoord.getColumn() + this.getWidth());
  }

  private void setWidth(int width) {
    this.width = width;
  }

  private void setHeight(int height) {
    this.height = height;
  }
//----------------------------------


// Constructor
//----------------------------------
  public TextBox(Coordinates topLeftCoord, int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
    this.setCoord(topLeftCoord);
  }
//----------------------------------


// Methods
//----------------------------------
  /*move to position row;column in terminal*/
  public void cursor(int row, int column) {
    System.out.print("\033[" + row + ";" + column + "H");
  }

  /*Draw an horizontal line*/
  private void drawHorizontal(Coordinates start, char c) {
    String drawing = "";

    //Move cursor to correct line row and column
    cursor(start.getRow(), start.getColumn());

    //If width is even, no space between char
    if (this.getWidth() % 2 == 0) {
      for (int i = 0; i < this.getWidth(); i++) {
        drawing += c;
      }
    //If odd, add beautiful space betwen
    } else {
      drawing += c;
      for (int i = 0; i < (this.getWidth() / 2); i++) {
        drawing += " " + c;
      }
    }
    System.out.print(drawing);
  }

  /*Draw a vertical line*/
  private void drawVertical(Coordinates start, char c) {
    for (int i = 0; i < this.getHeight(); i++) {
      //Move cursor to correct line row and column
      cursor(start.getRow() + i, start.getColumn());
      System.out.print(c);
    }
  }

  /*Draw one of the fourth lines, with a certain character*/
  public void draw(oneSide side, char c) {
    switch (side) {
      case TOP:
        this.drawHorizontal(this.getCoord()[0], c);
        break;

      case BOTTOM:
        this.drawHorizontal(this.getCoord()[2], c);
        break;

      case LEFT:
        this.drawVertical(this.getCoord()[0], c);
        break;

      case RIGHT:
        this.drawVertical(this.getCoord()[1], c);
        break;

      default:
        break;
    }
  }
//----------------------------------
}
