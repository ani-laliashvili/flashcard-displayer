import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;


/* @author Ani Laliashvili
   @author Sarah Gregory */

public class FlashcardDisplayer {
    public FlashcardPriorityQueue queue;

    /**
    * Creates a flashcard displayer with the flashcards in file.
    * File has one flashcard per line. On each line, the date the flashcard 
    * should next be shown is first (format: YYYY-MM-DDTHH-MM), followed by a tab, 
    * followed by the text for the front of the flashcard, followed by another tab.
    * followed by the text for the back of the flashcard. You can assume that the 
    * front/back text does not itself contain tabs. (I.e., a properly formatted file
    * has exactly 2 tabs per line.)
    * The time may be more precise (e.g., seconds may be included). The parse method
    * in LocalDateTime can deal with this situation without any changes to your code.
    */
    public FlashcardDisplayer(String file){
        queue = new FlashcardPriorityQueue();
        Scanner fileData = null;
        try {
            fileData = new Scanner(new File(file));
            while (fileData.hasNextLine()) {
                String line = fileData.nextLine();
                String[] lineData = line.split("\t");
                Flashcard flashcard = new Flashcard(lineData[0], lineData[1], lineData[2]);
                queue.add(flashcard);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error opening the file: " + file);
            System.exit(1);
        }
    }
 
    /**
    * Writes out all flashcards to a file so that they can be loaded
    * by the FlashcardDisplayer(String file) constructor. Returns true
    * if the file could be written. The FlashcardDisplayer should still
    * have all of the same flashcards after this method is called as it
    * did before the method was called. However, flashcards with the same
    * exact same next display date may later be displayed in a different order.
    */
    public boolean saveFlashcards(String outFile){
        PrintWriter toFile = null;
        // try output file
            try {
                toFile = new PrintWriter(outFile);
            } catch (FileNotFoundException e) {
                System.err.println("Scanner error opening the file " + outFile);
            }
            
            toFile.print(queue.toText()); 
            toFile.close(); // close PrintWriter
        return false;
    }
 
    /**
    * Displays any flashcards that are currently due to the user, and 
    * asks them to report whether they got each card correct. If the
    * card was correct, it is added back to the deck of cards with a new
    * due date that is one day later than the current date and time; if
    * the card was incorrect, it is added back to the card with a new due
    * date that is one minute later than that the current date and time.
    */
    public void displayFlashcards(){
        boolean stop = false;
        int i = 0;
        
        while (stop == false && i <= queue.getLastIndex()) {
            if (queue.getFlashcard(i).getDueDate().compareTo(LocalDateTime.now()) < 0) {
                System.out.println(queue.getFlashcard(i).getFrontText());
                Scanner input = new Scanner(System.in);
                System.out.println("[Press return for back of card]");
                if (input.nextLine().equals("")) {
                    System.out.println(queue.getFlashcard(i).getBackText());
                }
                System.out.println("Press 1 if you got the card correct and 2 if you got the card incorrect.");
               // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                String correct = input.next();

                if (correct.equals("1")) {
                // make due in one day
                    queue.getFlashcard(i).setDueDate((queue.getFlashcard(i).getDueDate().minusDays(-1)).format(formatter));
                } else if (correct.equals("2")) {
                // make due in one minute
                    queue.getFlashcard(i).setDueDate((queue.getFlashcard(i).getDueDate().minusMinutes(-1)).format(formatter));
                }
                i++;
            } else {
                stop = true;
               // System.out.println("No cards are waiting to be studied!"); // all cards due have been displayed
            }
        }
        System.out.println("No cards are waiting to be studied!"); // all cards due have been displayed
    }
    
    
    public static void main(String[] args){
        String path = args[0];
        FlashcardDisplayer disp = new FlashcardDisplayer(path);
        
        disp.queue.display();
        
        System.out.println("Time to practice flashcards! The computer will display your flashcards, you generate the response in your head, and then see if you got it right.");
        System.out.println("The computer will show you cards that you miss more often than those you know!");
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (exit == false) {
        System.out.println("Enter a command: ");
        String function = (scanner.nextLine()).trim();
        if (function.equals("quiz")) {
            // display flashcards due
            disp.displayFlashcards();
        } else if (function.equals("save")) {
            // prompt with filename to save file as, should not crash with bad name
          // WHEN WOULD THE PROGRAM CRASH?

            System.out.println("Please input desired name as .txt file:");
            String filename = scanner.nextLine();
            
            disp.saveFlashcards(filename);
        } else if (function.equals("exit")) {
            exit = true;
            System.exit(1);
        }
        }
    
    }
}