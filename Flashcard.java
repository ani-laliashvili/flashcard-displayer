/* @author Ani Laliashvili
   @author Sarah Gregory */

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Flashcard implements Comparable<Flashcard> {
  public String frontText;
  public String backText;
  public String dueDate; //YYYY-MM-DDTHH:MM

/**
 * Creates a new flashcard with the given dueDate, text for the front
 * of the card (front), and text for the back of the card (back).
 * dueDate must be in the format YYYY-MM-DDTHH:MM. For example,
 * 2019-11-04T13:03 represents 1:03PM on November 4, 2019. It's
 * okay if this method crashes if the date format is incorrect.
 * In the format above, the time may be 
 * more precise (e.g., seconds or milliseconds may be included). 
 * The parse method in LocalDateTime can deal with these situations
 *  without any changes to your code.
 */
public Flashcard(String dueDate, String front, String back){
  frontText = front;
  backText = back;
  this.dueDate = dueDate;
} // end constructor
 
/**
 * Gets the text for the front of this flashcard.
 */
public String getFrontText(){
  return frontText;
}
 
/**
 * Gets the text for the Back of this flashcard.
 */
public String getBackText(){
  return backText;
}
 
/**
 * Gets the time when this flashcard is next due.
 */
public LocalDateTime getDueDate(){
  return LocalDateTime.parse(dueDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
}
    
public void setDueDate(String date) {
    dueDate = date;
}    

public String toString(){
  String string = "Front text: " + frontText + "; Back text: " + backText + "; Due date: " + dueDate;
  return string;
}

public String stringDueDate(){
  return dueDate;
}

@Override
/** 
*returns < 0 if Flashcard1 is smaller than Flashcard2, and > 0 if
*Flashcard2 is smaller than Flashcard1, and 0 if Flashcard1 == 
*Flashcard2. 
* Example usage: Flashcard1.compareTo(Flashcard2)
*/
public int compareTo(Flashcard obj2){
  int comp = 0;
  boolean stop = false;
  int i = 0;

  while (stop != true && i < dueDate.length()) {
    if (dueDate.charAt(i) < obj2.dueDate.charAt(i)) {
      comp = -1;
      stop = true;
    } else if (dueDate.charAt(i) > obj2.dueDate.charAt(i)) {
      comp = 1;
      stop = true;
    } else if (i == dueDate.length() && dueDate.charAt(i) == obj2.dueDate.charAt(i)) {
      comp = 0;
    }
    i++; 
  }
  return comp;
}
    
    
    
public static void main(String[] args){
    Flashcard item1 = new Flashcard("1999-12-11T12:22","a","b");
    Flashcard item2 = new Flashcard("1999-12-11T13:33","c","d");
    Flashcard item3 = new Flashcard("2000-12-11T13:33","e","f");
    
    System.out.println("Testing getText: ");
    
    System.out.println("This should return 'a'" + item1.getFrontText());
    System.out.println("This should return 'b'" + item1.getBackText());
    
    System.out.println("This should return 'c'" + item2.getFrontText());
    System.out.println("This should return 'd'" + item2.getBackText());
    
    System.out.println("This should return 'e'" + item3.getFrontText());
    System.out.println("This should return 'f'" + item3.getBackText());
    
    System.out.println("Testing toString: ");
    
    System.out.println("item1: " + item1.toString());
    System.out.println("item2: " + item2.toString());
    System.out.println("item3: " + item3.toString());
    
    System.out.println("Testing compareTo: ");
    
    System.out.println("Should return a negative number: " + item1.compareTo(item2));
    System.out.println("Should return a positive number: " + item3.compareTo(item1));
}    

}