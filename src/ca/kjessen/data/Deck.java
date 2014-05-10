package ca.kjessen.data;

import java.util.ArrayList;
import java.util.Set;
import java.util.Random;
/**
 * Class Deck holds an Array List of Cards. It has method to add a single card, to add all 52 valid cards,
 * to remove the first card and return it, to validate a position in the deck, to print the contents to the console,
 * to check if the deck is empty, to swap the cards at two index positions, and to shuffle the deck using the swap 
 * method multiple times (currently 50).
 * 
 * @author Katrina Jessen
 * @version 2014.01.27
 */
public class Deck {
	// instance variables
    private ArrayList<Card> cards;
    public static final int TIMES_TO_SHUFFLE = 200;

    /**
     * Constructor for objects of class Deck
     */
    public Deck()
    {
        // initialise instance variables
        cards = new ArrayList<Card>();
    }

    /**
     * Add one card to the Deck
     */
    public void addCard(Card newCard)
    {
        cards.add(newCard);
    }
    
    /**
     * Add all 52 valid cards to the deck
     */
    public void addAllCards()
    {
       Card garbageCard = new Card("king", "spade");
       Set<String> descriptionSet = garbageCard.descriptions.keySet();
       for( int i = 0; i< 4; i++){
           for(String currentKey : descriptionSet){
               cards.add(new Card(currentKey, garbageCard.getSuitsValue(i)));
            }
        }
    }
    
    /**
     * Takes the Card from the beginning of the Deck and returns it. If the Deck is empty, then it
     * returns null.
     * @return Card the Card that was at the beginning of the Deck, null if Deck was empty.
     */
    public Card returnFirstCard()
    {
    	if(cards.size() > 0){
    		Card firstCard = cards.get(0);
    		cards.remove(0);
    		return firstCard;
    	} else {
    		return null;
    	}
    
    }
    
    /**
     * Validate a position
     * @param input integer to be checked, check will insure that integer is greater than zero (inclusive)
     * and less than the size of the Deck (exclusive)
     * @return boolean true if integer is greater than zero (inclusive) and less than the size of the
     * Deck (exclusive), false if it is not
     */
    public boolean isValid(int input)
    {
    	return (input >= 0 && input < cards.size());
    }
    
    /**
     * Swaps the Cards at the two given index positions
     * @param position1 integer which will give the index of the first card to be swapped
     * @param position2 integer which will give the index of the second card to be swapped
     */
    public void swap(int position1, int position2)
    {
    	if(isValid(position1) && isValid(position2))
    	{
    		Card card1 = cards.get(position1);
    		Card card2 = cards.get(position2);
    		cards.set(position1, card2);
    		cards.set(position2,  card1);
    	}
    }
    /**
     * Shuffle the deck ten times by calling the swap function TIMES_TO_SHUFFLE times (currently 50), 
     * using random numbers created by a generator of the Random class.
     */
    public void shuffle()
    {
    	Random randomGenerator = new Random();
    	int timesShuffled = 0;
    	while(timesShuffled < TIMES_TO_SHUFFLE){
    		swap(randomGenerator.nextInt(cards.size()), randomGenerator.nextInt(cards.size()));
            timesShuffled++;
    	}
    }
    
    /**
     * Prints the contents of the Deck to the console, which displays the information from the
     * description and suit fields of the Card object in format "<description> of <suit>",
     * eg. "Ace of Spades"
     */
    public void showDeck()
    {
        for(Card currentCard : cards){
            System.out.println(currentCard.getDescription() + " of " + currentCard.getSuit());
        }
    }
    
    /**
     * Checks to see if the Deck is empty
     * @return returns true if the deck is empty, returns false if the deck is not empty
     */
    public boolean isEmpty()
    {
    	return cards.isEmpty();
    }
}
