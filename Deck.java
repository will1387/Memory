/*
 * Zachary Williams
 * Mrs. Gallatin
 * 2nd
 * Deck
 * Creates a full or half deck of Cards
 */
 
 import java.util.*;
 import java.io.*;
 
 /**
  * Creates a full or half deck of cards
  */
 public class Deck
 {
 	private ArrayList<Cards> deck;
// 	private Queue<Cards> queuedDeck;
 	
 	public static final int FULL = 0;
 	public static final int RED = 1;
 	public static final int BLACK = 2;
 	
 	/**
 	 * Constructs a default shuffled full deck
 	 */
 	public Deck()
 	{
 		deck = new ArrayList<Cards>();
// 		queuedDeck = new LinkedList<Cards>();
 		for(int s = 0; s <= 3; s++ )
 		{
 			for(int n = 1; n <= 13; n++)
 			{
 				deck.add(new Cards(n, s));
 			}
 		}
 		shuffle();
 	}
 	
 	/**
 	 * Constructs a deck based on the size given
 	 * Static vairbales FULL, RED, and BLACK are used
 	 * to determine the size.
 	 */
 	public Deck(int size)
 	{
 		deck = new ArrayList<Cards>();
// 		queuedDeck = new LinkedList<Cards>();
 		int suit;
 		int max;
 		if(size == FULL)
 		{
 			suit = 0;
 			max = 3;
 		}
 		else if(size == RED)
 		{
 			suit = 2;
 			max = 3;
 		}
 		else
 		{
 			suit = 0;
 			max = 1;
 		}
 		for(int s = suit; s <= max; s++ )
 		{
 			for(int n = 1; n <= 13; n++)
 			{
 				deck.add(new Cards(n, s));
 			}
 		}
 		shuffle();
 	}
 	
 	/**
 	 * Shuffles the deck
 	 */
 	public void shuffle()
 	{
 		ArrayList<Cards> temp = new ArrayList<Cards>();
 		while(deck.size() > 0)
 		{
 			int i = (int)(Math.random()*deck.size());
 			temp.add(deck.remove(i));
 		}
 		while(temp.size() > 0)
 		{
 			deck.add(temp.remove(0));
 		}
// 		for(Cards c : deck)
// 		{
// 			queuedDeck.add(c);
// 		}
 	}

//Not used for the game of Memory

// 	public Cards getNextCard()
// 	{
// 		return queuedDeck.remove();
// 	}
// 	
// 	public void addCard(Cards c)
// 	{
// 		queuedDeck.add(c);
// 	}
// 	
// 	public boolean queueIsEmpty()
// 	{
// 		return queuedDeck.isEmpty();
// 	}
 	
 	/**
 	 * Returns the card at the given index in the ArrayList
 	 */
 	public Cards getCard(int i)
 	{
 		return deck.get(i);
 	}
 	
 	/**
 	 * Returns a stack version of the deck
 	 * @return a stack version of the deck
 	 */
 	public Stack<Cards> getStack()
 	{
 		Stack<Cards> stack = new Stack<Cards>();
 		for(int i = deck.size()-1; i >= 0; i--)
 		{
 			stack.push(deck.get(i));
 		}
 		return stack;
 	}
 	
 	/**
 	 * Returns a String of all the PNG files in order
 	 * @return a String of all the PNG files in order
 	 */
 	public String toString()
 	{
 		String s = "";
 		for(Cards c : deck)
 		{
 			s += c + "\n";
 		}
 		return s;
 	}
 }