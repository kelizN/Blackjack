package ca.kjessen;

import ca.kjessen.data.Game;


/**
 * Class Game_Driver allows the user to play the BlackJack game contained in the Game class
 * 
 * @author Katrina Jessen
 * @version 2014.02.04
 *
 */
public class Game_Driver {
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		Game Game1 = new Game();
		Game1.play();
	}

}
