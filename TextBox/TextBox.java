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
  private int width;
  private int height;
  private Coordinates startCoord;
  private HorizontalBoxLine topLine;
  private HorizontalBoxLine bottomLine;
  private VerticalBoxLine leftLine;
  private VerticalBoxLine rightLine;
  private String content;
//----------------------------------


//set & get methods
//----------------------------------
  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.width;
  }

  public String getContent() {
    return this.content;
  }

  private void setWidth(int width) {
    this.width = width;
  }

  private void setHeight(int height) {
    this.height = height;
  }

  private void setStartCoord(int row, int column) {
    this.startCoord = new Coordinates(row, column);
  }

  private void setTopLine(int row, int column) {
    this.topLine = new HorizontalBoxLine(row, column);
  }

  private void setBottomLine(int row, int column) {
    this.bottomLine = new HorizontalBoxLine(row, column);
  }

  private void setLeftLine(int row, int column) {
    this.leftLine = new VerticalBoxLine(row, column);
  }

  private void setRightLine(int row, int column) {
    this.rightLine = new VerticalBoxLine(row, column);
  }

  public void setContent(String txt) {
    this.content = txt;
    this.printContent();
  }

  //Set the 4 corners coordinates
  private void setLines(int startRow, int startColumn) {
    this.setTopLine(startRow, startColumn);
    this.setBottomLine(startRow + this.getHeight() - 1, startColumn);
    this.setLeftLine(startRow, startColumn);
    this.setRightLine(startRow, startColumn + this.getWidth() - 1);
  }

  /*Print content inside the TextBox*/
  protected void printContent() {
    //Divide text in seperate line
    String[] linesText = this.content.split("\n");

    //Theses variables are used when we need to print word by word
    //We know how many line was used
    int lineUsed = 0;
    //Look into each line until we cannot print anymore
    for (int i = 0; i < linesText.length && i <= this.height - 2; i++) {
      //Calculate new position
      int rowPosition = this.startCoord.getRow() + i + lineUsed;
      int columnPosition = this.startCoord.getColumn() + 2;
      //Move cursor to it
      Curse.cursor(rowPosition, columnPosition);
      //If there is enough place in line to print
      if (linesText[i].length() <= this.height - 4) {
        System.out.println(linesText[i]);
      } else {
        //If not, we try to print word by word
        String[] words = linesText[i].split(" ");
        int sizeUsed = 0;
        int wordRetries = 0;
        for (int j = 0; j < words.length; j++) {
          //We need to edit word if it's null, to add backspace
          String formatedWord = words[j];
          if (words[j].length() == 0) {
            formatedWord += " ";
          }
          int wordLength = formatedWord.length();
          int sizeNeeded = (this.width - 4) * (1 + lineUsed);
          //If there is enough place, we print it !
          if (wordLength + sizeUsed < sizeNeeded) {
            System.out.print(formatedWord);
            wordRetries = 0;
          } else {
            //If not, we need to go to next line
            lineUsed++;
            //And increment size not used
            sizeUsed = (this.width - 4) * lineUsed;
            //Retry this word
            wordRetries++;
            //Go to next position and decrement j to retry word only once
            if (wordRetries < 2) {
              Curse.cursor(rowPosition + lineUsed, columnPosition);
              j--;
            } else {
              //We cannot print anymore !
              return;
            }
          }
        }
      }
    }
  }

//----------------------------------


// Constructor
//----------------------------------
  public TextBox(int startRow, int startColumn, int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
    this.setStartCoord(startRow, startColumn);
    this.setLines(startRow, startColumn);
  }
//----------------------------------


// Methods
//----------------------------------
  /*Draw each lines*/
  public void draw(char c) {
    this.topLine.draw(this, c);
    this.bottomLine.draw(this, c);
    this.leftLine.draw(this, c);
    this.rightLine.draw(this, c);
  }

  /*Draw one of the fourth lines, with a certain character*/
  public void draw(oneSide side, char c) {
    switch (side) {
      case TOP:
        this.topLine.draw(this, c);
        break;

      case BOTTOM:
        this.bottomLine.draw(this, c);
        break;

      case LEFT:
        this.leftLine.draw(this, c);
        break;

      case RIGHT:
        this.rightLine.draw(this, c);
        break;

      default:
        break;
    }
  }
//----------------------------------
}
