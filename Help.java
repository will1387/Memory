/*
 * Zachary Williams
 * Mrs. Gallatin
 * 2nd
 * Help
 * Help/Instructions for the Memory game
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Help/Instructions for the Memory game
 */
public class Help extends JFrame
{
	
	/**
	 * Constructs a new Help window
	 */
    public Help() 
    {
    	setSize(535,580);
    	setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel center = new JPanel();
		
   		center.setLayout(new FlowLayout(FlowLayout.LEFT));
   		try //reads in the help text
   		{
   			Scanner in = new Scanner(new File("help.txt"));
   			String help = "";
   			while(in.hasNextLine())
   			{
   				help += in.nextLine() + "\n";
   			}
   			JTextArea text = new JTextArea(help, 34, 46);
   			text.setLineWrap(true);
   			
   			center.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)); //adds a scroll bar
   		}
   		catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
   		add(center, BorderLayout.CENTER);
   		setVisible(true);
    }
    
    
}