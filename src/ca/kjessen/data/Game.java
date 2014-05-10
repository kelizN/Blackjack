package ca.kjessen.data;

import java.util.ArrayList;

import ca.kjessen.data.Card;
import ca.kjessen.reader.InputReader;
/**
 * Class Game simulates a game of BlackJack with only one player (the user). The objective of the game
 * is to get 21 points in a hand without going over (or bust). Game has an input reader so
 * the user can specify whether to continue a game, and whether to hit or stand.
 * 
 * @author Katrina Jessen
 * @version 2014.02.04
 */

public class Game {
	
	//instance variables
	private InputReader reader;
	private Deck deck;
	private ArrayList<Card> playerCards;
	public static final int BLACKJACK = 21;
	
	/**
	 * Default constructor for objects of class game
	 */
	public Game()
	{
		reader = new InputReader();
		deck = new Deck();
		playerCards = new ArrayList<Card>();
	}
	
	/**
	 * Method for playing BlackJack. User will be dealt cards and prompted to hit or stand (if applicable)
	 * and asked whether they would like to play additional rounds.
	 */
	public void play()
	{
		int points = 0;
		int rounds = 0;
		intro();
		for(String playInput = "yes"; playInput.equals("yes") ; rounds++ ){
			boolean playing = true;
			dealCard();
			dealCard();
			while(playing){
				//check outcome
					//if Blackjack playing = false
				if(hasBlackjack()){
					System.out.println("Blackjack!");
					points += 5;
					playing = false;
					//else if busted playing = false
				} else if(isBusted()){
					System.out.println("You have busted!");
					playing = false;
				}else{//else ask player hit or stand
					showHand();
					System.out.println();
					System.out.println("Your choice: hit or stand? > ");
					String hitInput = reader.getInput().toLowerCase();
					//if stand playing = false
					if(isStanding(hitInput)){
						System.out.println("Player stands");
						playing = false;
						if(getHandValue() >= 17){
							points += 1;
						}
					}else if(isHit(hitInput)){//else deal card
						dealCard();
					}else{
						System.out.println("That is not a valid input. Please enter 'hit' or 'stand'.");
					}
				}
			}
			showHand();
			System.out.println();
			System.out.print("Another round? Type yes or no: ");
			playInput = reader.getInput().toLowerCase();
			playerCards.clear();
		}
		report(points, rounds);
	}
	
	/**
	 * Adds all 52 valid cards to the deck and shuffles them
	 */
	private void newShuffledDeck()
	{
		deck.addAllCards();
		deck.shuffle();
	}
	
	/**
	 * Prints the introduction explaining the rules to the console
	 */
	private void intro()
	{
		System.out.println("Welcome to 1451 Blackjack!");
		System.out.println("You will start with two cards.");
		System.out.println("You will be prompted to 'hit' or 'stand'");
		System.out.println("'hit' means you want another card, 'stand' not.");
		System.out.println("You are trying to get Blackjack with exactly 21 points.");
		System.out.println("If you get Blackjack you will get 5 points for that round, ");
		System.out.println("if you stand with 17 or more you will get 1 point for ");
		System.out.println("that round, if you stand with less than 17 or you go bust you");
		System.out.println("will receive zero points for that round.");
		System.out.println("");
	}
	
	/**
	 * Removes a card from the deck and places it in the player's hand. First checks to 
	 * ensure that the deck is not empty, and if it is empty, populates and shuffles the deck.
	 */
	private void dealCard()
	{
		if(deck.isEmpty()){
			newShuffledDeck();
		}
		playerCards.add(deck.returnFirstCard());
	}
	
	/**
	 * Gets the value of all the cards in the players hand. Face cards are 10 points, number cards
	 * are their value (eg. Two = 2 points, Two = 3 points etc.), and Aces are 11 points unless that
	 * would cause the value of the hand to be greater than 21, in which case they are 1 point.
	 * @return Returns integer holding the value of all the cards in the player's hand
	 */
	private int getHandValue()
	{
		int handValue = 0;
		int numberAces = 0;
		for(Card allCards : playerCards){
			handValue += allCards.getValue();
		}
		for(Card allCards : playerCards){
			if(allCards.getDescription().equals("Ace")){
				numberAces++;
			}
		}
		int handValueCopy = handValue;
		for(int i = 0; i < numberAces; i++){
			if(handValueCopy <= 21){
				handValue = handValueCopy;
				i = numberAces;
			}else{
				handValueCopy -= 10;
				handValue = handValueCopy;
			}
		}
		return handValue;
	}
	
	/**
	 * Prints the contents of the user's hand to the console in the format "<description> of <suit>" along
	 * with the value of the cards in the user's hand
	 */
	private void showHand()
	{
		System.out.println("Your cards: ");
		for(Card allCards : playerCards){
			System.out.println(allCards.getDescription() + " of " + allCards.getSuit());
		}
		System.out.println("Hand value: " + getHandValue());
	}
	
	/**
	 * Checks to see if the user has BlackJack ie. if the value of the cards in their hand is equal to 21
	 * @return Returns true if the user has BlackJack (ie. hand value = 21), false if the user does not
	 */
	private boolean hasBlackjack()
	{
		return (getHandValue() == BLACKJACK);
	}
	
	/**
	 * Checks to see if the user has Busted, ie. if the value of the cards in their hand is greater than 21
	 * @return Returns true if the user has busted (ie. hand value > 21), false if the user has not
	 */
	private boolean isBusted()
	{
		return (getHandValue() > BLACKJACK);
	}
	
	/**
	 * Checks to see if the user is standing, ie. has chosen to receive no more cards, with a hand value of less than 21.
	 * @return Returns true if the user is standing (ie. user entered "stand" when prompted), false if the user is not
	 */
	private boolean isStanding(String input)
	{
		return (input.toLowerCase().equals("stand"));
	}
	
	/**
	 * Checks to see if the user is hitting ie. has chosen to receive more cards.
	 * @return Returns true if the user is hitting (ie. user entered "hit" when prompted), false if the user is not
	 */
	private boolean isHit(String input){
		return (input.toLowerCase().equals("hit"));
	}
	
	/**
	 * Reports the final point tally from all of the rounds played by the user, and the total number of rounds played
	 * @param _points integer value of the number of points earned by the user, with 5 points earned for
	 * Blackjack and 1 point for standing at a hand value of 17 or greater
	 * @param _rounds integer value of the number of rounds of Blackjack played by the user
	 */
	private void report(int _points, int _rounds)
	{
		System.out.println("Player points: " + _points);
		System.out.println("Total rounds: " + _rounds);
	}
}
