package com.zipper.snake;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
class GamePlayPanel extends JPanel
{
	public static int width = 20;
	public static int height = 20;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	private ArrayList<DataOfSquare> data;
	private ThreadsController c;
	
	private JPanel screenPanel;
	
	private JPanel scorePanel;
	private JLabel scoreLabel;
	static JTextField scoreTxtField;
	
	public GamePlayPanel()
	{
		
		setLayout(new BorderLayout());
		
		// Creates the ArrayList that'll contain the DataOfSquares
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		

		// Creates the DataOfSquares and its data and adds it to the arrayList
		for (int i = 0; i < height; i++)
		{
			data = new ArrayList<DataOfSquare>();
			for (int j = 0; j < width; j++)
			{
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			} //inner for loop
			Grid.add(data);
		} //outer for loop
		
		screenPanel = new JPanel();
		screenPanel.setMinimumSize(new Dimension(300, 300)); // look into panel setting abilities..

		// Setting up the layout of the panel
		screenPanel.setLayout(new GridLayout(height, width));

		// Adds every squarePanel of each DataOfSquare to
		// the panel
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				screenPanel.add(Grid.get(i).get(j).square);
			} //inner for loop
		} //outer for loop

		add(screenPanel, BorderLayout.CENTER);
		
		buildScoreComponents();
		add(scorePanel, BorderLayout.NORTH);
		
		// Links the window to the KeyboardListener.
		//screenPanel.addKeyListener((KeyListener) new KeyboardListener());

		// initial position of the snake
		Tuple position = new Tuple(10, 10);
		// passing this value to the controller
		c = new ThreadsController(position);

		// c.setSpeed(100);
		// Let's start the game now..
		c.start();

	} //end of GamePlayPanel constructor
	
	public void resetGrid()
	{
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				Grid.get(i).get(j).lightMeUp(2);
			} //inner for loop
		} //outer for loop
		
	} //end of resetGrid method
	
	public ThreadsController getThreadsController()
	{
		return c;
	} //end of getThreadsController method
	
	public void buildScoreComponents()
	{
		scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		
		scoreLabel = new JLabel("Score:");
		scoreTxtField = new JTextField();
		scoreTxtField.setEditable(false);
		
		scorePanel.add(scoreLabel);
		scorePanel.add(scoreTxtField);
		
		scoreTxtField.setColumns(4);
		scoreTxtField.setHorizontalAlignment(JTextField.RIGHT);
		//scoreTxtField.setText(""); //for testing
		
	} // end of buildScoreComponents method
	
} // end of GamePlayPanel class
