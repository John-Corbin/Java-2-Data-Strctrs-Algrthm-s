package Project_7;

import java.io.*;
import java.util.*;

/**
 * WordCountNode class - The data portion of the linked list
 */
class WordCountNode {			//we dont have to change the, but we do need to use them.
	String word;
	int count;
	
	/**
	 * WordCountNode constructor
	 * @param inWord
	 */
	public WordCountNode(String inWord) {
		word = inWord;
		count = 1;
	}	
	
	/**
	 * @return the object word datum
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * @return the object count datum
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the object count auto-incremented
	 */
	public int incrementCount() {
		count++;
		return count;
	}
}


public class linkedList {	//name of linked list is words
	static LinkedList<WordCountNode> words = new LinkedList<WordCountNode>();
	
	/**
	 * addWordToLinkedList - add word to linked list with counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
 
static int addWordToLinkedList(String inWord) {
    	int wordCount = 0;
        boolean foundWord = false;
        
		// Search for word, set boolean and increment count if found
        for (WordCountNode list : words) {
            if (list.getWord().equals(inWord)) {
            	list.incrementCount();
            	foundWord = true;
                break;
            }
        }
        
		// If word not found in the loop above, add it to list end.
        if (!foundWord) {
            WordCountNode newWord = new WordCountNode(inWord);
            words.add(newWord);
        }
        return wordCount;
    }
	
	
	/**
	 * addWordToSortedLinked List - add word to sorted linked list
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
static int addWordToSortedLinkedList(String inWord) {
    int index = 0;
    // find correct pos for new word
    for (WordCountNode list : words) {
        if (inWord.compareTo(list.getWord()) < 0) {
            // if new word is less than the current insert it at the currents spot
            words.add(index, new WordCountNode(inWord));
            return words.size();
        }
        else if (inWord.equals(list.getWord())) {
        	// if word exists count++ and return words.size
            list.incrementCount();
            return words.size();
        }
        index++;
    }
    // adds a new node at the end of the list
    words.add(new WordCountNode(inWord));
    return words.size();
}
	
	
	/**
	 * printWordList - dump the list if count > inMinimum
	 */
	static void printWordList(int inMinimum) {
		System.out.println("These Words Appear more than 1000 time in the book of Psalms: ");
	    for (WordCountNode list : words) {
	        if (list.getCount() >= inMinimum) {
	            System.out.println(list.getWord() + ": " + list.getCount());
	        }
	    }
	}
	
	/**
	 * getVerse - Parse and return only the Psalms verse
	 * @param inLine - the line to parse on tab delimiter
	 * @return right half of tab delimited string
	 */
	static String getVerse(String inLine) {
		String[] ver = inLine.split("\t");
		return ver[1];
	}

	/**
	 * PsalmsReaderMain main() - Template for Assignment-7
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Open the required text file for sequential read
			Scanner inputFile =
					new Scanner (new File("bible-psalms.txt"));
			// Check for EOF, read the next line, and display it
			while (inputFile.hasNextLine()) {
				String inLine, verse;
				String[] verseParsed;
				
				inLine = inputFile.nextLine();
				verse = getVerse(inLine);
				verseParsed = verse.split("[ :;,.'!?()-]+");

				for (String s: verseParsed) {
					//addWordToLinkedList(s.toLowerCase());
					addWordToSortedLinkedList(s.toLowerCase());
				}
			}
			// Close the required file when EOF is reached
			inputFile.close();
			printWordList(1000);
		} // END try
		catch (Exception e) {
			// All Exceptions come here for graceful termination
			System.out.println("PsalmsReaderMain Error: " + e);
		} // END catch
	} // END main
} // END class
