package com.zipper.snake;

import javax.swing.JFrame;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;

class Window extends JFrame
{
	private static final long serialVersionUID = -2542001418764869760L;
	
	//Menu components
	private JMenuBar menuBar;
	private JMenu mainMenu;
	private JMenu testMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem settingsMenuItem;
	private JMenuItem settingsTestMI;
	private JMenuItem gameTestMI;
	
	//Panels
	private GamePlayPanel gamePanel;
	private SettingsPanel settingsPanel;
	
	public Window()
	{
		setLayout(new FlowLayout());
		//setMinimumSize(new Dimension(410, 300) ); //look into panel setting abilities..
		
		//Create and add SettingsPanel to the GUI
		settingsPanel = new SettingsPanel();
		add(settingsPanel);
		//settingsPanel.setVisible(false);
		
		//Create and add GamePlayPanel to the GUI
		gamePanel = new GamePlayPanel();
		add(gamePanel);
		//gamePanel.setVisible(false);
		//settingsPanel.setVisible(true);
		
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
	    
	    
	    buildMenu();
	    //add(menuBar);
	    
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
	
	private void buildMenu()
	{
		//Create menuBar and Menus
		menuBar = new JMenuBar();
		mainMenu = new JMenu("Main Menu");
		testMenu = new JMenu("Test Menu");
		
		//Add Menus to menuBar
		menuBar.add(mainMenu);
		menuBar.add(testMenu);
		
		//Create Main Menu components and add them to Main Menu
		newGameMenuItem = new JMenuItem("New Game");
		settingsMenuItem = new JMenuItem("Settings");
		mainMenu.add(newGameMenuItem);
		mainMenu.add(settingsMenuItem);
		
		//Create Test Menu components and add them to Test Menu
		settingsTestMI = new JMenuItem("Settings");
		gameTestMI = new JMenuItem("Game");
		testMenu.add(settingsTestMI);
		testMenu.add(gameTestMI);
		
		//Add MenuListener to MenuItems
		//newGameMenuItem.addActionListener(new MenuListener() );
		//settingsMenuItem.addActionListener(new MenuListener() );
		settingsTestMI.addActionListener( new MenuListener() );
		gameTestMI.addActionListener(new MenuListener() );
		
		//add menuBar to JFrame
		setJMenuBar(menuBar);
		
	} //end of buildMenu method
	
	private class MenuListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			/*
			
			if( e.getSource().equals(newGameMenuItem) )
			{
				System.out.println("newGameMenuItem menu item clicked");
			}
			
			if( e.getSource().equals(settingsMenuItem) )
			{
				System.out.println("settingsMenuItem menu item clicked");
			}
			*/
			
			if( e.getSource().equals(settingsTestMI) )
			{
				//System.out.println("Settings menu item clicked");
				gamePanel.setVisible(false);
				settingsPanel.setVisible(true);
				gamePanel = null;
				//gamePanel = new GamePlayPanel();
			}
			
			if( e.getSource().equals(gameTestMI) )
			{
				//System.out.println("gameTestMI menu item clicked");
				gamePanel = new GamePlayPanel();
				settingsPanel.setVisible(false);
				add(gamePanel);
				gamePanel.addKeyListener((KeyListener) new KeyboardListener());
				//pack();
				gamePanel.setVisible(true);
			}
			
			
		} //end of actionPerformed method
		
	}
} //end of window class
