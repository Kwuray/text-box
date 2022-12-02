public class Main {
  public static void main(String[] args) {
    System.out.print("\033c");
    TextBox mainBox = new TextBox(new Coordinates(1, 1), 20, 40);
    mainBox.draw(TextBox.oneSide.TOP, '-');
    mainBox.draw(TextBox.oneSide.BOTTOM, '-');
    mainBox.draw(TextBox.oneSide.LEFT, '|');
    mainBox.draw(TextBox.oneSide.RIGHT, '|');
    mainBox.cursor(mainBox.getCoord()[1].getRow(), mainBox.getCoord()[1].getColumn());
    while (true) {

    }
  }
}
