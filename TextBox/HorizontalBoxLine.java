class HorizontalBoxLine extends BoxLine {

  /*Draw an horizontal line*/
  public void draw(TextBox box, char c) {
    String drawing = "";

    //Move cursor to correct line row and column
    Curse.cursor(this.startCoord.getRow(), this.startCoord.getColumn());

    //If width is even, no space between char
    if (box.getWidth() % 2 == 0) {
      for (int i = 0; i < box.getWidth(); i++) {
        drawing += c;
      }
    //If odd, add beautiful space betwen
    } else {
      drawing += c;
      for (int i = 0; i < (box.getWidth() / 2); i++) {
        drawing += " " + c;
      }
    }
    System.out.print(drawing);
  }

  /*Constructor*/
  public HorizontalBoxLine(int row, int column) {
    super(row, column);
  }
}
