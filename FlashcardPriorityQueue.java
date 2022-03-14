import java.util.NoSuchElementException;

/* @author Ani Laliashvili*/
public class FlashcardPriorityQueue implements PriorityQueue<Flashcard>{
   private Flashcard[] heap; // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries

  /**
   * Creates an empty priority queue.
   */
  public FlashcardPriorityQueue(){
    heap = new Flashcard[300];
    lastIndex = 0;
  }

 /** Adds the given item to the queue. */
 
  public void add(Flashcard item){
    if (isEmpty()){
        heap[lastIndex] = item;
    } else { 
        int childIndex = lastIndex + 1;
        lastIndex ++;       
        int i = lastIndex;
        
      while ((i != -1) && item.compareTo(heap[(i-1)/3]) < 0){
            heap[i] = heap[(i-1)/3];
            childIndex = (i-1)/3;
            if (i == 0) {
                i = -1;
            } else {
                i = (i-1)/3;
            }
      } // end while  
      heap[childIndex] = item;
    } 
  } // end add method


    
  /** Adds the given item to the queue. */
  /*public void add(Flashcard item){
    if (isEmpty()){
        heap[lastIndex] = item;
    } else {        
      heap[lastIndex + 1] = item;
      lastIndex++;
      int i = lastIndex;
      Flashcard parent;

      while ((i != -1) && item.compareTo(heap[(i-1)/3]) < 0){
            parent = heap[(i-1)/3];
            heap[(i-1)/3] = item;
            heap[i] = parent;
            if (i == 0) {
                i = -1;
            } else {
                i = (i-1)/3;
            }
      } // end while  
    } // maybe reserve space instead of placing item
      
  } // end add method */
  
