package com.zipper.snake;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Window extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 20;
	public static int height = 20;
	
	private JPanel screenPanel;
	
	private JPanel speedButtonsPanel;
	private JRadioButton rBLowSpeed;
	private JRadioButton rBMedSpeed;
	private JRadioButton rBHiSpeed;
	private ButtonGroup group;
	
	
	public Window()
	{
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(410, 300) ); //look into panel setting abilities..
		
		buildScreenPanelPlus();
		add(screenPanel, BorderLayout.CENTER);
		
		buildSpeedRadioButtons();
	    add(speedButtonsPanel, BorderLayout.WEST);
	    
		// Links the window to the keyboardlistenner.
		screenPanel.addKeyListener((KeyListener) new KeyboardListener());
		
		//gives the screenPanel back the keyboard focus so that arrows can control the snake.
		//screenPanel.requestFocusInWindow();
		//screenPanel.setFocusable(true);			//needed to give focus to screenPanel 
	    addMouseListener(new MouseEventDemo());	//needed to return focus to screenPanel
		
	    // initial position of the snake
		Tuple position = new Tuple(10,10);
		// passing this value to the controller
		ThreadsController c = new ThreadsController(position);
	    
		//Make screenPanel get the [initial focus.] focus whenever window is activated.
		addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        screenPanel.requestFocusInWindow();
		    }
		});
	   
		//c.setSpeed(100);
		//Let's start the game now..
		c.start();

		
		
		//Setting up the window settings
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null); //Center window on screen.
		setVisible(true);
		
		//To do : handle multiplayers .. The above works, test it and see what happens
		
		//Tuple position2 = new Tuple(13,13);
		//ControlleurThreads c2 = new ControlleurThreads(position2);
		//c2.start();
		
	}
	
	private void buildScreenPanelPlus()
	{
		screenPanel = new JPanel();
		screenPanel.setMinimumSize(new Dimension(300,300)); //look into panel setting abilities..
		
		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++)
		{
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++)
			{
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			}
			Grid.add(data);
		}
				
		// Setting up the layout of the panel
		screenPanel.setLayout(new GridLayout(20,20,0,0));
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				screenPanel.add(Grid.get(i).get(j).square);
			}
		}
				
	} //end of buildScreenPanelPlus method
	
	private void buildSpeedRadioButtons()
	{
		speedButtonsPanel = new JPanel();
		
		//Set some Settings to Panel.
		speedButtonsPanel.setLayout(new GridLayout(3,1));
		speedButtonsPanel.setBorder(BorderFactory.createTitledBorder("Choose Snake Speed"));
	
		//Create RadioButtons.
		rBLowSpeed 	= new JRadioButton("Low Speed");
		rBMedSpeed 	= new JRadioButton("Medium Speed");
		rBHiSpeed 	= new JRadioButton("High Speed (Original)");
		
		//Add RadioButtons to a Group.
		group = new ButtonGroup();
		group.add(rBLowSpeed);
		group.add(rBMedSpeed);
		group.add(rBHiSpeed);
	
		rBHiSpeed.doClick();
	
		//Add RadioButtons to the Panel.
		speedButtonsPanel.add(rBLowSpeed);
		speedButtonsPanel.add(rBMedSpeed);
		speedButtonsPanel.add(rBHiSpeed);
	
		rBLowSpeed.addActionListener(new RadioButtonListener());
		rBMedSpeed.addActionListener(new RadioButtonListener());
		rBHiSpeed.addActionListener(new RadioButtonListener());
	
	} // end of buildSpeedRadioButtons method.
	
	//class enables radio buttons to work.
	private class RadioButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(rBLowSpeed.isSelected())
			{
				ThreadsController.setSpeed(110);
			}
			else if(rBMedSpeed.isSelected())
			{
				ThreadsController.setSpeed(80);
			}
			else if(rBHiSpeed.isSelected())
			{
				ThreadsController.setSpeed(50);
			}
		}
		
	} //end of RadioButtonListener class
	
	//class that gives the screenPanel back the keyboard focus so that arrows can control the snake.
	public class MouseEventDemo implements MouseListener
	{
		 public void mouseClicked(MouseEvent e) {
		        //Since the user clicked on us, let us get focus!
		        screenPanel.requestFocusInWindow();
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
	} //end of MouseEventDemo class
	
} //end of window class
