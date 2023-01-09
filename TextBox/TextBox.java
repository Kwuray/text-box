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
  private String[] splittedContent;
//----------------------------------


//set & get methods
//----------------------------------
  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
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
    //We need to remove all non-printable char at the end
    this.content = Curse.removeNoPrintableAtTheEnd(txt);
    this.setSplittedContent();
    this.printContent();
  }

  //Set the 4 corners coordinates
  private void setLines(int startRow, int startColumn) {
    this.setTopLine(startRow, startColumn);
    this.setBottomLine(startRow + this.getHeight() - 1, startColumn);
    this.setLeftLine(startRow, startColumn);
    this.setRightLine(startRow, startColumn + this.getWidth() - 1);
  }

  //split content in array with one entry by row
  private void setSplittedContent() {
    for (int i = 0; i < this.splittedContent.length; i++) {
      this.splittedContent[i] = "";
    }
    //Divide text in seperate line
    String[] linesText = Curse.split(this.content, '\n');
    //While we can print inside the box
    int resultCounter = 0;
    int lineCounter = 0;
    while(resultCounter < this.splittedContent.length) {
      //Add line if there is one
      if (lineCounter < linesText.length) {
        //If there is enough space
        if (this.splittedContent[resultCounter].length() + linesText[lineCounter].length() <= this.width - 4) {
          this.splittedContent[resultCounter] = linesText[lineCounter];
        } else {
          //If not, we try to print word by word
          String[] words = Curse.split(linesText[lineCounter], ' ');
          //Add each word
          for (int i = 0; i < words.length; i++) {
            //If there is enough space, add the word
            if (this.splittedContent[resultCounter].length() + words[i].length() <= this.width - 4) {
              this.splittedContent[resultCounter] = this.splittedContent[resultCounter] + words[i];
            } else if (words[i].charAt(words[i].length() - 1) == ' ' && this.splittedContent[resultCounter].length() + words[i].length() - 1 <= this.width - 4) { //Try to print if last char is backspace
              this.splittedContent[resultCounter] = this.splittedContent[resultCounter] + words[i].substring(0, words[i].length() - 2);
            } else if (words[i].length() > this.width - 4) { //If the word length is greater than box width
              //add the maximum characters
              resultCounter++;
              this.splittedContent[resultCounter] = words[i].substring(0, this.width - 4);
              words[i] = words[i].substring(this.width - 4, words[i].length());
              i--;
            } else {
              //No more space !
              resultCounter++;
              i--;
            }
          }
        }
      }
      resultCounter++;
      lineCounter++;
    }
  }

  /*Print content inside the TextBox*/
  protected void printContent() {
    for (int i = 0; i < this.splittedContent.length; i++) {
      //Calculate new position
      int rowPosition = this.startCoord.getRow() + i + 1;
      int columnPosition = this.startCoord.getColumn() + 2;
      //Move cursor to it
      Curse.cursor(rowPosition, columnPosition);
      //print one line
      System.out.print(this.splittedContent[i]);

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
    this.splittedContent = new String[this.getHeight() - 2];
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
