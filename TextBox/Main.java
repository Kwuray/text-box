public class Main {
  public static void main(String[] args) {
    
    Curse.clearTerminal();
    TextBox mainBox = new TextBox(1, 1, 21, 40);
    mainBox.draw('#');
    mainBox.setContent("\nIl était une fois\n   La princesse Sarah la plus belle de toutes\n\n\n");
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
