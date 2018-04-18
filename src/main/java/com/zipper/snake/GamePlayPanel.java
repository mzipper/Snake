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

	private JPanel screenPanel;
	
	private JPanel scorePanel;
	private JLabel scoreLabel;
	static JTextField scoreTxtField;
	
	public GamePlayPanel()
	{
		
		setLayout(new BorderLayout());
		
		// Creates the ArrayList that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;

		// Creates Threads and its data and adds it to the arrayList
		for (int i = 0; i < width; i++)
		{
			data = new ArrayList<DataOfSquare>();
			for (int j = 0; j < height; j++)
			{
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			} //inner for loop
			Grid.add(data);
		} //outer for loop

		screenPanel = new JPanel();
		screenPanel.setMinimumSize(new Dimension(300, 300)); // look into panel setting abilities..

		// Setting up the layout of the panel
		screenPanel.setLayout(new GridLayout(20, 20, 0, 0));

		// Start & pauses all threads, then adds every square of each thread to
		// the panel
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				screenPanel.add(Grid.get(i).get(j).square);
			} //inner for loop
		} //outer for loop

		add(screenPanel, BorderLayout.CENTER);
		
		buildScoreComponents();
		add(scorePanel, BorderLayout.NORTH);
		
		// Links the window to the keyboardlistenner.
		//screenPanel.addKeyListener((KeyListener) new KeyboardListener());

		// initial position of the snake
		Tuple position = new Tuple(10, 10);
		// passing this value to the controller
		ThreadsController c = new ThreadsController(position);

		// c.setSpeed(100);
		// Let's start the game now..
		c.start();

	} // end of GamePlayPanel constructor
	
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