    /** Removes the first item according to compareTo from the queue, and returns it.
  * Throws a NoSuchElementException if the queue is empty.
  */
  public Flashcard poll(){
    Flashcard removed;
    //int lastItemIndex = -1;
    try {
      removed = heap[0];
      int lastItemIndex = 0;
      Flashcard lastItem = heap[lastIndex];

     // heap[0] = heap[lastIndex];
      lastIndex--;
      int i = 0;

      while (i != lastIndex) {
       // Flashcard item = heap[i];

        //Flashcard parent = heap[3*i + 1];
        int parIndex = 3*i + 1;

          if (heap[3*i + 2] != null && heap[3*i + 2].compareTo(heap[parIndex]) < 0){
            //parent = (heap[3*i + 2]);
            parIndex = 3*i + 2;
          } else if (heap[3*i + 3] != null && heap[3*i + 3].compareTo(heap[parIndex]) < 0) {
            //parent = heap[3*i + 3];
            parIndex = 3*i + 3;
          }
         // System.out.println("parIndex " + parIndex + heap[parIndex].toString());
          if (heap[parIndex].compareTo(lastItem) < 0){
            //heap[i] = parent;
            heap[i] = heap[parIndex];
            //heap[parIndex] = item;
            lastItemIndex = parIndex;
            i = 3*i + 1;
          }
        }// end while
        heap[lastItemIndex] = lastItem;

      } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Queue is empty");
    } // end catch
    return removed;
  } // end poll method


  /** Removes the first item according to compareTo from the queue, and returns it.
  * Throws a NoSuchElementException if the queue is empty.
  */
  /*
  public Flashcard poll(){
    Flashcard removed;
    try {
      removed = heap[0];
      heap[0] = heap[lastIndex];
      lastIndex--;
      int i = 0;

      while (i != lastIndex) {
        Flashcard item = heap[i];
        Flashcard parent = heap[3*i + 1];
        int parIndex = 3*i + 1;

          if (heap[3*i + 2] != null && heap[3*i + 2].compareTo(parent) < 0){
            parent = (heap[3*i + 2]);
            parIndex = 3*i + 2;
          } else if (heap[3*i + 3] != null && heap[3*i + 3].compareTo(parent) < 0) {
            parent = heap[3*i + 3];
            parIndex = 3*i + 3;
          }

          if (parent.compareTo(item) < 0){
            heap[i] = parent;
            heap[parIndex] = item;
            i = 3*i + 1;
          }
        }// end while
      } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Queue is empty");
    } // end catch
    return removed;
  } // end poll method */

   /* public Flashcard poll(){
    try {
      heap[1] = heap[lastIndex];
      lastIndex--;
      int i = 1;

      while (i != lastIndex) {
        Flashcard item = heap[i];
        Flashcard parent = heap[3*i];
        int parIndex = 3*i;
        
          if (heap[3*i + 1] != null && heap[3*i + 1] < parent){
            parent = (heap[3*i + 1]);
            parIndex = 3*i + 1;
          } else if (heap[3*i + 2] != null && heap[3*i + 2] < parent) {
            parent = heap[3*i + 2];
            parIndex = 3*i + 2;
          }

          if (parent < item){
            heap[i] = parent;
            heap[parIndex] = item;
            i = 3*i;
          }
        }// end while
      }//end try
    } catch {
      throw new NoSuchElementException;
    } // end catch
  } // end poll method
    */

  /** Returns the first item according to compareTo in the queue, without removing it.
  * Throws a NoSuchElementException if the queue is empty.
  */
  public Flashcard peek(){
    try {
      return heap[0];
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException(" ");
    }
  }
    
  /** Returns true if the queue is empty. */
  public boolean isEmpty(){
    return heap[0] == null;
  }
    
    public int getLastIndex(){
        return lastIndex;
    }
    
  /** Removes all items from the queue. */
  public void clear(){
    int i = lastIndex;
    lastIndex = 0;

      while (i > -1){
        heap[i] = null;
        i--;
      }
  }
    
    public void display(){
        for (int i = 0; i <= lastIndex; i++){
            System.out.println("Index " + i + ": " + heap[i]);
        }
    }
    
    public Flashcard getFlashcard(int i){
        return heap[i];
    }

    public String toText(){
      int i = 0;
      String text = "";
      while(!isEmpty() && i <= lastIndex){
        text = text + "\n" + heap[i].stringDueDate() + "\t" + heap[i].getFrontText() + "\t" + heap[i].getBackText();
        i++;
      }
      return text;
    }


  public static void main(String[] args){
    //test
    FlashcardPriorityQueue q = new FlashcardPriorityQueue();
    System.out.println("Testing isEmpty(): ");
    System.out.println("Should return true: " + q.isEmpty());

    Flashcard item1 = new Flashcard("1998-12-11T12:22","a","b");
    Flashcard item2 = new Flashcard("1999-12-11T13:33","c","d");
    Flashcard item3 = new Flashcard("2000-12-11T13:33","e","f");
    Flashcard item4 = new Flashcard("1999-11-11T13:33","h","i");
    Flashcard item5 = new Flashcard("1999-12-12T13:33","j","k");

      
    System.out.println("item1: " + item1.toString());
    System.out.println("item2: " + item2.toString());
    System.out.println("item3: " + item2.toString());
      
    System.out.println("Testing add: ");  
    q.add(item2);
    System.out.println("lastIndex is 0: " + q.lastIndex);  
    q.add(item1);
    System.out.println("lastIndex is 1: " + q.lastIndex);
    q.add(item3);
    System.out.println("lastIndex is 2: " + q.lastIndex);
    q.add(item3);
    System.out.println("lastIndex is 3: " + q.lastIndex);
      
    // add not working
    System.out.println("Index 0 should show item1: " + q.heap[0].toString());
    System.out.println("Index 1 should show item2: " + q.heap[1].toString());
    System.out.println("Index 2 should show item3: " + q.heap[2].toString());
    System.out.println("Index 3 should show item3: " + q.heap[3].toString());
    
    // more add tests
    q.add(item4);
    System.out.println("lastIndex is 4: " + q.lastIndex);
      
    System.out.println("Index 0 should show item1: " + q.heap[0].toString());
    System.out.println("Index 1 should show item4: " + q.heap[1].toString());
    System.out.println("Index 2 should show item3: " + q.heap[2].toString());
    System.out.println("Index 3 should show item3: " + q.heap[3].toString());  
    System.out.println("Index 4 should show item2: " + q.heap[4].toString());  

    // more add tests
    q.add(item5);
    System.out.println("lastIndex is 5: " + q.lastIndex);
      

    System.out.println("Index 0 should show item1: " + q.heap[0].toString());
    System.out.println("Index 1 should show item4: " + q.heap[1].toString());
    System.out.println("Index 2 should show item3: " + q.heap[2].toString());
    System.out.println("Index 3 should show item3: " + q.heap[3].toString());  
    System.out.println("Index 4 should show item2: " + q.heap[4].toString());   
    System.out.println("Index 5 should show item5: " + q.heap[5].toString());    

      System.out.println("Testing peek, should show item1: " + q.peek());
      
    System.out.println("Testing poll()");  
    System.out.println("Removed: " + q.poll().toString());
    System.out.println("Index 0 should show item4: " + q.heap[0].toString());
    System.out.println("Index 1 should show item2: " + q.heap[1].toString());
    System.out.println("Index 2 should show item3: " + q.heap[2].toString());
    System.out.println("Index 3 should show item3: " + q.heap[3].toString());  
    System.out.println("Index 4 should show item5: " + q.heap[4].toString()); 
    
    System.out.println(q.toText());

      q.clear();
      System.out.println("Testing clear(), should return true: " + q.isEmpty());
  
    Flashcard f0 = new Flashcard("1700-12-11T12:22","a","b");
    Flashcard f1 = new Flashcard("1800-12-11T13:33","c","d");
    Flashcard f2 = new Flashcard("1800-12-11T13:33","e","f");
    Flashcard f3 = new Flashcard("1800-11-11T13:33","h","i");
    Flashcard f4 = new Flashcard("1900-12-12T13:33","j","k");
    Flashcard f5 = new Flashcard("2000-12-12T13:33","j","k");

    q.add(f0);
    q.add(f1);
    q.add(f2);
    q.add(f3);
    q.add(f4);
    q.add(f5);
    System.out.println("test edge case for poll(): ");
    q.poll();
    System.out.println("Index 0 should show f1: " + q.heap[0].toString());
    System.out.println("Index 1 should show f2: " + q.heap[1].toString());
    System.out.println("Index 2 should show f3: " + q.heap[2].toString());
    System.out.println("Index 3 should show f4: " + q.heap[3].toString());  
    System.out.println("Index 4 should show f5: " + q.heap[4].toString()); 
  }
}
