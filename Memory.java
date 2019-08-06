/*
 * Zachary Williams
 * Mrs. Gallatin
 * 2nd
 * Memory
 * A game of Memory
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * A game of Memory
 */
public class Memory extends JFrame implements ActionListener
{
	
	private ArrayList<JButton> buttons;
	private ArrayList<JButton> deckButtons;
	private Deck deck; 
	private JPanel northSide;
	private JPanel center;
	private int score;
	private int flippedOver;
	private int cardOne;
	private int cardTwo;
	private JLabel scoreView;
	private JCheckBox redBox;
	private JCheckBox blackBox;
	private boolean halfDeck;
	private boolean hasStarted;
	private Queue<Cards> freeTries;
	private JLabel freeTryView;
	private int nextFreeTry;
	
	public static final int CARDS_ARE_VISIBLE = 3;
	
	/**
	 * Constructs the Memory game window
	 */
    public Memory() 
    {
    	setSize(1006,479);
    	setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		center = new JPanel();
		northSide = new JPanel();
		flippedOver = CARDS_ARE_VISIBLE;
		cardOne = cardTwo = -1;
		score = 0;
		scoreView = new JLabel("     Score: " + score);
		deckButtons = new ArrayList<JButton>();
		halfDeck = true;
		hasStarted = false;
		freeTries = new LinkedList<Cards>();
		
		JToolBar topSide = new JToolBar(JToolBar.HORIZONTAL);
		
		buttons = new ArrayList<JButton>();
		buttons.add(new JButton("Help"));
		buttons.add(new JButton("Quit"));
		buttons.add(new JButton("Start"));
		for(JButton b: buttons)
		{
			topSide.add(b);
			b.addActionListener(this);
		}
		
		redBox = new JCheckBox("Red Cards");
		blackBox = new JCheckBox("Black Cards");
		topSide.add(redBox);
		topSide.add(blackBox);
		topSide.add(scoreView);
		
		nextFreeTry = 11;
		freeTryView = new JLabel("     Next Free Try: " + nextFreeTry); 
		topSide.add(freeTryView);
		
		northSide.add(new JScrollPane(topSide, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		((JScrollPane)northSide.getComponent(0)).setPreferredSize(new Dimension(1006,35));
		
		add(northSide, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		
		setVisible(true);
    }
    
    /**
	 * Performs the action of the button clicked
	 * @param e the JButton clicked
	 */
    public void actionPerformed(ActionEvent e)
    {
    	JButton button = (JButton)e.getSource();
    	int i = 0;
    	boolean found = false;
   	 	while(!found && i != deckButtons.size())
    	{
    		if(deckButtons.get(i).equals(button))
    			found = true;
    		else
    			i++;
    	}
    	
    	if(found)
    	{
    		if(flippedOver != CARDS_ARE_VISIBLE)
    		{
    			if(flippedOver < 2 && deckButtons.get(i).getIcon() != null)
    			{
    				button.setIcon(new ImageIcon(deck.getCard(i).getPNG()));
    				flippedOver++;
    				if(cardOne == -1)
    				{
    					cardOne = i;
    				}
    				else
    				{
    					if(i != cardOne)
    						cardTwo = i;
    					else
    						flippedOver--;
    				}
    			}
    			else if(flippedOver == 2)
    			{
    				if(deck.getCard(cardOne).getNumber() == deck.getCard(cardTwo).getNumber() && deck.getCard(cardOne).getSuitColor() == deck.getCard(cardTwo).getSuitColor())
    				{
    						freeTries.add(deck.getCard(cardOne));
    						deckButtons.get(cardOne).setIcon(null);
    						deckButtons.get(cardTwo).setIcon(null);
    						boolean win = true;
    						for(JButton b : deckButtons)
    						{
    							if(b.getIcon() != null)
    								win = false;
    						}
    						if(win)
    						{
    							new NewGameWindow(this);
    						}
    						
    						if(freeTries.peek() == null)
    							nextFreeTry = 11;
    						else
    						{
    							nextFreeTry = freeTries.peek().getNumber();
    							if(nextFreeTry > 10)
    								nextFreeTry = 10;
    						}
    						freeTryView.setText("     Next Free Try: " + nextFreeTry);
    							
    				}
    				else
    				{
    					Cards card = freeTries.poll();
    					deckButtons.get(cardOne).setIcon(new ImageIcon("Back.png"));
    					deckButtons.get(cardTwo).setIcon(new ImageIcon("Back.png"));
    					
    					if(card == null || card.getNumber() >= deck.getCard(cardOne).getNumber() || card.getNumber() >= deck.getCard(cardTwo).getNumber())
    					{
    						if(deck.getCard(cardOne).getNumber() > deck.getCard(cardTwo).getNumber())
    						{
    							if(deck.getCard(cardOne).getNumber() >= 10)
    								score += 10;
    							else
	    							score += deck.getCard(cardOne).getNumber();
    						}
    						else
    						{
    							if(deck.getCard(cardTwo).getNumber() >= 10)
    								score += 10;
    							else
	    							score += deck.getCard(cardTwo).getNumber();
    						}
    					
    						scoreView.setText("     Score: " + score);
    					}
    					
    					if(freeTries.peek() == null)
    						nextFreeTry = 11;
    					else
    					{
    						nextFreeTry = freeTries.peek().getNumber();
    						if(nextFreeTry > 10)
    							nextFreeTry = 10;
    					}
    					freeTryView.setText("     Next Free Try: " + nextFreeTry);
    					
    					
    					
    				}
    				cardOne = cardTwo = -1;
    				flippedOver = 0;
    			}	
    		}
    		else
    		{
    			hideCards();
    		}
    	}
    	else
    	{
    		if(button.getText().equals("Quit"))
    		{
    			exit();
    		}
    		else if(button.getText().equals("Start") && !hasStarted)
    		{
    			center.setLayout(new GridLayout(4,13));
    			if(redBox.isSelected() && blackBox.isSelected())
    			{
    				deck = new Deck(Deck.FULL);
    				
    				halfDeck = false;
    			}
    			else if(redBox.isSelected())
    			{
    				deck = new Deck(Deck.RED);
    			}
    			else
    			{
    				deck = new Deck(Deck.BLACK);
    			}
    			
				
				Stack<Cards> cards = deck.getStack();
				while(!cards.empty())
				{
					String s = cards.pop().getPNG();
					Icon ic = new ImageIcon(s);
					deckButtons.add(new JButton(ic));
				}
				for(JButton b: deckButtons)
				{
					center.add(b);
					b.addActionListener(this);
				}
				hasStarted = true;
				center.paintImmediately(0,0,1000,1000);
				showCards();
    		}
    		else if(button.getText().equals("Help"))
    		{
    			new Help();
    		}
    	}
    	
    }
    
    /**
     * Shows the cards for the given period of time then
     * it flips the cards back over
     */
    public void hideCards()
    {
    	int i = 0;
    	for(JButton b : deckButtons)
    	{
    		b.setIcon(new ImageIcon(deck.getCard(i).getPNG()));
    		i++;
    	}
    	center.setVisible(true);
    	try
		{
			if(halfDeck)
			{
				center.paintImmediately(0,0,1000,1000);
				Thread.sleep(5000);
			}	
			else
			{
				center.paintImmediately(0,0,1000,1000);
				Thread.sleep(10000);
			}
				
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}

		center.setVisible(false);
		for(JButton b : deckButtons)
		{
			b.setIcon(new ImageIcon("Back.png"));
		}
		center.setVisible(true);
		flippedOver = 0;
    }
    
    /**
     * Makes the cards visible on the screen
     */
    public void showCards()
    {
		center.setVisible(false);
		for(JButton b : deckButtons)
		{
			b.setIcon(new ImageIcon("Back.png"));
		}
		center.setVisible(true);
    }
    
    /**
     * Ends the game and closes the window
     */
    public void exit()
    {
    	setVisible(false);
    	dispose();
    }
}