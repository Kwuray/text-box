//This is the main class of this package.
//This represent a textbox, with 4 coordinates for each corner
//It can be drawn or not, entirely or only some sides
public class TextBox {
  
  private Coordinates[] coord;
  private int width;
  private int height;

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
  private setCoord(Coordinates topLeftCoord) {
    this.coord = new coordDebut[4];
    this.coord[0] = topLeftCoord;
    this.coord[1] = new Coordinates(topLeftCoord.getRow(), topLeftCoord.getColumn() + this.getWidth())
    this.coord[2] = new Coordinates(topLeftCoord.getRow() + this.height - 1, topLeftCoord.getColumn());
    this.coord[3] = new Coordinates(topLeftCoord.getRow() + this.height - 1, topLeftCoord.getColumn() + this.getWidth());
  }

  private setWidth(int width) {
    this.width = width;
  }

  private setHeight(int height) {
    this.height = height;
  }

  public TextBox(Coordinates topLeftCoord, int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
    this.setCoord(topLeftCoord);
  }
}
