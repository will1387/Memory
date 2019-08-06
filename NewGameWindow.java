/*
 * Zachary Williams
 * Mrs. Gallatin
 * 2nd
 * NewGameWindow
 * A new game option window for the Memory game
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * A new game option window for the Memory game
 */
public class NewGameWindow extends JFrame implements ActionListener
{
	
	private JPanel southSide;
	private JPanel center;
	private Memory rootWindow;
	
	/**
	 * Constructs the new game window
	 * @param the memory game it is
	 * responsible for affecting
	 */
    public NewGameWindow(Memory root) 
    {
    	setSize(200,100);
    	setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rootWindow = root;
		
		center = new JPanel();
		center.add(new JLabel("Congratulations!"));
								
		southSide = new JPanel();
		JButton newGame = new JButton("New Game");
		JButton exit = new JButton("Exit");
		newGame.addActionListener(this);
		exit.addActionListener(this);
		southSide.add(newGame);
		southSide.add(exit);
		add(center, BorderLayout.CENTER);
		add(southSide, BorderLayout.SOUTH);
		setVisible(true);
    }
    
    /**
	 * Performs the action of the button clicked
	 * @param e the JButton clicked
	 */
    public void actionPerformed(ActionEvent e)
    {
    	JButton button = (JButton)e.getSource();
    	
    	if(button.getText().equals("Exit"))
    	{
    		rootWindow.exit();
    		exit();
    	}
    	else if(button.getText().equals("New Game"))
    	{
    		rootWindow.exit();
    		exit();
			new Memory();
    	}
    }
    
    /**
     * Closes the window
     */
    private void exit()
    {
    	setVisible(false);
    	dispose();
    }
}