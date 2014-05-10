package ca.kjessen.data;

import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

/**
 * This class describes a playing Card with fields for the value, suit, and description and methods
 * to get the values in the fields and to change the values in the fields. The class will not allow invalid values as 
 * card descriptions, values or suits and the set methods validate the parameters before changing card values.
 * 
 * @author Katrina Jessen
 * @version 2013.11.11
 */
public class Card {
	// instance variables
    private int cardValue;
    private String cardSuit;
    private String cardDescription;
    String[] suits = new String[4];
    HashMap<String, Integer> descriptions = new HashMap<String, Integer>();
    
    /**
     * Default constructor for objects of class Card. Default card created is a Joker of Spades with value 0.
     */
    public Card()
    {
        cardDescription = "Joker";
        cardSuit = "Spades";
        cardValue = 0;
    }
    
    /**
     * Constructor for objects of class Card requiring user input. Input is validated against accepted values for
     * description and suit. The value field is automatically determined based on the description. If user input for description or suit
     * is invalid, the card will default to Joker of Spades with value 0.
     * @param description String used to initialize description field
     * @param suit String used to initialize suit field
     */
    public Card(String description, String suit)
    {
        populateSuits();
        populateCards();
        setDescription(description);
        setSuit(suit);
        setValue(description);
    }
    
    /**
     * Method to convert strings to a format with the first letter as upper case and the remaining letters in lower case
     * @param name String input to be converted to proper format
     * @return returns a formatted String
     */
    private String formatName(String name)
    {
        String first = "";
        String rest = "";
        if(name != null && name.length() > 0){
            first = name.substring(0,1).toUpperCase();
            if(name.length() > 1){
                rest = name.substring(1).toLowerCase();
            }
        }
        return first + rest;
    }
    
    /**
     * Populates the Hash Map which holds the card description with accompanying card value (eg. Ace, 11; Two, 2; Jack, 10 etc.)
     * This Hash Map is used to validate user input, and to determine the integer for the value field based on the description
     */
    public void populateCards()
    {
        //cards = new HashMap<String, Integer>();
        descriptions.put("Ace", 11);
        descriptions.put("Two", 2);
        descriptions.put("Three", 3);
        descriptions.put("Four", 4);
        descriptions.put("Five", 5);
        descriptions.put("Six", 6);
        descriptions.put("Seven", 7);
        descriptions.put("Eight", 8);
        descriptions.put("Nine", 9);
        descriptions.put("Ten", 10);
        descriptions.put("Jack", 10);
        descriptions.put("Queen", 10);
        descriptions.put("King", 10);
    }
    
    /**
     * Populates the suits Array which holds the four valid Strings for the field suit. This Array is used to 
     * validate user input for the suit field.
     */
    public void populateSuits()
    {
        suits[0] = "Clubs";
        suits[1] = "Diamonds";
        suits[2] = "Hearts";
        suits[3] = "Spades";
    }
    
    /**
     * Method to get the Suit at a particular position in the suits array. If the index is invalid, will return the first
     * item in the array
     * @param position integer which specifies the index in the array to return
     * @return Returns the suit String at that position
     */
    public String getSuitsValue(int position)
    {
        if(position >= 0 && position <= suits.length){
        	return suits[position];
        }else{
        	position = 0;
        	return suits[position];
        }
    }
    
    /**
     * Get value of card
     * @return value of card as integer
     */
    public int getValue()
    {
        return cardValue;
    }
    
    /**
     * Get suit of card
     * @return suit of card as String
     */
    public String getSuit()
    {
        return cardSuit;
    }
    
    /**
     * Get description of card
     * @return description of card
     */
    public String getDescription()
    {
        return cardDescription;
    }
    
    /**
     * Set value of card - checks the parameter against the cards HashMap to ensure that the parameter entered
     * is valid. If invalid, sets value to 0
     * @param description String holding the description which requires a value
     */
    public void setValue(String description)
    {
        if(descriptions.isEmpty())
        {
            populateCards();
            if(descriptions.containsKey(formatName(description)))
            {
                cardValue = descriptions.get(formatName(description));
            } else {
                cardValue = 0;
            }
        } else {
            if(descriptions.containsKey(formatName(description)))
            {
                cardValue = descriptions.get(formatName(description));
            } else {
                cardValue = 0;
            }
        }
    }
    
    /**
     * Set suit of card - checks the parameter against the suits Array to ensure that the parameter
     * entered is valid. If invalid, sets the suit to Spades
     * @param suit new card suit as String
     */
    public void setSuit(String suit)
    {
        List<String> suitList = Arrays.asList(suits);
        if(suitList.isEmpty())
        {
            populateSuits();
            if(suitList.contains(formatName(suit)))
            {
                cardSuit = formatName(suit);
            } else {
                cardSuit = "Spades";
            }
        }else{
            if(suitList.contains(formatName(suit)))
            {
                cardSuit = formatName(suit);
            } else {
                cardSuit = "Spades";
            }
        }
    }
    
    /**
     * Set description of card - checks the parameter agains the cards HashMap to ensure that the parameter
     * entered is valid. If invalid, sets the description to Joker
     * @param description new card description as String
     */
    public void setDescription(String description)
    {
        if(descriptions.isEmpty())
        {
            populateCards();
            if(descriptions.containsKey(formatName(description)))
            {
                cardDescription = formatName(description);
            } else {
                cardDescription = "Joker";
            }
        } else {
            if(descriptions.containsKey(formatName(description)))
            {
                cardDescription = formatName(description);
            } else {
                cardDescription = "Joker";
            }
        }
    }
}
