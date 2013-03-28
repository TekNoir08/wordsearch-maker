import java.util.*;
/**
 * WordSearchMaker
 * 
 * @author Michael Kemp
 * @version 1.0
 */
public class WordSearchMaker
{
   // Class variables
   private static final int NUMBER_ROWS = 12;
   private static final int NUMBER_COLS = 25;
   private static final char BLANK_ELEMENT = '*';

   // Instance variables
   public char[][] letterGrid;
   private Random ran;
   private int lastWordOrientation;

   /**
    * Constructor for objects of class WordSearchMaker - 
    */
   public WordSearchMaker()
   {
      super();
       
      this.ran = new Random();
      this.lastWordOrientation = 0;
      this.letterGrid = new char [WordSearchMaker.NUMBER_ROWS][WordSearchMaker.NUMBER_COLS];
      this.clearGrid();
   }
   
   // instance methods

   /**
    * Getter for ran instance variable
    */
   private Random getRan()
   {
      return this.ran;
   }
   
   /**
    * Helper method - returns a random number between 0 and one 
    * less than the argument.
    */
   private int randomPosition(int aNumber)
   {
    return this.getRan().nextInt(aNumber);
   }
   
   /** 
    * Helper method - returns a random capital letter.
    */
   private char randomLetter()
   { 
      return (char) (this.getRan().nextInt('Z'-'A') + 'A');
   }

   /**
    * Setter for lastWordOrientation.
    */
   private void setLastWordOrientation(int aNumber)
   {
      this.lastWordOrientation = aNumber;
   }
   
   /**
    * Getter for lastWordOrientation.
    */
   private int getLastWordOrientation()
   {
      return lastWordOrientation;
   }   
   
    /**
     * Attempts up to 10 times to insert the word given by the String argument 
     * horizontally into the grid starting at a random row and column position. 
     * If insertion successful, returns true, false otherwise.
     */
   
    public boolean insertHorizontally(String word)
    {        
         for (int i = 0; i < 10; i++)
         {
             int targetRow = randomPosition(WordSearchMaker.NUMBER_ROWS);
             int targetCol = randomPosition((WordSearchMaker.NUMBER_COLS - word.length()) + 1);

             if (this.isHorizontalSpaceFree(targetRow, targetCol, word))
             {
                 for (int j = 0; j < word.length(); j++)
                 {
                	 letterGrid[targetRow][targetCol + j] = word.charAt(j); 	 
                 }
                 return true;
             }
             
         }    
         return false;                                        
    }


   /**
    * Attempts up to 10 times to insert the word given by the String argument 
    * vertically into the grid starting at a random row and column position. 
    * If insertion successful, returns true, false otherwise.
    */
    public boolean insertVertically(String word)
    {        
         for (int i = 0; i < 10; i++)
         {
             int targetCol = randomPosition(WordSearchMaker.NUMBER_COLS);
             int targetRow = randomPosition((WordSearchMaker.NUMBER_ROWS - word.length()) + 1);

             if (this.isVerticalSpaceFree(targetRow, targetCol, word))
             {
                 for (int j = 0; j < word.length(); j++)
                 {
                	 letterGrid[targetRow + j][targetCol] = word.charAt(j); 	 
                 }
                 return true;
             }
             
         }    
         return false;                                        
    }


   /**
    * Attempts up to 10 times to insert the word given by the String argument 
    * diagonally into the grid starting at a random row and column position. 
    * If insertion successful, returns true, false otherwise.
    */
    public boolean insertDiagonally(String word)
    {        
         for (int i = 0; i < 10; i++)
         {
             int targetCol = randomPosition((WordSearchMaker.NUMBER_COLS - word.length()) + 1);
             int targetRow = randomPosition((WordSearchMaker.NUMBER_ROWS - word.length()) + 1);
        	 	

             if (this.isDiagonalSpaceFree(targetRow, targetCol, word))
             {
                 for (int j = 0; j < word.length(); j++)
                 {
                	 letterGrid[targetRow + j][targetCol + j] = word.charAt(j); 	 
                 }
                 return true;
             }
             
         }    
         return false;                                        
    }




   
   
