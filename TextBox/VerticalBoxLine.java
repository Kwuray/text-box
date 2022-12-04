class VerticalBoxLine extends BoxLine {

  /*Draw a vertical line*/
  protected void draw(TextBox box, char c) {
    for (int i = 0; i < box.getHeight(); i++) {
      //Move cursor to correct line row and column
      Curse.cursor(this.startCoord.getRow() + i, this.startCoord.getColumn());
      System.out.print(c);
    }
  }

    /*Constructor*/
    protected VerticalBoxLine(int row, int column) {
      super(row, column);
    }
}
