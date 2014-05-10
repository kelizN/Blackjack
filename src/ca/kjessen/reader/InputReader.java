package ca.kjessen.reader;

import java.util.Scanner;
/**
 * Class InputReader reads user input from the keyboard.
 * 
 * @author Katrina Jessen
 * @version 2014.02.04
 */
public class InputReader {
	
	private Scanner scanner;
    
    /**
     * Create a new InputReader to read user input.
     */
    public InputReader()
    {
        scanner = new Scanner(System.in); //for keyboard input
    }
    
    /**
     * Gets the user's input
     * @return the user's input as a String
     */
    public String getInput()
    {
        return scanner.nextLine();
    }
}
