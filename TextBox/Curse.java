public class Curse {
  /*move to position row;column in terminal*/
  static void cursor(int row, int column) {
    System.out.print("\033[" + row + ";" + column + "H");
  }

  /*Clear everything in terminal*/
  static void clearTerminal() {
    System.out.print("\033c");
  }

  static int countOccurencesOf(String text, char c) {
    int result = 0;
    int textLength = text.length();
    for (int i = 0; i < textLength; i++) {
      char currentChar = text.charAt(i);
      if (currentChar == c) {
        result++;
      }
    }
    return result;
  }

  static String[] split(String text, char c) {
    int totalOccurences = countOccurencesOf(text, c) + 1;
    String[] result = new String[totalOccurences];
    int textLength = text.length();
    int totalFound = 0;
    int j = 0;
    String ligne = "";
    while (totalFound < totalOccurences && j < textLength) {
      char currentChar = text.charAt(j);
      ligne = ligne + currentChar;
      if (currentChar == c || j == textLength - 1) {
        result[totalFound] = ligne;
        totalFound++;
        ligne = "";
      }
      j++;
    }
    //result[totalFound] = ligne;
    return result;
  }

  static String removeNoPrintableAtTheEnd(String str) {
    //If the last character is not printable, we remove it
    char lastChar = str.charAt(str.length() - 1);
    while (lastChar < ' ' && str.length() > 0) {
      str = str.substring(0, str.length() - 1);
      if (str.length() > 0) {
        lastChar = str.charAt(str.length() - 1);
      }
    }
    return str;
  }
}
