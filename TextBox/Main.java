public class Main {
  public static void main(String[] args) {

    Curse.clearTerminal();
    TextBox mainBox = new TextBox(1, 1, 40, 21);
    mainBox.draw('#');
    mainBox.setContent("\nIl était une fois\n   La princesse Sarah la plus belle de toutesssssssssssssssssssssssssssssssssssssssssssssssss\n\n\ne\ne\ne\ne\na\na\na\na\na\na\nd\nd\nd\nd");
    /*
    String[] s = Curse.split("Il était une fois...", '\n');
    for (int i = 0; i < s.length; i++) {
      System.out.print(s[i]);
    }
    */
    while (true) {

    }

    /*
    String s = "\nIl était une fois\n   La princesse Sarah la plus belle de toutes\n\n\n";
    String[] x = s.split("\n");
    System.out.print(x.length);
    for (int i = 0; i < x.length; i++) {
      System.out.print(x[i]);
    }
    */
  }
}
