package com.zipper.snake;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.zipper.snake.Window.ScreenMouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Window extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	
	private JPanel speedButtonsPanel;
	
	private JRadioButton rBLowSpeed;
	private JRadioButton rBMedSpeed;
	private JRadioButton rBHiSpeed;
	private ButtonGroup group;
	
	private GamePlayPanel gamePanel;
	
	public Window()
	{
		//setLayout(new FlowLayout());
		//setMinimumSize(new Dimension(410, 300) ); //look into panel setting abilities..
		
		gamePanel = new GamePlayPanel();
		add(gamePanel);
		
		// Links the window to the keyboardlistenner.
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
	    
//		buildSpeedRadioButtons();
//		add(speedButtonsPanel, BorderLayout.WEST);
	    
		
		
		

		
		
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
	} //end of MouseEventDemo class
	
	
	
} //end of window class
