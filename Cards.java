/*
 * Zachary Williams
 * Mrs. Gallatin
 * 2nd
 * Cards
 * Stores card attributes
 */
 
 /**
  * Stores card attributes
  */
 public class Cards
 {
 	private int number;
 	private int suit;
 	
 	public static final int SPADES = 0;
 	public static final int CLUBS = 1;
 	public static final int HEARTS = 2;
 	public static final int DIAMONDS = 3;
 	
 	public static final int BLACK_SUIT = 0;
 	public static final int RED_SUIT = 1;
 	
 	public static final int ACE = 1;
 	public static final int JACK = 11;
 	public static final int QUEEN = 12;
 	public static final int KING = 13;
 	
 	/**
 	 * Constructs a card with a number and suit
 	 * Precondition: num is between 1 and 13 inclusively
 	 * @param num the number of the card, non-number cards are declared by constants
 	 * ACE, JACK, QUEEN, KING
 	 * @param suit the number of the suit declared by constants
 	 * SPADES, CLUBS, HEARTS, and DIAMONDS
 	 */
 	public Cards(int num, int suit)
 	{
 		number = num;
 		this.suit = suit;
 	}
 	
 	/**
 	 * sets the number of the card, non-number cards are declared by constants
 	 * ACE, JACK, QUEEN, KING
 	 * @param num the new number of the card
 	 */
 	public void setNumber(int num)
 	{
 		number = num;
 	}
 	
 	/**
 	 * sets the suit of the card
 	 * @prarm the new suit (suits are declared by constants 
 	 * SPADES, CLUBS, HEARTS, and DIAMONDS)
 	 */
 	public void setSuit(int suit)
 	{
 		this.suit = suit;
 	}
 	
 	/**
 	 * gets the suit color of the card
 	 * @return the color of the suit declared
 	 * by constants BLACK_SUIT and RED_SUIT
 	 */
 	public int getSuitColor()
 	{
 		if(suit == SPADES || suit == CLUBS)
 			return BLACK_SUIT;
 		else
 			return RED_SUIT;
 	}
 	
 	/**
 	 * returns the number of the card (non-number cards correspond by constants
 	 * ACE, JACK, QUEEN, KING)
 	 * @return the number of the card
 	 * Ace = 1 Jack = 11 Queen = 12 King = 13
 	 */
 	public int getNumber()
 	{
 		return number;
 	}
 	
 	/**
 	 * returns the suit
 	 * @return the suit(suits correspond to constants 
 	 * SPADES, CLUBS, HEARTS, and DIAMONDS)
 	 */
 	public int getSuit()
 	{
 		return suit;
 	}
 	
 	/**
 	 * returns the prefix for the PNG file name
 	 * @return the prefix for the PNG file name
 	 */
 	private String getPrefix()
 	{
 		if(number == 1 || number > 10)
 		{
 			switch(number)
 			{
 				case 11: return "Jack";
				case 12: return "Queen";
				case 13: return "King";
				default: return "Ace";
 			}	
 		}
 		else
 			return "" + number;
 	}
 	
 	/**
 	 * returns the suffix for the PNG file name
 	 * @return the suffix for the PNG file name
 	 */
 	private String getSuffix()
 	{
 		switch(suit)
 		{
 			case SPADES: return "Spades";
 			case CLUBS: return "Clubs";
 			case HEARTS: return "Hearts";
 			default: return "Diamonds";
 		}
 	}
 	
 	/**
 	 * returns the PNG file name of the card
 	 * @return the PNG file name of the card
 	 */
 	public String getPNG()
 	{
 		return getPrefix() + "_of_" + getSuffix() + ".png";
 	}
 	
 	public String toString()
 	{
 		return getPrefix() + " of " + getSuffix();
 	}
 }