   /**
    * Method attempts to insert word into letterGrid.
    * 
    * If an attempt at inserting word vertically is unsuccessful, 
    * an attempt is made at inserting it horizontally; if this attempt 
    * is unsuccessful, an attempt is made at inserting it diagonally.
    * 
    * The above sequence of attempts is carried out 20 times or until  
    * word has been successfully inserted into letterGrid. 
    */
   public void addWordAllOrientations(String word)
   {
      int orientation =  this.getLastWordOrientation() % 3;
      boolean placed = false;
      this.setLastWordOrientation(this.getLastWordOrientation() + 1);
      int index = 0;
      while ((placed == false) && (index < 20))
      {
         if (orientation == 0)
         {
            placed = this.insertVertically(word);
         }
         if (orientation == 1)
         {
            placed = this.insertHorizontally(word);
         }
         if (orientation == 2)
         {
            placed = this.insertDiagonally(word);
         }
         index++;
      }
   }
   
   /**
    * This method iterates over the letterGrid array and assigns
    * the BLANK_ELEMENT value to each component.
    */
    public void clearGrid()
    {
        for (int i = 0; i < WordSearchMaker.NUMBER_ROWS; i++)
        {
            for (int j = 0; j < WordSearchMaker.NUMBER_COLS; j++)
            {
                this.letterGrid[i][j] = WordSearchMaker.BLANK_ELEMENT;
            }
        }
    }
  
    /**
     * Prints the letterGrid array to the display pane. The method first 
     * prints a blank line, then prints each element from the array in 
     * the correct position and with a space ' ' following it. Once
     * each element is displayed, another blank line is then printed.
     */
    public void printWordSearch()
    {
        System.out.println();
        
        for (int i = 0; i < WordSearchMaker.NUMBER_ROWS; i++)
        { 
            for (int j = 0; j < WordSearchMaker.NUMBER_COLS; j++)
            {
                  System.out.print(this.letterGrid[i][j]);
                  System.out.print(' ');
            }   
            System.out.println();
        }
        System.out.println();
    }
    
 
    /** 
     * This method checks to see if there is enough space horizontaly
     * from left to right to insert word into the letter grid. It will 
     * return true if there is and false if not.
     */
    private boolean isHorizontalSpaceFree(int aRow, int aCol, String word) 
    {	
        int count = 0;
    	boolean flag = true;
    	while(count < word.length() && flag != false)
    	{
    		if (letterGrid[aRow][aCol] == WordSearchMaker.BLANK_ELEMENT)
    		{
    			aCol++;
    			count++;
    		} else {
    			flag = false;
    		}
    		
    	}
    	return flag;
    }

    /** 
     * This method checks to see if there is enough space vertically
     * from left to right to insert word into the letter grid. It will 
     * return true if there is and false if not.
     */    
    private boolean isVerticalSpaceFree(int aRow, int aCol, String word) 
    {	
    	int count = 0;
    	boolean flag = true;
    	while(count < word.length() && flag != false)
    	{
    		if (letterGrid[aRow][aCol] == WordSearchMaker.BLANK_ELEMENT)
    		{
    			aRow++;
    			count++;
    		} else {
    			flag = false;
    		}
    		
    	}
    	return flag;
    }
        
    
    /** 
     * This method checks to see if there is enough space diagonally
     * from top left to bottom right to insert word into the letter grid. 
     * It will return true if there is and false if not.
     */  
    public boolean isDiagonalSpaceFree(int aRow, int aCol, String word) 
    {	
    	int count = 0;
    	boolean flag = true;
    	while(count < word.length() && flag != false)
    	{
    		if (letterGrid[aRow][aCol] == WordSearchMaker.BLANK_ELEMENT)
    		{
    			aRow++;
    			aCol++;
    			count++;
    		} else {
    			flag = false;
    		}
    		
    	}
    	return flag;
    }     
    /**
     * This method takes an array of strings and then for each one
     * of those strings in the array passes it to the addWordAllOrientations
     * method.
     */
    public void buildForm(String[] wordList)
    {
        for(String eachString : wordList)
        {
            this.addWordAllOrientations(eachString);   
        }
    }
    /**
     * This method iterates through all the components in the array
     * if the component is a BLANK_ELEMENT it will replace it with a
     * random uppercase letter from A-Z
     */
    public void fillRemainingComponents()
    {
        for (int i = 0; i < WordSearchMaker.NUMBER_ROWS; i++)
        {
            for (int j = 0; j < WordSearchMaker.NUMBER_COLS; j++)
            {
                if (letterGrid[i][j] == WordSearchMaker.BLANK_ELEMENT)
                {
                    letterGrid[i][j] = this.randomLetter();  
                }
            }
        }
    }
        
        
}