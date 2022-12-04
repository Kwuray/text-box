class VerticalBoxLine extends BoxLine {

  /*Draw a vertical line*/
  public void draw(TextBox box, char c) {
    for (int i = 0; i < box.getHeight(); i++) {
      //Move cursor to correct line row and column
      Curse.cursor(this.startCoord.getRow() + i, this.startCoord.getColumn());
      System.out.print(c);
    }
  }

    /*Constructor*/
    public VerticalBoxLine(int row, int column) {
      super(row, column);
    }
}
