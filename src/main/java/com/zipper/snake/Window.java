package com.zipper.snake;

import javax.swing.JFrame;
import java.awt.event.*;

class Window extends JFrame
{
	private static final long serialVersionUID = -2542001418764869760L;
	
	
	private GamePlayPanel gamePanel;
	
	public Window()
	{
		//setLayout(new FlowLayout());
		//setMinimumSize(new Dimension(410, 300) ); //look into panel setting abilities..
		
		gamePanel = new GamePlayPanel();
		add(gamePanel);
		
		// Links the window to the KeyboardListener.
		gamePanel.addKeyListener((KeyListener) new KeyboardListener());
	    
		//Make screenPanel get the [initial focus.] focus whenever window is activated.
		addWindowFocusListener(new WindowAdapter()
		{
			 public void windowGainedFocus(WindowEvent e)
			 {
				 gamePanel.requestFocusInWindow();
			 }
		});
		
		//gives the screenPanel back the keyboard focus so that arrows can control the snake.
		//screenPanel.requestFocusInWindow();
		//screenPanel.setFocusable(true);			//needed to give focus to screenPanel 
	    addMouseListener(new ScreenMouseEvent());	//needed to return focus to screenPanel
	    
		
		//Setting up the window settings
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null); //Center window on screen.
		setVisible(true);
		
		//To do : handle multiplayer .. The (above) game works, test it and see what happens
		
		//Tuple position2 = new Tuple(13,13);
		//ThreadsController c2 = new ThreadsController(position2);
		//c2.start();
		
	} //end of Window constructor
	
	
	//class that gives the screenPanel back the keyboard focus so that arrows can control the snake.
	public class ScreenMouseEvent implements MouseListener
	{
		 public void mouseClicked(MouseEvent e) {
		        //Since the user clicked on us, let us get focus!
		        gamePanel.requestFocusInWindow();
		    }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	} //end of ScreenMouseEvent class
	
	
} //end of window class
