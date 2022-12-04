public class Curse {
  /*move to position row;column in terminal*/
  static void cursor(int row, int column) {
    System.out.print("\033[" + row + ";" + column + "H");
  }

  /*Clear everything in terminal*/
  void clearTerminal() {
    print("\033c");
  }
}
